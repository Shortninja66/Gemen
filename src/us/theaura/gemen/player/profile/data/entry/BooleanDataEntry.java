package us.theaura.gemen.player.profile.data.entry;

/**
 * Extended entry of data that is still represented by a list of strings, but
 * only allows one value at a time and uses booleans instead.
 * 
 * @since 9 September 2016 2:16 AM
 * @author Shortninja
 */

public class BooleanDataEntry extends DataEntry
{
	/**
	 * @return The zeroth-index value as a boolean.
	 */
	public boolean value()
	{
		return Boolean.parseBoolean(value(0));
	}
	
	/**
	 * @param value Boolean to set as the main value.
	 * @return Current instance.
	 */
	public BooleanDataEntry setValue(boolean value)
	{
		values.clear();
		addValue(Boolean.toString(value));
		
		return this;
	}
}