package us.theaura.gemen.player.profile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.gui.AbstractGui;
import us.theaura.gemen.player.language.Locale;
import us.theaura.gemen.player.profile.data.UserData;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Object for compacting all user information.
 * 
 * @since 21 December 2016 9:41 PM
 * @author Shortninja
 */

public class User {
	
	private UUID uuid;
	private String name;
	private Locale locale;
	private UserLevel level;
	private UserMode mode;
	private Set<RankMap> ranks;
	private UserData data;
	private ThunderFile file = null;
	private AbstractGui gui = null;
	private boolean isOnline = true;
	
	/**
	 * Constructor for existing users.
	 */
	public User(UUID uuid, String name, Locale locale, UserLevel level, UserMode mode, Set<RankMap> ranks, UserData data, boolean hasData) {
		this.uuid = uuid;
		this.name = name;
		this.locale = locale;
		this.level = level;
		this.mode = mode;
		this.ranks = ranks;
		this.data = data;
		
		file = Backend.instance().data.load(uuid.toString(), "users");
		
		if(!hasData) {
			initialize();
		}
	}
	
	/**
	 * Constructor for new users. Initializes data entries.
	 */
	public User(UUID uuid, String name, Locale locale) {
		this(uuid, name, locale, new UserLevel(), UserMode.EASY, new HashSet<RankMap>(), new UserData(uuid), false);
	}
	
	/**
	 * @return Player associated with this user.
	 */
	public Player player() {
		return Bukkit.getPlayer(uuid);
	}
	
	/**
	 * @return User's UUID.
	 */
	public UUID uuid() {
		return uuid;
	}
	
	/**
	 * @return User's most recent player name.
	 */
	public String name() {
		return name;
	}
	
	/**
	 * @return User's assumed or set locale; default to English.
	 */
	public Locale locale() {
		return locale;
	}
	
	/**
	 * @return User's level instance.
	 */
	public UserLevel level() {
		return level;
	}
	
	/**
	 * @return User's mode type.
	 */
	public UserMode mode() {
		return mode;
	}
	
	/**
	 * @return All ranks for this user.
	 */
	public Set<RankMap> ranks() {
		return Collections.unmodifiableSet(ranks);
	}
	
	/**
	 * @return User's data instance.
	 */
	public UserData data() {
		return data;
	}
	
	/**
	 * @return User's associated ThunderFile.
	 */
	public ThunderFile file() {
		return file;
	}
	
	/**
	 * @return User's current open GUI; may be null.
	 */
	public AbstractGui gui() {
		return gui;
	}
	
	/**
	 * @param rank The rank to query for.
	 * @return Whether or not this user has the given rank.
	 */
	public boolean hasRank(RankMap rank) {
		return ranks.contains(rank);
	}
	
	/**
	 * @return Whether or not the user is detected as online.
	 */
	public boolean isOnline() {
		return isOnline;
	}
	
	/**
	 * @param name Name to update to.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param locale Locale to set for this user.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * @param mode Mode to set to for this user.
	 */
	public void setMode(UserMode mode) {
		this.mode = mode;
	}
	
	/**
	 * @param gui Gui to set as current for this user.
	 */
	public void setGui(AbstractGui gui) {
		this.gui = gui;
	}
	
	/**
	 * @param isOnline Current online status for this user.
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	/**
	 * @param rank Rank to grant this user.
	 */
	public void addRank(RankMap rank) {
		ranks.add(rank);
	}
	
	/**
	 * @param rank Rank to revoke from this user.
	 */
	public void removeRank(RankMap rank) {
		ranks.remove(rank);
	}	
	
	private void initialize() {
		for(UserKey key : UserKey.values()) {
			data.put(key.index(), key.standard());
		}
	}
	
}