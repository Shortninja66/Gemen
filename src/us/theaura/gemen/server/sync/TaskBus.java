package us.theaura.gemen.server.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple bus that allows for modules to execute tasks without worrying about
 * runnables.
 * 
 * @since 10 November 2016 9:03 PM
 * @author Shortninja
 */

public class TaskBus
{
	private final int IDENTIFIER;
	private final long DELAY;
	private final double BUFFER;
	private List<ITask> passengers = new ArrayList<ITask>();
	
	public TaskBus(int identifier, long delay)
	{
		IDENTIFIER = identifier;
		DELAY = delay;
		BUFFER = DELAY - (DELAY * 0.3);
	}
	
	public int getIdentifier()
	{
		return IDENTIFIER;
	}
	
	public long getDelay()
	{
		return DELAY;
	}
	
	public boolean addTask(ITask task, long delay)
	{
		boolean added = false;
		
		if((DELAY - delay) - BUFFER <= 0)
		{
			passengers.add(task);
			added = true;
		}
		
		return added;
	}
	
	public void call()
	{
		for(ITask task : passengers)
		{
			task.execute();
		}
	}
}