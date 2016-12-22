package us.theaura.gemen.player.profile.data.entry;

/**
 * Extended entry of data that is still represented by a list of strings, but
 * only allows one value at a time and uses doubles instead. Even integers 
 * will be represented as doubles.
 * 
 * @since 9 September 2016 2:16 AM
 * @author Shortninja
 */

public class NumberDataEntry extends DataEntry
{
	/**
	 * @return The zeroth-index value as a double.
	 */
	public double value()
	{
		return Double.parseDouble(value(0));
	}
	
	/**
	 * @param value Double to set as the main value.
	 * @return Current instance
	 */
	public NumberDataEntry setValue(double value)
	{
		values.clear();
		addValue(Double.toString(value));
		
		return this;
	}
}