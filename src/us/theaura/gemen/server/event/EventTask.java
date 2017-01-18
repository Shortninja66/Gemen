package us.theaura.gemen.server.event;

import us.theaura.gemen.server.event.attribute.EventParameters;

/**
 * Class for passing abstract code in event busses.
 * 
 * @since 14 January 2017 8:23 PM
 * @author Shortninja
 */

public interface EventTask {

	/**
	 * @param parameters Event parameters to pass.
	 */
	public void call(EventParameters parameters);
	
}