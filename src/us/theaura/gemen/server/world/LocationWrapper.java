package us.theaura.gemen.server.world;

import org.bukkit.Location;

/**
 * Data object wrapper for Bukkit's Location.
 * 
 * @since 14 January 2017 6:50 PM
 * @author Shortninja
 */

public class LocationWrapper extends Location {

	private Planet planet;
	
	/**
	 * Constructor for when you know the current planet.
	 */
	public LocationWrapper(Planet planet, Location location) {
		super(planet.world(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		this.planet = planet;
	}
	
	/**
	 * @param location Constructor when the current planet is not known. Don't use moons!
	 */
	public LocationWrapper(Location location) {
		this(Planet.current(location.getWorld()), location);
	}
	
	/**
	 * @return The associated planet for this location.
	 */
	public Planet planet() {
		return planet;
	}
	
}