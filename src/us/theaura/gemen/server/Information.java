package us.theaura.gemen.server;

import org.bukkit.plugin.PluginDescriptionFile;

/**
 * All information about the server; "lazy" initialization is required.
 * 
 * @since 15 January 2017 7:39 PM
 * @author Shortninja
 */

public enum Information {
	
	PREFIX, PLUGIN_VERSION, SERVER_VERSION, AUTHOR, WEBSITE, LINES;
	
	private String content;
	
	public static void initialize(PluginDescriptionFile plugin) {
		PREFIX.content = plugin.getPrefix();
		PLUGIN_VERSION.content = plugin.getVersion();
		SERVER_VERSION.content = "v1_7_R4";
		AUTHOR.content = plugin.getAuthors().get(0);
		WEBSITE.content = plugin.getWebsite();
		LINES.content = "8,722";
	}
	
	public String content() {
		return content;
	}
	
}