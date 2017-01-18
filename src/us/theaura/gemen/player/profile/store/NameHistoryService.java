package us.theaura.gemen.player.profile.store;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.server.event.attribute.EventType;
import us.theaura.gemen.server.event.observe.Observable;

/**
 * Data store for player's usernames.
 * 
 * @since 15 January 2017 6:32 PM
 * @author Shortninja
 */

public class NameHistoryService {

	private static Map<UUID, List<String>> names = new HashMap<UUID, List<String>>();
	
	/*
	 * @PlayerJoinEvent
	 * 		Checks and, if needed, updates the player's username.
	 */
	public NameHistoryService() {
		onLogin();
	}
	
	/**
	 * @param uuid UUID to query for.
	 * @return List of all of this player's previous usernames. Zeroth-index is latest username.
	 */
	public List<String> history(UUID uuid) {
		return names.get(uuid);
	}
	
	/**
	 * @param uuid UUID to query for.
	 * @return Most recent, or current, username for this user.
	 */
	public String latestName(UUID uuid) {
		return history(uuid).get(0);
	}
	
	/**
	 * @param uuid UUID to query for
	 * @return Whether or not the UUID is new to the system.
	 */
	public boolean isNew(UUID uuid) {
		return history(uuid) == null;
	}
	
	/**
	 * @param player Player to query for and, if needed, to get name from.
	 * @return Whether or not the player's name is new to the system.
	 */
	public boolean hasChanged(Player player) {
		List<String> history = history(player.getUniqueId());
		
		return history == null || !history.get(0).equals(player.getName());
	}
	
	/**
	 * @param uuid UUID to query for.
	 * @param currentName Name to update to.
	 */
	public void update(UUID uuid, String currentName) {
		if(names.containsKey(uuid)) {
			names.get(uuid).add(currentName);
		}else names.put(uuid, Arrays.asList(currentName));
	}
	
	/**
	 * Used when a user's name history is loaded and can now be transferred to this service.
	 * 
	 * @param uuid UUID to query for.
	 * @param history List of names to set.
	 */
	public void logHistory(UUID uuid, List<String> history) {
		names.put(uuid, history);
	}
	
	private void onLogin() {
		Backend.instance().event.register((parameters) -> {
			Player player = parameters.player();
			UUID uuid = player.getUniqueId();
			String currentName = player.getName();
			
			if(isNew(uuid)) {
				update(uuid, currentName);
			}else if(hasChanged(player)) {
				Backend.instance().user.get(uuid).setName(player.getName());
				Backend.instance().observatory.call(Observable.NAME_CHANGE, latestName(uuid), currentName);
				update(uuid, currentName);
			}
		}, EventType.PLAYER_JOIN);
	}
	
}