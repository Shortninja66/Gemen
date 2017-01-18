package us.theaura.gemen.server.sync;

/**
 * Enum for getting default task delays in the tasks manager.
 * 
 * @since 13 January 2017 5:38 PM
 * @author Shortninja
 */

public enum TaskDelay {

	SLOW(1800), MODERATE(900), FAST(40);
	
	private int identifier = ordinal();
	private int delay;
	
	TaskDelay(int delay) {
		this.delay = delay;
	}
	
	/**
	 * @return Default ID of this task speed. 
	 */
	public int identifier() {
		return identifier;
	}
	
	/**
	 * @return Delay for this task type.
	 */
	public int delay() {
		return delay;
	}
	
}