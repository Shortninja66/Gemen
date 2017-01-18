package us.theaura.gemen.server.event.observe;

/**
 * Simple interface for classes that listen to an observatory event.
 * 
 * @since 15 January 2017 9:14 PM
 * @author Shortninja
 */

public interface Observer {

	/**
	 * @return Event being listened to.
	 */
	public Observable observing();
	
	/**
	 * Called when the listened action has occurred.
	 */
	public void update(String key, String value);
	
}