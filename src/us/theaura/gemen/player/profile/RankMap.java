package us.theaura.gemen.player.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Simple enum to organize ranks and their according permissions.
 * 
 * (!) ENUM NAMES CANNOT CHANGE AFTER FIRST LOAD.
 * 
 * @since 18 December 2016 12:54 PM
 * @author Shortninja
 */

public enum RankMap {
	
	SUPPORTER("Supporter", "a"), CONTRIBUTOR("Contributor", "b"), ENTHUSIAST("Enthusiast", "6"),
	INTERN("Intern", "e"), MODERATOR("Moderator", "c"), ADMINISTRATOR("Administrator", "4"),
	BUILDER("Builder", "5"), TESTER("Tester", "9"), NOTABLE("Notable", "3");
	
	private static Map<UUID, RankMap> ranks = new HashMap<UUID, RankMap>();
	private String identifier;
	private String color;
	private UUID uuid;
	
	RankMap(String identifier, String color) {
		this.identifier = identifier;
		this.color = "&" + color;
		uuid = UUID.nameUUIDFromBytes(name().getBytes());
	}
	
	/**
	 * @param uuid UUID of the rank to query with.
	 * @return Rank associated with the given UUID; may be null. 
	 */
	public static RankMap rank(UUID uuid) {
		return ranks.get(uuid);
	}
	
	/**
	 * @return String that this rank is displayed as ingame.
	 */
	public String identifier() {
		return identifier;
	}
	
	/**
	 * @return Color code associated with this rank.
	 */
	public String color() {
		return color;
	}
	
	/**
	 * @return UUID of this rank.
	 */
	public UUID uuid() {
		return uuid;
	}
	
	static {
		for(RankMap rank : values()) {
			ranks.put(rank.uuid(), rank);
		}
	}
	
}