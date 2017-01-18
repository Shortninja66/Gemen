package us.theaura.gemen.player.language.message;

import java.util.EnumMap;
import java.util.Map;

import us.theaura.gemen.player.language.LanguageCategory;
import us.theaura.gemen.player.language.Locale;

/**
 * Enum for all general messages.
 * 
 * @since Jan 15, 2017 8:39:13 PM
 * @author Shortninja
 */

public enum Message implements LanguageCategory {

	WELCOME, NO_PERMISSION, INVALID_ARGUMENTS, ONLY_PLAYERS, INTERNAL_ERROR;
	
	private static final String IDENTIFIER = "messages";
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