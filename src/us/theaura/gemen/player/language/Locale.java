package us.theaura.gemen.player.language;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Enum for all supported locales. Locales are taken from Minecraft's actual 
 * locales: http://minecraft.gamepedia.com/Language#Available_languages
 * 
 * @since 10 September 2016 7:35 PM
 * @author Shortninja
 */

public enum Locale {
	
	ENGLISH("en"), SWEDISH("sv");
	
	private static Set<String> values = new HashSet<String>();
	private String code;
	private FileConfiguration file = null;
	private String identifier;
	private String status;
	private String contributor;
	
	Locale(String code) {
		this.code = code;
	}
	
	/**
	 * @param code Locale code to query for.
	 * @return Locale associated with the given code; may be null.
	 */
	public static Locale get(String code)
	{
		Locale locale = null;
		
		for(Locale l : values()) {
			if(l.code().equalsIgnoreCase(code)) {
				locale = l;
				break;
			}
		}
		
		return locale;
	}
	
	/**
	 * @param code Locale code to query for.
	 * @return Whether or not the given code exists as a locale.
	 */
	public static boolean has(String code) {
		return values.contains(code);
	}
	
	/**
	 * @return The code for this locale.
	 */
	public String code() {
		return code;
	}
	
	/**
	 * @return File for this locale; may be null.
	 */
	public FileConfiguration file() {
		return file;
	}
	
	/**
	 * @return The ingame representation of this language's name.
	 */
	public String identifier() {
		return identifier;
	}
	
	/**
	 * @return The status of this language's translation.
	 */
	public String status() {
		return status;
	}
	
	/**
	 * @return The user that contributed to this language's translation.
	 */
	public String contributor () {
		return contributor;
	}
	
	/**
	 * Sets the language file and initializes all language information.
	 * 
	 * @param file File to set for this locale.
	 */
	public void setFile(FileConfiguration file) {
		this.file = file;
		identifier = file.getString("language");
		status = file.getString("status");
		contributor = file.getString("contributor");
	}
	
	static {
		for(Locale locale : values()) {
			values.add(locale.code());
		}
	}
	
}