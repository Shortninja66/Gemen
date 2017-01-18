package us.theaura.gemen.player.language.message;

import java.util.EnumMap;
import java.util.Map;

import us.theaura.gemen.player.language.LanguageCategory;
import us.theaura.gemen.player.language.Locale;

/**
 * Enum for all broadcast messages.
 * 
 * @since 15 January 2017 8:37 PM
 * @author Shortninja
 */

public enum Broadcast implements LanguageCategory {

	HELP;
	
	private static final String IDENTIFIER = "broadcasts";
	private Map<Locale, String> message = new EnumMap<Locale, String>(Locale.class);
	
	/**
	 * @return Node identifier for this language category.
	 */
	public static String identifier() {
		return IDENTIFIER;
	}
	
	@Override
	public String message(Locale locale) {
		return message.get(locale);
	}
	
	@Override
	public void setMessage(Locale locale, String message) {
		this.message.put(locale, message);
	}
	
}