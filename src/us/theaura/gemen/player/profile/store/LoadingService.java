package us.theaura.gemen.player.profile.store;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.UserController;
import us.theaura.gemen.player.language.LanguageService;
import us.theaura.gemen.player.language.Locale;
import us.theaura.gemen.player.language.message.Message;
import us.theaura.gemen.player.language.message.Prefix;
import us.theaura.gemen.player.profile.RankMap;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.player.profile.UserLevel;
import us.theaura.gemen.player.profile.UserMode;
import us.theaura.gemen.player.profile.data.UserData;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.util.lib.bukkit.information.SimpleOfflinePlayer;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Service for simply loading a user's data.
 * 
 * @since 15 January 2017 6:27 PM
 * @author Shortninja
 */

public class LoadingService {

	private LanguageService language = Backend.instance().language;
	private UserController user = Backend.instance().user;
	
	public void attemptLoad(Player player, boolean hasJoined) {
		if(!hasJoined) {
			User user = new User(player.getUniqueId(), player.getName(), language.getLocale(player));
			
			this.user.add(user);
			new SimpleOfflinePlayer(player);
			language.send(user, Prefix.THE_AURA, Message.WELCOME, player.getName());
		}else load(player.getUniqueId());
	}
	
	private void load(UUID uuid) {
		ThunderFile file = Backend.instance().data.load(uuid.toString(), "users");
		List<String> names = file.getStringList("names");
		Locale locale = Locale.valueOf(file.getString("locale"));
		UserLevel level = deserializeLevel(file.getString("level"));
		UserMode mode = UserMode.valueOf(file.getString("mode"));
		Set<RankMap> ranks = deserializeRanks(file.getString("ranks"));
		UserData data = new UserData(uuid);
		boolean hasData = false;
		
		for(String key : file.keySet()) {
			if(!NODES.contains(key)) {
				Object value = file.get(key);
				int index = UserKey.matchKey(key).index();

				hasData = true;
				
				if(value instanceof List<?>)
				{
					data.put(index, (List<String>) value);
				}else data.put(index, value.toString());
			} 
		}
		
		Backend.instance().nameHistory.logHistory(uuid, names);
		user.add(new User(uuid, names.get(0), locale, level, mode, ranks, data, hasData));
	}
	
	private UserLevel deserializeLevel(String string) {
		String[] parts = string.split(";");
		
		return new UserLevel(Integer.valueOf(parts[0]), Long.valueOf(parts[1]), Long.valueOf(parts[1]));
	}
	
	private Set<RankMap> deserializeRanks(String string) {
		Set<RankMap> ranks = new HashSet<RankMap>();
		
		if(!string.isEmpty()) {
			for(String uuid : string.split(";")) {
				ranks.add(RankMap.rank(UUID.fromString(uuid)));
			}
		}
		
		return ranks;
	}
	
	private static final Set<String> NODES = new HashSet<String>(Arrays.asList (
		"uuid", "names", "locale", "level", "mode", "ranks"
	));
	
}