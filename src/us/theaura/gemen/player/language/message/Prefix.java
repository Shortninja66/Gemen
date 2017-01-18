package us.theaura.gemen.player.language.message;

import java.util.EnumMap;
import java.util.Map;

import us.theaura.gemen.player.language.LanguageCategory;
import us.theaura.gemen.player.language.Locale;

/**
 * Enum for all prefix phrases.
 * 
 * @since 14 January 2017 9:39 PM
 * @author Shortninja
 */

public enum Prefix implements LanguageCategory {

	BLANK, THE_AURA, INFORMATION, PERKS, PLANETS, MOON, PLOTS, STAFF, RESTRICTED, ERROR;
	
	private static final String IDENTIFIER = "prefixes";
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