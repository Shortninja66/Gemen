package us.theaura.gemen.player.profile.data;

import java.util.List;
import java.util.UUID;

import us.theaura.gemen.player.profile.data.entry.BooleanDataEntry;
import us.theaura.gemen.player.profile.data.entry.DataEntry;

/**
 * Object class for containing abstract data that can be passed from other modules
 * and interpreted when saving occurs. User data is similar to how NBT works in
 * Minecraft; basically assigning abstract data to an object.
 * 
 * @since 28 August 2016 5:13 AM
 * @author Shortninja
 */

public class UserData
{
	public static final int MAX = UserKey.values().length - 1;
	private UUID uuid;
	private String stringUuid;
	private DataEntry[] data = new DataEntry[MAX];
	private boolean hasUpdated = true;
	
	public UserData(UUID uuid)
	{
		this.uuid = uuid;
		stringUuid = uuid.toString();
	}
	
	/**
	 * @return Holding player's UUID.
	 */
	public UUID uuid()
	{
		return uuid;
	}
	
	/**
	 * @param index Index to search data array for.
	 * @return Found value; may be null.
	 */
	public DataEntry value(int index)
	{
		return data[index];
	}
	
	/**
	 * @return Whether or not the data array has been set to changed.
	 */
	public boolean hasUpdated()
	{
		return hasUpdated;
	}
	
	/**
	 * @param index Index to validate.
	 * @return Whether or not the given index is possible to use with the data array.
	 */
	public boolean checkValidity(int index)
	{
		boolean isValid = index < 0 || index >= MAX;
		
		if(!isValid)
		{
			
		}
		
		return isValid;
	}
	
	/**
	 * @param hasUpdated Whether or not the data array should be set as changed.
	 */
	public void setUpdated(boolean hasUpdated)
	{
		this.hasUpdated = hasUpdated;
	}
	
	/**
	 * @param index Index to update.
	 * @param value String value to set.
	 */
	public void add(int index, String value)
	{
//		if(JavaUtils.isInteger(value))
//		{
//			data[index] = new NumberDataEntry().setValue(Double.valueOf(value));
//		}else data[index] = new StringDataEntry().setValue(value);
		
		update(index, value);
	}
	
	/**
	 * @param index Index to update.
	 * @param value Boolean value to set.
	 */
	public void add(int index, boolean value)
	{
		data[index] = new BooleanDataEntry().setValue(value);
		update(index, value);
	}
	
	/**
	 * @param index Index to update.
	 * @param value List<String> value to set.
	 */
	public void add(int index, List<String> values)
	{
		StringBuilder builder = new StringBuilder().append("| ");
		
		for(String string : values)
		{
			builder.append(string + " | ");
		}
		
		data[index] = new DataEntry().setValues(values);
		update(index, builder.toString().trim());
	}
	
	/**
	 * Performs any needed operation whenever a data node is modified.
	 * 
	 * @param index Index that was updated.
	 * @param value Object value that was set.
	 */
	private void update(int index, Object value)
	{
		setUpdated(true);
	}
}