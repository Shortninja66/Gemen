package us.theaura.gemen.server.world;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.util.lib.bukkit.information.Serializer;

/**
 * Enum for managing all moons.
 * 
 * @since 13 January 2017 6:14 PM
 * @author Shortninja
 */

public enum Moon {

	LUNA("Luna", 1, "60.5;64.0;73.5;-2.3;-175.7"),
	CALLISTO("Callisto", 30, "270.5;78.0;621.5;-0.3;-1.7");
	
	private World world;
	private int minimumLevel;
	private String spawnString;
	private LocationWrapper spawn;
	
	Moon(String worldName, int minimumLevel, String spawnString) {
		this.world = Bukkit.getWorld(worldName);
		this.minimumLevel = minimumLevel;
		this.spawnString = spawnString;
	}
	
	/**
	 * @return All available moons in a list.
	 */
	public static List<Moon> moons() {
		return Arrays.asList(values());
	}
	
	/**
	 * @return World associated with this moon.
	 */
	public World world()
	{
		return world;
	}

	/**
	 * @return Minimum level to access this moon.
	 */
	public int minimumLevel() {
		return minimumLevel;
	}
	
	/**
	 * @param player Player to teleport to this moon's spawn.
	 */
	public void toSpawn(Player player) {
		if(spawn == null) {
			spawn = new LocationWrapper(Serializer.deserializeFullLocation(world, spawnString));
		}
		
		Backend.INSTANCE.teleport.sendTo(spawn, player);
	}
	
	/**
	 * Teleports all players from the moon and back to their original location.
	 */
	public void clearPlayers() {
		
	}
}