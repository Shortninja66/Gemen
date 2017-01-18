package us.theaura.gemen.server.event;

import java.util.HashMap;
import java.util.Map;

import us.theaura.gemen.Backend;
import us.theaura.gemen.server.event.attribute.EventParameters;
import us.theaura.gemen.server.event.attribute.EventType;
import us.theaura.gemen.server.event.attribute.TaskNode;
import us.theaura.gemen.server.log.LogService;
import us.theaura.gemen.util.lib.bukkit.event.BukkitEventBus;

/**
 * Extension of rbrick's event bus that only requires one instantiation of an event.
 * 
 * @since 14 January 2017 7:39 PM
 * @author Shortninja
 */

public class EventService {

	private static Map<Integer, TaskNode> events = new HashMap<Integer, TaskNode>();
	private LogService log = Backend.instance().log;
	
	public void initialize(BukkitEventBus eventBus) {
		eventBus.register(new ListenerNode());
	}
	
	/**
	 * @param task Task to associate with.
	 * @param type Event type to register to.
	 */
	public void register(EventTask task, EventType type) {
		if(events.containsKey(type.identifier())) {
			events.get(type.identifier()).addTask(task);
		}else events.put(type.identifier(), new TaskNode(task));
	}
	
	/**
	 * @param identity Identity of the listener.
	 * @param parameters Parameters of the event.
	 */
	public void call(int identity, EventParameters parameters) {
		if(events.containsKey(identity)) {
			events.get(identity).call(parameters);
			return;
		}else if(!containsIdentity(identity)) {
			log.warn("Unknown identity '" + identity + "' called!", true);
		}else if(identity < 0) {
			log.warn("Invalid identity '" + identity + "' specified!", false);
		}
	}
	
	private boolean containsIdentity(int identity) {
		boolean contains = false;
		
		for(EventType type : EventType.values()) {
			if(type.identifier() == identity) {
				contains = true;
				break;
			}
		}
		
		return contains;
	}
	
}