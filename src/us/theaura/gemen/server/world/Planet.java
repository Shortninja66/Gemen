package us.theaura.gemen.server.world;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.util.JavaUtils;
import us.theaura.gemen.util.lib.bukkit.information.Serializer;

/**
 * Enum for managing all planets, has moons as children nodes.
 * 
 * TODO: Lang messages (ores and mobs)
 * 
 * @since 13 January 2017 5:53 PM
 * @author Shortninja
 */

public enum Planet {
	
	MERCURY("Mercury", 1, "Luna", "9.5;29.0;38.5;10.0;-359.6", "34.5;21.5;0.5;-8.7;-359.1"),
	VENUS("Venus", 10, "Luna", "-15.5;75.0;-5.5;12.2;-269.9", "2.5;64.0;-33.5;-0.3;-359.6"),
	MARS("Mars", 20, "Luna", "-17.5;95.0;-4.5;10.2;-268.8", "12.5;87.0;-28.5;1.1;-359.2"),
	IO("Io", 30, "Callisto", "-37.5;134.0;-36.5;10.8;-269.5", "-4.5;133.0;-42.5;7.2;27.8"),
	EUROPA("Europa", 40, "Callisto", "-24.5;69.0;-2.5;10.5;-268.8", "20.5;61.5;-22.5;-3.9;-0.1"),
	GANYMEDE("Ganymede", 50, "Callisto", "31.5;87.0;495.5;10.2;-269.1", "62.5;70.0;553.5;1.1;-180.0");
	
	private World world;
	private int minimumLevel;
	private Moon moon;
	private String spawnString;
	private String infirmaryString;
	private LocationWrapper spawn;
	private LocationWrapper infirmary;
	
	Planet(String worldName, int minimumLevel, String moon, String spawnString, String infirmaryString) {
		this.world = Bukkit.getWorld(worldName);
		this.minimumLevel = minimumLevel;
		this.spawnString = spawnString;
		this.infirmaryString = infirmaryString;
		this.moon = Moon.valueOf(moon.toUpperCase());
	}
	
	/**
	 * @return All available planets in a list.
	 */
	public static List<Planet> planets() {
		return Arrays.asList(values());
	}
	
	/**
	 * @param player World to check for.
	 * @return Planet that the given world is identified as; may be null.
	 */
	public static Planet current(World world) {
		String worldName = world.getName().toUpperCase();
		Planet planet = null;
		
		if(!JavaUtils.isValidEnum(Planet.class, worldName)) {
			Backend.instance().log.warn("Illegal world '" + world.getName() + "' exists!", true);
		}else planet = Planet.valueOf(worldName);
		
		return planet;
	}
	
	/**
	 * @return World associated with this planet.
	 */
	public World world() {
		return world;
	}

	/**
	 * @return Minimum level to access this planet.
	 */
	public int minimumLevel() {
		return minimumLevel;
	}
	
	/**
	 * @return Moon associated with this planet.
	 */
	public Moon moon() {
		return moon;
	}
	
	/**
	 * @param player Player to send to this planet's spawn.
	 */
	public void toSpawn(Player player) {
		if(spawn == null) {
			spawn = new LocationWrapper(Serializer.deserializeFullLocation(world, spawnString));
		}
		
		Backend.INSTANCE.teleport.sendTo(spawn, player);
	}
	
	/**
	 * @param player Player to send to this planet's infirmary.
	 */
	public void toInfirmary(Player player) {
		if(infirmary == null) {
			infirmary = new LocationWrapper(Serializer.deserializeFullLocation(world, infirmaryString));
		}
		
		Backend.INSTANCE.teleport.sendTo(infirmary, player);
	}
	
}