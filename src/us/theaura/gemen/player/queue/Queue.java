package us.theaura.gemen.player.queue;

import java.util.UUID;

import us.theaura.gemen.server.sync.ITask;

/**
 * Object class for storing information regarding a queue, which basically allows for players to submit
 * responses via chat.
 * 
 * @since 23 December2016 9:46 PM
 * @author Shortninja
 */

public class Queue
{
	private UUID uuid;
	private ITask[] task;
	
	public Queue(UUID uuid, ITask[] task)
	{
		this.uuid = uuid;
		this.task = task;
	}
	
	public UUID uuid()
	{
		return uuid;
	}
	
	public void submit(boolean hasAccepted)
	{
		if(hasAccepted)
		{
			task[0].execute();
		}else task[1].execute();
	}
}