package us.theaura.gemen.server.event.attribute;

/**
 * Enum for pairing events with their relative identity in annotations.
 * 
 * @since 14 January 2017 8:09 PM
 * @author Shortninja
 */

public enum EventType {

	PLAYER_JOIN(0);
	
	private int identifier;
	
	EventType(int identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return ID of this event type.
	 */
	public int identifier() {
		return identifier;
	}
	
}