package us.theaura.gemen.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.language.Locale;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.server.event.attribute.EventType;
import us.theaura.gemen.util.lib.bukkit.information.SimpleOfflinePlayer;

/**
 * Class that manages all functions regarding user's and their instances.
 * 
 * @since December 2016 9:40 PM
 * @author Shortninja
 */

public class UserController {
	
	private static Map<UUID, User> users = new HashMap<UUID, User>();

	/*
	 * @PlayerJoinEvent 
	 * 		Registers the user or loads them depending on if they have joined before.
	 */
	public UserController() {
		onLogin();
	}
	
	public Collection<User> getAll() {
		return users.values();
	}
	
	/**
	 * @param uuid UUID to query for.
	 * @return User associated with the given UUID; may be null.
	 */
	public User get(UUID uuid) {
		User user = users.get(uuid);

		if(user == null) {
			user = generate(uuid);
			Backend.instance().log.warn("Temporary user generated for '" + uuid.toString() + "'!", true);
		}

		return user;
	}

	/**
	 * @param uuid UUID to generate for.
	 * @return A new temporary user with "null" as the username.
	 */
	public User generate(UUID uuid) {
		return new User(uuid, "null", Locale.ENGLISH);
	}

	/**
	 * @param uuid UUID to query for.
	 * @return Whether or not the given UUID exists in the user map.
	 */
	public boolean exists(UUID uuid) {
		return users.containsKey(uuid);
	}
	
	/**
	 * Uses SimpleOfflinePlayer service to check if the player has joined the server before.
	 * 
	 * @param uuid UUID to check
	 * @return Whether or not the player has joined TheAura before.
	 */
	public boolean hasJoined(UUID uuid) {
		return SimpleOfflinePlayer.getByUuid(uuid) != null;
	}

	/**
	 * @param user User to add.
	 */
	public void add(User user) {
		users.put(user.uuid(), user);
	}

	/**
	 * @param uuid UUID of the user to remove.
	 */
	public void remove(UUID uuid) {
		users.remove(uuid);
	}
	
	private void onLogin() {
		Backend.instance().event.register((parameters) -> {
			Player player = parameters.player();
			UUID uuid = player.getUniqueId();
			
			if(exists(uuid)) {
				get(uuid).setOnline(true);
			}else Backend.instance().load.attemptLoad(player, hasJoined(uuid));
		}, EventType.PLAYER_JOIN);
	}
	
}