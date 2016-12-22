package us.theaura.gemen.player.profile.data;

import java.util.HashMap;
import java.util.Map;

import us.theaura.gemen.player.profile.data.entry.BooleanDataEntry;
import us.theaura.gemen.player.profile.data.entry.DataEntry;
import us.theaura.gemen.player.profile.data.entry.NumberDataEntry;
import us.theaura.gemen.player.profile.data.entry.StringDataEntry;

/**
 * Enum for storing default keys that are used in user data. This adds keys for 
 * pretty much all known modules since each element contains no direct correlation 
 * to a certain class or import.
 * 
 * (!) ENUM ORDER CANNOT CHANGE AFTER FIRST LOAD.
 * 
 * @since 10 September 2016 7:05 PM
 * @author Shortninja
 */

public enum UserKey
{
	JOIN_TIME("join-time"), JOIN_DATE("join-date"), UNIQUE_JOIN("unique-join"),
	IGNORE_PACK("ignore-pack"), PREFIX("prefix"), NICK("nick"), RANK("ranks"),
	NOTABILITY("notability");
	
	private static Map<Integer, UserKey> indexMap = new HashMap<Integer, UserKey>();
	private static Map<String, UserKey> keyMap = new HashMap<String, UserKey>();
	private int index = ordinal();
	private String key;
	
	UserKey(String key)
	{
		this.key = key;
	}

	/**
	 * @param index Index to search indexMap for.
	 * @return UserKey associated with the given index; may be null.
	 */
	public static UserKey matchIndex(int index)
	{
		return indexMap.get(index);
	}
	
	/**
	 * @param key Index to search keyMap for.
	 * @return UserKey associated with the given String.
	 */
	public static UserKey matchKey(String key)
	{
		return keyMap.get(key);
	}
	
	/**
	 * @return Node used in data files and identification of this element.
	 */
	public String key()
	{
		return key;
	}
	
	/**
	 * @return Ordinal value of this element.
	 */
	public int get()
	{
		return index;
	}
	
	public DataEntry entry(UserData data)
	{
		return data.value(index);
	}
	
	public BooleanDataEntry getBooleanEntry(UserData data)
	{
		return (BooleanDataEntry) data.value(index);
	}
	
	public StringDataEntry getStringEntry(UserData data)
	{
		return (StringDataEntry) data.value(index);
	}
	
	public NumberDataEntry getNumberEntry(UserData data)
	{
		return (NumberDataEntry) data.value(index);
	}
	
	static
	{
		for(UserKey userKey : values())
		{
			indexMap.put(userKey.get(), userKey);
			keyMap.put(userKey.key(), userKey);
		}
	}
}