package us.theaura.gemen;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Serves as the Gemen's main class as well as the accessor for other modules.
 * 
 * TODO: Document all methods.
 * 
 * @since December 21 2016 9:35 PM
 * @author Shortninja
 */

public class TheAura extends JavaPlugin
{
	private static TheAura plugin;
	private Backend backend = Backend.instance();
	
	@Override
	public void onEnable()
	{
		plugin = this;
	}

	@Override
	public void onDisable()
	{
		plugin = null;
	}
	
	public static TheAura instance()
	{
		return plugin;
	}
}