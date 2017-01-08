package us.theaura.gemen.server.sync;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;

import us.theaura.gemen.TheAura;

/**
 * Class for all of the synchronous tasks that need to be executed throughout the
 * server's uptime. This class also tracks lag within the core.
 * 
 * slowInterval  1 minute and 30 seconds interval.
 * fastInterval  2 second interval.
 * tpsInterval   1/20th of a second interval.
 * 
 * @since 20 February 2016 at 5:48 AM
 * @author Shortninja
 */

public class Tasks
{
	private Map<Long, TaskBus> busses = new HashMap<Long, TaskBus>();
	private DecimalFormat decimal = new DecimalFormat("#.#");
	private double tps = 20.0;
	
	public Tasks()
	{
		slowInterval();
		fastInterval();
		tpsInterval();
	}
	
	public String getTps()
	{
		return decimal.format(tps);
	}
	
	public void addTask(ITask task, long delay)
	{
		for(TaskBus bus : busses.values())
		{
			if(bus.addTask(task, delay))
			{
				return;
			}
		}
	}
	
	public void synchronousExecution(final ITask task, long delay)
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				task.execute();
			}
		}.runTaskLater(TheAura.instance(), delay);
	}
	
	private void slowInterval()
	{
		long delay = 1800;
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				busses.get(delay).call();
			}
		}.runTaskTimer(TheAura.instance(), 0L, delay);
		
		busses.put(delay, new TaskBus(0, delay));
	}
	
	private void fastInterval()
	{
		long delay = 40;
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				busses.get(delay).call();
			}
		}.runTaskTimer(TheAura.instance(), 0L, delay);
		
		busses.put(delay, new TaskBus(1, delay));
	}
	
	private void tpsInterval()
	{
		new BukkitRunnable()
		{
			long second = (System.currentTimeMillis() / 1000);
			long currentSecond;
			int ticks;
			
			@Override
			public void run()
			{
				if(currentSecond != second)
				{
					currentSecond = second;
					tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
					ticks = 0;
				}else ticks++;
			}
		}.runTaskTimerAsynchronously(TheAura.instance(), 0L, 1L);
	}
}