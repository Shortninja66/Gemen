package us.theaura.gemen.player.profile.data;

import java.util.HashMap;
import java.util.Map;

import us.theaura.gemen.player.profile.data.entry.BooleanDataEntry;
import us.theaura.gemen.player.profile.data.entry.DataEntry;
import us.theaura.gemen.player.profile.data.entry.NumberDataEntry;
import us.theaura.gemen.player.profile.data.entry.StringDataEntry;
import us.theaura.gemen.util.JavaUtils;

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

public enum UserKey {
	
	JOIN_TIME("join-time") {
		@Override
		public String standard() {
			return Long.toString(System.currentTimeMillis());
		}
	},
	JOIN_DATE("join-date") {
		@Override
		public String standard() {
			return JavaUtils.getDate(System.currentTimeMillis());
		}
	},
	UNIQUE_JOIN("unique-join") {
		@Override
		public String standard() {
			return Integer.toString(1);
		}
	}, IGNORE_PACK("ignore-pack"),
	PREFIX("prefix") {
		@Override
		public String standard() {
			return "";
		}
	},
	NICK("nick") {
		@Override
		public String standard() {
			return "&7";
		}
	},
	NOTABILITY("notability") {
		@Override
		public String standard() {
			return "";
		}
	},
	GUI_COLOR("gui-color") {
		@Override
		public String standard() {
			return Integer.toString(9);
		}
	};
	
	private static Map<Integer, UserKey> indexMap = new HashMap<Integer, UserKey>();
	private static Map<String, UserKey> keyMap = new HashMap<String, UserKey>();
	private int index = ordinal();
	private String key;
	
	UserKey(String key) {
		this.key = key;
	}

	/**
	 * @param index Index to search indexMap for.
	 * @return UserKey associated with the given index; may be null.
	 */
	public static UserKey matchIndex(int index) {
		return indexMap.get(index);
	}
	
	/**
	 * @param key Index to search keyMap for.
	 * @return UserKey associated with the given String.
	 */
	public static UserKey matchKey(String key) {
		return keyMap.get(key);
	}
	
	/**
	 * @return Node used in data files and identification of this element.
	 */
	public String key() {
		return key;
	}
	
	/**
	 * @return Ordinal value of this element.
	 */
	public int index() {
		return index;
	}
	
	/**
	 * @return Default value of this key.
	 */
	public String standard() {
		return Boolean.toString(false);
	}
	
	/**
	 * @param data User data to check
	 * @return Entry as a DataEntry
	 */
	public DataEntry entry(UserData data) {
		return data.value(index);
	}
	
	/**
	 * @param data User data to check
	 * @return Entry as a BooleanDataEntry
	 */
	public BooleanDataEntry booleanEntry(UserData data) {
		return (BooleanDataEntry) data.value(index);
	}
	
	/**
	 * @param data User data to check
	 * @return Entry as a StringDataEntry
	 */
	public StringDataEntry stringEntry(UserData data) {
		return (StringDataEntry) data.value(index);
	}
	
	/**
	 * @param data User data to check
	 * @return Entry as a NumberDataEntry
	 */
	public NumberDataEntry numberEntry(UserData data) {
		return (NumberDataEntry) data.value(index);
	}
	
	static {
		for(UserKey userKey : values()) {
			indexMap.put(userKey.index(), userKey);
			keyMap.put(userKey.key(), userKey);
		}
	}
	
}