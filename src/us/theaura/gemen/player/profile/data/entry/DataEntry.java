package us.theaura.gemen.player.profile.data.entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entry of data that is represented by a list of strings. This is mainly used to
 * attach data to players with UserData, but it is also used to contain a set
 * of information and easily transport it. One example of this can be seen as a
 * way to transfer pairs of data, a key and a value, to MongoDB data calls.
 * 
 * @since 28 August 2016 5:26 AM
 * @author Shortninja
 */

public class DataEntry
{
	protected List<String> values = new ArrayList<String>();
	
	/**
	 * @return All values as an unmodifiable List.
	 */
	public List<String> values()
	{
		return Collections.unmodifiableList(values);
	}
	
	/**
	 * @param index Index to search values List for.
	 * @return Found value; may be null.
	 */
	public String value(int index)
	{
		return values.get(index);
	}
	
	/**
	 * @param values New List of values.
	 * @return Current instance.
	 */
	public DataEntry setValues(List<String> values)
	{
		this.values = values;
		
		return this;
	}
	
	/**
	 * @param value String to add to values List.
	 * @return Current instance.
	 */
	public DataEntry addValue(String value)
	{
		values.add(value);
		
		return this;
	}
}