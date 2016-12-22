package us.theaura.gemen.player.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.theaura.gemen.player.profile.data.UserData;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.util.JavaUtils;

/**
 * Object for compacting all user information.
 * 
 * @since 21 December 2016 9:41 PM
 * @author Shortninja
 */

public class User
{
	private UUID uuid;
	private String name;
	private UserLevel level;
	private UserMode mode;
	private Set<RankMap> ranks;
	private UserData data;
	private boolean isOnline = true;
	
	public User(UUID uuid, String name, UserLevel level, UserMode mode, Set<RankMap> ranks, UserData data)
	{
		this.uuid = uuid;
		this.name = name;
		this.level = level;
		this.mode = mode;
		this.ranks = ranks;
		this.data = data;
	}
	
	public User(UUID uuid, String name)
	{
		this(uuid, name, new UserLevel(uuid), UserMode.EASY, new HashSet<RankMap>(), new UserData(uuid));
		initialize();
	}
	
	public Player player()
	{
		return Bukkit.getPlayer(uuid);
	}
	
	public UUID uuid()
	{
		return uuid;
	}
	
	public String name()
	{
		return name;
	}
	
	public UserLevel level()
	{
		return level;
	}
	
	public Set<RankMap> ranks()
	{
		return ranks;
	}
	
	public UserData data()
	{
		return data;
	}
	
	public UserMode mode()
	{
		return mode;
	}
	
	public boolean hasRank(RankMap rank)
	{
		return ranks.contains(rank);
	}
	
	public boolean isOnline()
	{
		return isOnline;
	}
	
	public void setMode(UserMode mode)
	{
		this.mode = mode;
	}
	
	public void setOnline(boolean isOnline)
	{
		this.isOnline = isOnline;
	}
	
	public void addRank(RankMap rank)
	{
		ranks.add(rank);
	}
	
	public void removeRank(RankMap rank)
	{
		ranks.remove(rank);
	}
	
	private void initialize()
	{
		long now = System.currentTimeMillis();
		
		data.add(UserKey.JOIN_TIME.get(), Long.toString(now));
		data.add(UserKey.JOIN_DATE.get(), JavaUtils.getDate(now));
		data.add(UserKey.UNIQUE_JOIN.get(), Integer.toString(1));
		data.add(UserKey.PREFIX.get(), "");
		data.add(UserKey.NICK.get(), "&7");
	}
}