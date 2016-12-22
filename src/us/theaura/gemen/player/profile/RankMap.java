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

public enum RankMap
{
	SUPPORTER("Supporter"), CONTRIBUTOR("Contributor"), ENTHUSIAST("Enthusiast"),
	INTERN("Intern"), MODERATOR("Moderator"), ADMINISTRATOR("Administrator"),
	BUILDER("Builder"), TESTER("Tester"), NOTABLE("Notable");
	
	private static Map<UUID, RankMap> ranks = new HashMap<UUID, RankMap>();
	private String identifier;
	private UUID uuid;
	
	RankMap(String identifier)
	{
		this.identifier = identifier;
		uuid = UUID.nameUUIDFromBytes(name().getBytes());
	}
	
	public static RankMap rank(UUID uuid)
	{
		return ranks.get(uuid);
	}
	
	public String identifier()
	{
		return identifier;
	}
	
	public UUID uuid()
	{
		return uuid;
	}
	
	static
	{
		for(RankMap rank : values())
		{
			ranks.put(rank.uuid(), rank);
		}
	}
}