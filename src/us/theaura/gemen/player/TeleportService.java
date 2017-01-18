package us.theaura.gemen.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.server.event.attribute.EventType;
import us.theaura.gemen.server.world.LocationWrapper;
import us.theaura.gemen.server.world.Planet;

/**
 * Class for managing player teleportation actions.
 * 
 * @since 14 January 2017 5:49 PM
 * @author Shortninja
 */

public class TeleportService {
	
	private static Map<UUID, LocationWrapper> lastLocations = new HashMap<UUID, LocationWrapper>();
	
	/*
	 * @PlayerJoinEvent
	 * 		Sets the player's previous location when they first join so that it cannot be null.
	 */
	public TeleportService() {
		onLogin();
	}
	
	/**
	 * @param player Player to query for.
	 * @return Previous planet that this player was on.
	 */
	public Planet previousPlanet(Player player) {
		return lastLocations.get(player.getUniqueId()).planet();
	}
	
	/**
	 * @param location Location to teleport to.
	 * @param player Player to teleport.
	 */
	public void sendTo(LocationWrapper location, Player player) {
		lastLocations.put(player.getUniqueId(), new LocationWrapper(player.getLocation()));
		player.teleport(location);
	}
	
	/**
	 * Sends the player to their previous location.
	 * 
	 * @param player Player to teleport.
	 */
	public void sendBack(Player player) {
		LocationWrapper location = lastLocations.get(player.getUniqueId());
		
		if(location == null) {
			Backend.instance().log.notify("Previous location null for '" + player.getName() + "'!", true);
		}else sendTo(location, player);
	}
	
	private void onLogin() {
		Backend.instance().event.register((parameters) -> {
			Player player = parameters.player();
			
			sendTo(new LocationWrapper(player.getLocation()), player);
		}, EventType.PLAYER_JOIN);
	}

}