package us.theaura.gemen.player.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class that controls all queues and their responses.
 * 
 * @since 23 December 2016 9:46 PM
 * @author Shortninja
 */

public class QueueService
{
	private static Map<UUID, Queue> queues = new HashMap<UUID, Queue>();
	
	public void add(Queue queue)
	{
		queues.put(queue.uuid(), queue);
	}
	
	public void respond(UUID uuid, String response)
	{
		if(!queues.containsKey(uuid))
		{
			return;
		}
		
		queues.get(uuid).submit(parseResponse(response));
	}
	
	private boolean parseResponse(String response)
	{
		boolean hasAccepted = false;
		
		switch(response.toLowerCase().trim())
		{
			case "yes":
			case "ok":
			case "okay":
				hasAccepted = true;
		}
		
		return hasAccepted;
	}
}