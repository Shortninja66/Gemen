package us.theaura.gemen.player.profile.store;

import java.util.Set;
import java.util.UUID;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.profile.RankMap;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.player.profile.UserLevel;
import us.theaura.gemen.player.profile.data.UserData;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.player.profile.data.entry.BooleanDataEntry;
import us.theaura.gemen.player.profile.data.entry.DataEntry;
import us.theaura.gemen.player.profile.data.entry.NumberDataEntry;
import us.theaura.gemen.player.profile.data.entry.StringDataEntry;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Service for simply saving a user's data.
 * 
 * @since 15 January 2017 6:27 PM
 * @author Shortninja
 */

public class SavingService {

	public void attemptSave(User user) {
		ThunderFile file = user.file();
		UUID uuid = user.uuid();
		
		file.set("uuid", uuid);
		file.set("names", Backend.instance().nameHistory.history(uuid));
		file.set("locale", user.locale().name());
		file.set("level", serializeLevel(user.level()));
		file.set("mode", user.mode().name());
		file.set("ranks", serializeRanks(user.ranks()));
		
		if(user.data().hasUpdated())
		{
			for(int i = 0; i < UserData.MAX; i++)
			{
				DataEntry entry = user.data().value(i);
				String key = UserKey.matchIndex(i).key();
				
				if(entry == null) {
					System.out.println("Null " + key + " - " + entry);
				}
				
				if(entry instanceof StringDataEntry)
				{
					file.set(key, ((StringDataEntry) entry).value());
				}else if(entry instanceof BooleanDataEntry)
				{
					file.set(key, ((BooleanDataEntry) entry).value());
				}else if(entry instanceof NumberDataEntry)
				{
					file.set(key, ((NumberDataEntry) entry).value());
				}else file.set(key, entry.values());
			}
			
			user.data().setUpdated(false);
		}
		
		Backend.instance().data.save(file);
	}
	
	private String serializeLevel(UserLevel level) {
		return level.current() + ";" + level.start() + ";" + level.end();
	}
	
	private String serializeRanks(Set<RankMap> ranks) {
		StringBuilder builder = new StringBuilder();
		
		for(RankMap rank : ranks) {
			builder.append(rank.uuid() + ";");
		}
		
		return builder.toString();
	}
	
}