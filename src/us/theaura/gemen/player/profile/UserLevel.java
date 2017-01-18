package us.theaura.gemen.player.profile;

/**
 * Object for handling and storing user level information.
 * 
 * @since 10 September 2016 5:45 PM
 * @author Shortninja
 */

public class UserLevel {
	
	public static final int START_EXPERIENCE = 50000;
	public static final int EXPERIENCE_INCREMENT = 100000;
	public static final int LEVEL_MAX = 50;
	private int level;
	private long start;
	private long end;
	
	/**
	 * Constructor for existing level instances.
	 */
	public UserLevel(int level, long start, long end) {
		this.level = level;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Constructor for new level instances.
	 */
	public UserLevel() {
		this(1, 0, START_EXPERIENCE);
	}
	
	/**
	 * @return Current level.
	 */
	public int current() {
		return level;
	}
	
	/**
	 * @return Current starting-end experience.
	 */
	public long start() {
		return start;
	}
	
	/**
	 * @return Current end-point experience.
	 */
	public long end() {
		return end;
	}
	
	/**
	 * @param level Levels to add to this instance; negative values allowed.
	 */
	public void updateLevel(int level) {
		if(Math.abs(this.level + level) > LEVEL_MAX) {
			return;
		}
		
		this.level += level;
		setExperience(this.level);
	}
	
	/**
	 * @param amount Experience to give to this instance.
	 */
	public void updateExperience(long amount) {
		this.start += amount;
		
		if(start >= end) {
			updateLevel(1);
		}
	}
	
	private void setExperience(int level) {
		start = 0;
		end = START_EXPERIENCE + (EXPERIENCE_INCREMENT * level);
	}
	
}