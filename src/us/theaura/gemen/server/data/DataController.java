package us.theaura.gemen.server.data;

import java.io.File;
import java.io.IOException;

import us.theaura.gemen.Backend;
import us.theaura.gemen.TheAura;
import us.theaura.gemen.player.language.LanguageLoader;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.server.log.LogService;
import us.theaura.gemen.util.JavaUtils;
import us.theaura.gemen.util.lib.bukkit.information.SimpleOfflinePlayer;
import us.theaura.gemen.util.lib.thunderbolt.Thunderbolt;
import us.theaura.gemen.util.lib.thunderbolt.exceptions.FileLoadException;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Class that manages all data-related functions from saving to loading.
 * 
 * @since 22 December 2016 1:18 PM
 * @author Shortninja
 */

public class DataController {
	
	private LogService log = Backend.instance().log;
	private ThunderFile dataFile = Backend.instance().dataFile;
	
	/**
	 * Initialization for data controller in a field method.
	 */
	public void initialize() {
		SimpleOfflinePlayer.load(TheAura.instance());
		new LanguageLoader();
		
	}
	
	/**
	 * @param name Name of the JSON file; do NOT include JSON extension.
	 * @param path Path to the JSON file.
	 * @return Loaded ThunderFile; will be a temporary file if the file could not be found or created.
	 */
	public ThunderFile load(String name, String path) {
		ThunderFile file = Thunderbolt.get(name);
		
		if(file == null) {
			try {
				file = Thunderbolt.load(name, TheAura.instance().getDataFolder().getAbsolutePath() + File.separator + path);
			}catch (FileLoadException | IOException exception) {
				file = Thunderbolt.get();
				log.warn("Temporary file used in place of '" + name + ".json'!", true);
			}
		}
		
		return file;
	}
	
	/**
	 * @param file JSON file to save.
	 */
	public void save(ThunderFile file) {
		try {
			file.save();
		}catch (IOException exception) {
			log.notify("IOException occurred during general save!", true);
		}
	}
	
	/**
	 * Saves all users as well as the main data file.
	 */
	public void saveAll() {
		for(User user : Backend.instance().user.getAll()) {
			Backend.instance().save.attemptSave(user);
		}
		
		for(Store store : Store.values()) {
			dataFile.set(JavaUtils.replace(store.name().toLowerCase(), "_", "-"), store.amount());
		}
		
		save(dataFile);
		SimpleOfflinePlayer.save(TheAura.instance());
	}
	
}