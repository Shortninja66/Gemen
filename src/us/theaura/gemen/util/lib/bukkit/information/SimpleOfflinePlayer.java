/**
 * Copyright 2016 Alexander Maxwell
 * Use and or redistribution of compiled JAR file and or source code is permitted 
 * only if given explicit permission from original author: Alexander Maxwell
 */
package us.theaura.gemen.util.lib.bukkit.information;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import us.theaura.gemen.Backend;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * More efficient implementation of offline players than Bukkit's. Recently redone
 * with JSON instead of YAML.
 * 
 * @since 1 May 2016 9:52 PM
 * @author Alexander Maxwell, Shortninja
 */

public class SimpleOfflinePlayer
{
	private static Set<SimpleOfflinePlayer> offlinePlayers = new HashSet<>();
	private String name;
	private UUID uuid;

	public SimpleOfflinePlayer(String name, UUID uuid)
	{
		this.name = name;
		this.uuid = uuid;
		
		if(getByUuid(uuid) == null)
		{
			offlinePlayers.add(this);
		}else if(!getByName(name).equals(name))
		{
			offlinePlayers.add(this);
		}
	}

	public SimpleOfflinePlayer(Player player)
	{
		this(player.getName(), player.getUniqueId());
	}

	public static void save(JavaPlugin main)
	{
		if(!(offlinePlayers.isEmpty()))
		{
			File file = new File(main.getDataFolder(), "offline.json");

			if(!(file.exists()))
			{
				try
				{
					file.createNewFile();
				}catch(IOException exception)
				{
					
				}
			}

			ThunderFile configuration = Backend.instance().data.load("offline", "");
			
			for(SimpleOfflinePlayer offlinePlayer : offlinePlayers)
			{
				configuration.set(offlinePlayer.getUuid().toString() + ".name", offlinePlayer.getName());
			}
			
			Backend.instance().data.save(configuration);
		}
	}

	public static void load(JavaPlugin main)
	{
		File file = new File(main.getDataFolder(), "offline.json");

		if(!(file.exists()))
		{
			try
			{
				file.createNewFile();
			}catch(IOException exception)
			{
				
			}
		}

		ThunderFile configuration = Backend.instance().data.load("offline", "");

		for(String key : configuration.keySet())
		{
			String name = configuration.getString(key + ".name");
			getOfflinePlayers().add(new SimpleOfflinePlayer(name, UUID.fromString(key)));
		}
	}

	public static SimpleOfflinePlayer getByUuid(UUID uuid)
	{
		for(SimpleOfflinePlayer offlinePlayer : getOfflinePlayers())
		{
			if(offlinePlayer.getUuid().equals(uuid))
			{
				return offlinePlayer;
			}
		}
		
		return null;
	}

	public static SimpleOfflinePlayer getByName(String name)
	{
		for(SimpleOfflinePlayer offlinePlayer : getOfflinePlayers())
		{
			if(offlinePlayer.getName().equals(name))
			{
				return offlinePlayer;
			}
		}
		
		return null;
	}

	public static Set<SimpleOfflinePlayer> getOfflinePlayers()
	{
		return offlinePlayers;
	}
	
	public String getName()
	{
		return name;
	}
	
	public UUID getUuid()
	{
		return uuid;
	}
}