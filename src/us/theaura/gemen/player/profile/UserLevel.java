package us.theaura.gemen.player.profile;

import java.util.UUID;

/**
 * Object for handling and storing user level information.
 * 
 * @since 10 September 2016 5:45 PM
 * @author Shortninja
 */

public class UserLevel
{
	public static final int START_EXPERIENCE = 50000;
	public static final int EXPERIENCE_INCREMENT = 125000;
	public static final int LEVEL_MAX = 50;
	private UUID uuid;
	private int level;
	private long start;
	private long end;
	
	public UserLevel(UUID uuid, int level, long start, long end)
	{
		this.uuid = uuid;
		this.level = level;
		this.start = start;
		this.end = end;
	}
	
	public UserLevel(UUID uuid)
	{
		this(uuid, 1, 0, START_EXPERIENCE);
	}
	
	public UUID uuid()
	{
		return uuid;
	}
	
	public int current()
	{
		return level;
	}
	
	public long start()
	{
		return start;
	}
	
	public long end()
	{
		return end;
	}
	
	public void updateLevel(int level)
	{
		if(Math.abs(this.level + level) > LEVEL_MAX)
		{
			return;
		}
		
		this.level += level;
		setExperience(this.level);
	}
	
	public void updateExperience(long amount)
	{
		this.start += amount;
		
		if(start >= end)
		{
			updateLevel(1);
		}
	}
	
	private void setExperience(int level)
	{
		start = 0;
		end = START_EXPERIENCE + (EXPERIENCE_INCREMENT * level);
	}
}