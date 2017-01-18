package us.theaura.gemen.util.lib.bukkit;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Useful Bukkit related methods from various sources.
 * 
 * @since 20 February 2016 at 5:49 AM
 * @author Shortninja, SteeZyyy, ...
 */

public class BukkitUtils {
	
	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static boolean hasInventorySpace(Player player) {
		return player.getInventory().firstEmpty() != -1;
	}

	public static String getLocale(Player player) {
		String language = "en";

		if(!(player instanceof CraftPlayer)) {
			return language;
		}

		String locale = ((CraftPlayer) player).getHandle().locale;

		return locale.substring(0, locale.lastIndexOf('_') + 1);
	}

	public static void lookAt(Player player, Location location) {
		Vector direction = player.getEyeLocation().toVector().subtract(location.toVector()).normalize();
		Location newLocation = player.getLocation().clone();

		newLocation.setYaw(180 - (float) Math.toDegrees(Math.atan2(direction.getX(), direction.getZ())));
		newLocation.setPitch(90 - (float) Math.toDegrees(Math.acos(direction.getY())));
		player.teleport(newLocation);
	}

	/**
	 * @param player
	 *            Player to check for direction.
	 * @return Gets target player by using all players within a radius of six
	 *         blocks from the given player.
	 */
	public static Player getTargetPlayer(Player player) {
		Player targetPlayer = null;
		Location playerPos = player.getEyeLocation();
		Vector3D playerDir = new Vector3D(playerPos.getDirection());
		Vector3D playerStart = new Vector3D(playerPos);
		Vector3D playerEnd = playerStart.add(playerDir.multiply(100));

		for(Player p : player.getWorld().getPlayers()) {
			Vector3D targetPos = new Vector3D(p.getLocation());
			Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
			Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

			if(p != player && hasIntersection(playerStart, playerEnd, minimum, maximum)) {
				if(targetPlayer == null || targetPlayer.getLocation().distanceSquared(playerPos) > p.getLocation().distanceSquared(playerPos)) {
					targetPlayer = p;
				}
			}
		}

		return targetPlayer;
	}

	/**
	 * @param p1
	 *            Vector to start with.
	 * @param p2
	 *            Vector the check for intersection with.
	 * @param min
	 *            Minimum hitbox vector.
	 * @param max
	 *            Maximum hitbox vector.
	 * @return Whether or not the two vectors intersect in 3D space.
	 */
	public static boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max) {
		final double epsilon = 0.0001f;
		Vector3D d = p2.subtract(p1).multiply(0.5);
		Vector3D e = max.subtract(min).multiply(0.5);
		Vector3D c = p1.add(d).subtract(min.add(max).multiply(0.5));
		Vector3D ad = d.abs();

		if(Math.abs(c.x) > e.x + ad.x)
			return false;
		if(Math.abs(c.y) > e.y + ad.y)
			return false;
		if(Math.abs(c.z) > e.z + ad.z)
			return false;

		if(Math.abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon)
			return false;
		if(Math.abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon)
			return false;
		if(Math.abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon)
			return false;

		return true;
	}

	public Vector rotate(Vector vector, Location location) {
		double pitchRadian = location.getPitch() / 180.0 * Math.PI;
		double yawRadian = location.getYaw() / 180.0 * Math.PI;

		vector = matrixX(vector, pitchRadian);
		vector = matrixY(vector, -yawRadian);

		return vector;
	}

	public Vector matrixX(Vector vector, double pitchRadian) {
		double y = Math.cos(pitchRadian) * vector.getY() - Math.sin(pitchRadian) * vector.getZ();
		double z = Math.sin(pitchRadian) * vector.getY() + Math.cos(pitchRadian) * vector.getZ();

		return vector.setY(y).setZ(z);
	}

	public Vector matrixY(Vector vector, double yawRadian) {
		double x = Math.cos(yawRadian) * vector.getX() - Math.sin(yawRadian) * vector.getZ();
		double z = -Math.sin(yawRadian) * vector.getX() + Math.cos(yawRadian) * vector.getZ();

		return vector.setX(x).setZ(z);
	}

	public Vector matrixZ(Vector vector, double radian) {
		double x = Math.cos(radian) * vector.getX() - Math.sin(radian) * vector.getY();
		double y = Math.sin(radian) * vector.getX() + Math.cos(radian) * vector.getY();

		return vector.setX(x).setY(y);
	}
	
	public static String dataColor(short data) {
		String color = "white";
		
		switch(data) {
			case 1:
				color = "orange";
				break;
			case 2:
				color = "magenta";
				break;
			case 3:
				color = "light blue";
				break;
			case 4:
				color = "yellow";
				break;
			case 5:
				color = "lime";
				break;
			case 6:
				color = "pink";
				break;
			case 7:
				color = "gray";
				break;
			case 8:
				color = "light gray";
				break;
			case 9:
				color = "cyan";
				break;
			case 10:
				color = "purple";
				break;
			case 11:
				color = "blue";
				break;
			case 12:
				color = "brown";
				break;
			case 13:
				color = "green";
				break;
			case 14:
				color = "red";
				break;
			case 15:
				color = "black";
				break;
		}
		
		return color;
	}

	public static Color numberColor(int value) {
		Color color = null;

		switch(value) {
			case 1:
			color = Color.AQUA;
				break;
			case 2:
			color = Color.BLACK;
				break;
			case 3:
			color = Color.BLUE;
				break;
			case 4:
			color = Color.FUCHSIA;
				break;
			case 5:
			color = Color.GRAY;
				break;
			case 6:
			color = Color.GREEN;
				break;
			case 7:
			color = Color.LIME;
				break;
			case 8:
			color = Color.MAROON;
				break;
			case 9:
			color = Color.NAVY;
				break;
			case 10:
			color = Color.OLIVE;
				break;
			case 11:
			color = Color.ORANGE;
				break;
			case 12:
			color = Color.PURPLE;
				break;
			case 13:
			color = Color.RED;
				break;
			case 14:
			color = Color.SILVER;
				break;
			case 15:
			color = Color.TEAL;
				break;
			case 16:
			color = Color.WHITE;
				break;
			case 17:
			color = Color.YELLOW;
				break;
		}

		return color;
	}
	
}