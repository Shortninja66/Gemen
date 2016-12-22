package us.theaura.gemen.player.profile;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import us.theaura.gemen.util.lib.hex.Strings;

/**
 * Enum for all mode types and their attributes.
 * 
 * @since 10 September 2016 9:47 PM
 * @author Shortninja
 */

public enum UserMode
{
	EASY("easy", 1.0, 1.0, 0, 3600000, false,
		new String[]
		{
			Strings.prefix("Easy mode features:", "&b"),
			Strings.prefix("Locks in for %a%.", "&c"),
			Strings.prefix("- Disables PvP", "&7")
		}
	),
	
	NORMAL("normal", 1.0, 1.0, 0, 3600000, true,
		new String[]
		{
			Strings.prefix("Normal mode features:", "&b"),
			Strings.prefix("Locks in for %a%.", "&c"),
			Strings.prefix("- Enables PvP", "&7")
		}
	),
	
	HARDCORE("hardcore", 1.5, 0.80, 50000, 28800000, true,
		new String[]
		{
			Strings.prefix("Hardcore mode features:", "&b"),
			Strings.prefix("Locks in for %a%.", "&c"),
			Strings.prefix("- Boost shop sell prices, ", "&7"),
			Strings.prefix("- Lower oxygen supply maximum and lose experience on death", "&7")
		}
	),

	REALISM("hardcore", 1.5, 0.80, 50000, 28800000, true,
		new String[]
		{
	    	Strings.prefix("Realism mode features:", "&b"),
	    	Strings.prefix("Locks in for %a%.", "&c"),
	    	Strings.prefix("- Enables PvP, boosts shop sell prices", "&7"),
	    	Strings.prefix("- Lower oxygen supply and lose experience on death", "&7"),
	    	Strings.prefix("- Currently work in progress!", "&7")
		}
	);
	
	private static Map<String, UserMode> values = new HashMap<String, UserMode>();
	private String identifier;
	private double priceMultiplier;
	private double oxygenMultiplier;
	private long cooldown;
	private long experienceLoss;
	private boolean canAttack;
	private String[] info;
	
	UserMode(String identifier, double priceMultiplier, double oxygenMultiplier, long cooldown, long experienceLoss, boolean canAttack, String[] info)
	{
		this.identifier = identifier;
		this.priceMultiplier = priceMultiplier;
		this.oxygenMultiplier = oxygenMultiplier;
		this.cooldown = cooldown;
		this.experienceLoss = experienceLoss;
		this.canAttack = canAttack;
		this.info = info;
	}
	
	public static UserMode mode(String name)
	{
		return values.get(name);
	}
	
	public String identifier()
	{
		return identifier;
	}
	
	public double priceMultiplier()
	{
		return priceMultiplier;
	}
	
	public double oxygenMultiplier()
	{
		return oxygenMultiplier;
	}
	
	public long cooldown()
	{
		return cooldown;
	}
	
	public long experienceLoss()
	{
		return -experienceLoss;
	}
	
	public boolean attack()
	{
		return canAttack;
	}
	
	public void info(Player player)
	{
		
	}
	
	static
	{
		for(UserMode mode : values())
		{
			values.put(mode.name(), mode);
		}
	}
}