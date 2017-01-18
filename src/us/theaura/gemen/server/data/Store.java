package us.theaura.gemen.server.data;

/**
 * Simple enum for storing data values. "Lazily" initialized.
 * 
 * Protocols:
 *   4  @ 1.7.1 - 1.7.5
 *   5  @ 1.7.6 - 1.7.10
 *   47 @ 1.8 - 1.8.9
 * 
 * @since 17 January 2017 11:44 PM
 * @author Shortninja
 */

public enum Store {
	UNIQUE_JOINS, MOST_CONCURRENT, PROTOCOL_4, PROTOCOL_5, PROTOCOL_47, PROTOCOL_OTHER;
	
	private int amount = 0;
	
	/**
	 * @return Amount associated with this field.
	 */
	public int amount() {
		return amount;
	}
	
	/**
	 * @param amount Amount to increment by
	 */
	public void increment(int amount) {
		this.amount += amount;
	}
}