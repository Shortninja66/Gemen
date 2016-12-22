package us.theaura.gemen.player.profile.data.entry;

/**
 * Extended entry of data that is still represented by a list of strings, but
 * allows only two values at a time; one as the key and one as a value.
 * 
 * @since 24 September 2016 8:52 PM
 * @author Shortninja
 */

public class PairDataEntry extends DataEntry
{
	/**
	 * Instantiation defaults key to "key" and value to "value" for null safety.
	 */
	public PairDataEntry()
	{
		setKey("key");
		setValue("value");
	}
	
	/**
	 * @return The zeroth-index value as a boolean.
	 */
	public String key()
	{
		return value(0);
	}
	
	/**
	 * @return The first-index value as a String.
	 */
	public String value()
	{
		return value(1);
	}
	
	/**
	 * @param key String to set as the key.
	 * @return Current instance.
	 */
	public PairDataEntry setKey(String key)
	{
		values.set(0, key);
		
		return this;
	}
	
	/**
	 * @param value String to set as the key's value.
	 * @return Current instance.
	 */
	public PairDataEntry setValue(String value)
	{
		values.set(1, value);
		
		return this;
	}
}