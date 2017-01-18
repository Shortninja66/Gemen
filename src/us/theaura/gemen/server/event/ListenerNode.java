package us.theaura.gemen.server.event;

import org.bukkit.event.player.PlayerJoinEvent;

import us.theaura.gemen.Backend;
import us.theaura.gemen.server.event.attribute.EventParameters;
import us.theaura.gemen.util.lib.bukkit.event.Subscriber;

/**
 * ListenerNode.java
 * 
 * @since Jan 15, 2017 4:55:38 PM
 * @author Shortninja
 */

public class ListenerNode {

	@Subscriber()
	public void onJoin(PlayerJoinEvent event) {
		Backend.instance().event.call(0, new EventParameters().setPlayer(event.getPlayer()));
	}
	
}