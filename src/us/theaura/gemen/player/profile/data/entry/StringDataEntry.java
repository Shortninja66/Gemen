package us.theaura.gemen.player.profile.data.entry;

/**
 * Extended entry of data that is still represented by a list of strings, but
 * only allows one value at a time.
 * 
 * @since 28 August 2016 5:30 AM
 * @author Shortninja
 */

public class StringDataEntry extends DataEntry
{
	/**
	 * @return The zeroth-index value as a String.
	 */
	public String value()
	{
		return value(0);
	}
	
	/**
	 * @param value String to set as the main value.
	 * @return Current instance.
	 */
	public StringDataEntry setValue(String value)
	{
		values.clear();
		addValue(value);
		
		return this;
	}
}