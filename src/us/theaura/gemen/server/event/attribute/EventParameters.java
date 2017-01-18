package us.theaura.gemen.server.event.attribute;

import org.bukkit.entity.Player;

/**
 * Simple object class for passing available event parameters.
 * 
 * @since 14 January 2017 8:21PM
 * @author Shortninja
 */

public class EventParameters {

	private Player player = null;
	
	/**
	 * @return Player in this event; may be null.
	 */
	public Player player() {
		return player;
	}
	
	/**
	 * @param player Player associated with the event.
	 * @return Current instance.
	 */
	public EventParameters setPlayer(Player player) {
		this.player = player;
		return this;
	}
	
}