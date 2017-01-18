package us.theaura.gemen.player.language;

import us.theaura.gemen.server.Option;

/**
 * Interface for message enums.
 * 
 * @since 14 January 2017 8:39 PM
 * @author Shortninja
 */

public interface LanguageCategory {

	/**
	 * @param message Custom text to display
	 * @return Temporary language category with the given message.
	 */
	public static LanguageCategory create(String message) {
		return new LanguageCategory() {
			@Override
			public String message(Locale locale) {
				return Option.PRIMARY.getValue() + message;
			}

			@Override
			public void setMessage(Locale locale, String message) {}
		};
	}
	
	/**
	 * @param locale Locale to query for.
	 * @return Message for this locale; may be null if not loaded.
	 */
	public String message(Locale locale);
	
	/**
	 * @param locale Locale of this message.
	 * @param message Message to associate with this locale.
	 */
	public void setMessage(Locale locale, String message);
	
}