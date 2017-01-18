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

public class QueueService {
	
	private static Map<UUID, Queue> queues = new HashMap<UUID, Queue>();
	private static final String CONFIRM = "yes";

	public QueueService() {}
	
	/**
	 * Adds the given queue to the "available" map.
	 * 
	 * @param queue Queue to add.
	 */
	public void add(Queue queue) {
		queues.put(queue.uuid(), queue);
	}

	/**
	 * Submits the given response to the associated queue.
	 * 
	 * @param uuid UUID associated with queue.
	 * @param response Response in chat.
	 */
	public void respond(UUID uuid, String response) {
		if(!queues.containsKey(uuid)) {
			return;
		}

		queues.get(uuid).submit(parseResponse(response));
	}

	private boolean parseResponse(String response) {
		boolean hasAccepted = false;

		for(String word : response.split(" ")) {
			if(word.equalsIgnoreCase(CONFIRM) || word.toLowerCase().contains(CONFIRM)) {
				hasAccepted = true;
				break;
			}
		}

		return hasAccepted;
	}
	
}