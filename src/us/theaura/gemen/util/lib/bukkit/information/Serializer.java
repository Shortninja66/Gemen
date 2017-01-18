package us.theaura.gemen.util.lib.bukkit.information;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Serializer class for many functional methods of serialization.
 * 
 * @since 28 May 2016 1:05 AM
 * @author Shortninja
 */

public class Serializer {
	
	public static String[] deserialize(String string) {
		return string.split(";");
	}

	public static String serializeLocation(Location location) {
		return location.getX() + ";" + location.getY() + ";" + location.getZ();
	}

	public static List<String> serializeLocations(Set<Location> locations) {
		List<String> serializedLocations = new ArrayList<String>();

		for(Location location : locations) {
			serializedLocations.add(serializeLocation(location));
		}

		return serializedLocations;
	}

	public static Location deserializeLocation(World world, String string) {
		String[] parts = string.split(";");

		return new Location(world, Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
	}

	public static Set<Location> deserializeLocations(World world, ArrayList<String> locations) {
		Set<Location> deserializedLocations = new HashSet<Location>();

		for(String object : locations) {
			deserializedLocations.add(deserializeLocation(world, object));
		}

		return deserializedLocations;
	}

	public static Location deserializeFullLocation(World world, String string) {
		String[] parts = string.split(";");

		return new Location(world, Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
	}

	public static String serializeChunk(Chunk chunk) {
		return chunk.getWorld().getName() + ";" + chunk.getX() + ";" + chunk.getZ();
	}

	public static Chunk deserializeChunk(String string) {
		String[] parts = string.split(";");

		return Bukkit.getWorld(parts[0]).getChunkAt(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
	}
	
}