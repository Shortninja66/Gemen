package us.theaura.gemen.server;

import java.io.IOException;
import java.util.UUID;

import us.theaura.gemen.TheAura;
import us.theaura.gemen.util.lib.bukkit.information.SimpleOfflinePlayer;
import us.theaura.gemen.util.lib.thunderbolt.Thunderbolt;
import us.theaura.gemen.util.lib.thunderbolt.exceptions.FileLoadException;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Class that manages all data-related functions from saving to loading.
 * 
 * @since 22 December 2016 1:18 PM
 * @author Shortninja
 */

public class DataController
{
	public boolean hasJoined(UUID uuid)
	{
		return SimpleOfflinePlayer.getByUuid(uuid) != null;
	}
	
	public ThunderFile load(String name, String path)
	{
		ThunderFile file = Thunderbolt.get(name);
		
		if(file == null)
		{
			try
			{
				file = Thunderbolt.load(name, TheAura.instance().getDataFolder().getAbsolutePath());
			}catch (FileLoadException | IOException exception)
			{
				file = Thunderbolt.get();
				// TODO: Log warning.
			}
		}
		
		return file;
	}
	
	public void save(ThunderFile file)
	{
		try
		{
			file.save();
		}catch (IOException exception)
		{
			// TODO: Log error.
		}
	}
}