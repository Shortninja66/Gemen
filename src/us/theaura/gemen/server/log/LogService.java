package us.theaura.gemen.server.log;

import org.bukkit.Bukkit;

import us.theaura.gemen.TheAura;
import us.theaura.gemen.server.Information;
import us.theaura.gemen.util.lib.bukkit.BukkitUtils;

/**
 * Class that handles all console logging.
 * 
 * @since 8 January 2017 10:40 PM
 * @author Shortninja
 */

public class LogService {
	
	private final String PREFIX;
	
	public LogService() {
		Information.initialize(TheAura.instance().getDescription());
		PREFIX = Information.PREFIX.content();
		notify(PREFIX + "v" + Information.PLUGIN_VERSION.content() + " is now enabling!", false); 
	}
	
	public void notify(String message, boolean isError) {
		String prefix = isError ? "&4[" + PREFIX + "] &c" : "&2[" + PREFIX + "] &a";
		
		sendToConsole(prefix + message);
	}
	
	public void warn(String message, boolean isSevere) {
		String prefix = isSevere ? "&6[" + PREFIX + "] &7" : "&e[" + PREFIX + "] &7";
		
		sendToConsole(prefix + message);
	}
	
	private void sendToConsole(String message) {
		Bukkit.getServer().getConsoleSender().sendMessage(BukkitUtils.colorize(message));
	}

}