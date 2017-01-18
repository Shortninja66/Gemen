package us.theaura.gemen.server.sync;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;

import us.theaura.gemen.TheAura;

/**
 * Class for all of the synchronous tasks that need to be executed throughout the
 * server's uptime. This class also asynchronously tracks lag within the core.
 * 
 * #tpsTask() is defined mainly due to it being a completely independent task that will have NO other
 * tasks linked to its delay.
 * 
 * @since 20 February 2016 at 5:48 AM
 * @author Shortninja
 */

public class Tasks {
	
	private static Map<Long, TaskBus> busses = new HashMap<Long, TaskBus>();
	private static volatile double tps = 20.0;
	private DecimalFormat decimal = new DecimalFormat("#.#");
	
	public Tasks() {
		addMainTask(null, TaskDelay.SLOW);
		addMainTask(null, TaskDelay.FAST);
		tpsTask();
	}
	
	/**
	 * @return The current tps of the server core.
	 */
	public String tps() {
		return decimal.format(tps);
	}
	
	/**
	 * Attempts to add the given task to an existing task bus. If this fails, one could create a new
	 * bus.
	 * 
	 * @param task Abstract code to add.
	 * @param delay Relative delay between executions of the task. Will NOT be exact.
	 * @return Whether or not the task was added.
	 */
	public boolean addTask(ITask task, long delay) {
		boolean added = false;
		
		for(TaskBus bus : busses.values()) {
			if(bus.addTask(task, delay)) {
				added = true;
				break;
			}
		}
		
		return added;
	}
	
	/**
	 * Executes the given task synchronously.
	 * 
	 * @param task Abstract code to execute.
	 * @param delay Delay to wait before executing.
	 */
	public void synchronousExecution(final ITask task, long delay) {
		new BukkitRunnable() {
			@Override
			public void run() {
				task.execute();
			}
		}.runTaskLater(TheAura.instance(), delay);
	}
	
	private void addMainTask(final ITask task, final TaskDelay delay) {
		final long timeDelay = delay.delay();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if(task != null) {
					task.execute();
				}
				
				busses.get(timeDelay).call();
			}
		}.runTaskTimer(TheAura.instance(), 0L, timeDelay);;
		
		busses.put(timeDelay, new TaskBus(delay.identifier(), delay));
	}
	
	private void tpsTask() {
		new BukkitRunnable() {
			long second = (System.currentTimeMillis() / 1000);
			long currentSecond;
			int ticks;
			
			@Override
			public void run() {
				if(currentSecond != second) {
					currentSecond = second;
					tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
					ticks = 0;
				}else ticks++;
			}
		}.runTaskTimerAsynchronously(TheAura.instance(), 0L, 1L);
	}
	
}