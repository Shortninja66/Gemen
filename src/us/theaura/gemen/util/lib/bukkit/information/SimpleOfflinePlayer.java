/**
 * Copyright 2016 Alexander Maxwell
 * Use and or redistribution of compiled JAR file and or source code is permitted 
 * only if given explicit permission from original author: Alexander Maxwell
 */
package us.theaura.gemen.util.lib.bukkit.information;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import us.theaura.gemen.Backend;
import us.theaura.gemen.server.event.observe.Observable;
import us.theaura.gemen.server.event.observe.Observer;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * More efficient implementation of offline players than Bukkit's. Recently
 * redone with JSON instead of YAML.
 * 
 * @since 1 May 2016 9:52 PM
 * @author Alexander Maxwell, Shortninja
 */

public class SimpleOfflinePlayer implements Observer {
	
	private static Set<SimpleOfflinePlayer> offlinePlayers = new HashSet<>();
	private String name;
	private UUID uuid;

	public SimpleOfflinePlayer(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;

		if(getByUuid(uuid) == null) {
			offlinePlayers.add(this);
		}else if(!getByName(name).equals(name)) {
			offlinePlayers.add(this);
		}
	}

	public SimpleOfflinePlayer(Player player) {
		this(player.getName(), player.getUniqueId());
	}

	public static void save(JavaPlugin main) {
		ThunderFile configuration = Backend.instance().data.load("offline", "data");

		for(SimpleOfflinePlayer offlinePlayer : offlinePlayers) {
			configuration.set(offlinePlayer.getUuid().toString(), offlinePlayer.getName());
		}

		Backend.instance().data.save(configuration);
	}

	public static void load(JavaPlugin main) {
		ThunderFile configuration = Backend.instance().data.load("offline", "data");

		for(String key : configuration.keySet()) {
			String name = configuration.getString(key);
			getOfflinePlayers().add(new SimpleOfflinePlayer(name, UUID.fromString(key)));
		}
	}

	public static SimpleOfflinePlayer getByUuid(UUID uuid) {
		for(SimpleOfflinePlayer offlinePlayer : getOfflinePlayers()) {
			if(offlinePlayer.getUuid().equals(uuid)) {
				return offlinePlayer;
			}
		}

		return null;
	}

	public static SimpleOfflinePlayer getByName(String name) {
		for(SimpleOfflinePlayer offlinePlayer : getOfflinePlayers()) {
			if(offlinePlayer.getName().equals(name)) {
				return offlinePlayer;
			}
		}

		return null;
	}

	public static Set<SimpleOfflinePlayer> getOfflinePlayers() {
		return offlinePlayers;
	}

	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Observable observing() {
		return Observable.NAME_CHANGE;
	}

	@Override
	public void update(String key, String value) {
		getByName(key).setName(value);
	}
	
}