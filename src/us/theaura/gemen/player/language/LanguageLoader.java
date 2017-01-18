package us.theaura.gemen.player.language;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import us.theaura.gemen.Backend;
import us.theaura.gemen.TheAura;
import us.theaura.gemen.server.log.LogService;

/**
 * Responsible for simply loading all language files to their associated locale.
 * 
 * @since 14 January 2017 8:47 PM
 * @author Shortninja
 */

public class LanguageLoader {

	private LogService log = Backend.instance().log;
	
	/**
	 * Attempts to register all language files to their associated locale.
	 */
	public LanguageLoader() {
		for(Locale locale : Locale.values()) {
			try {
				locale.setFile(copyFile("lang_" + locale.code()));
			}catch (IOException exception) {
				log.notify("IOException @ LanguageLoader initialization!", true);
			}
		}
		
		Backend.instance().language.load();
	}

	private FileConfiguration copyFile(String fileName) throws IOException {
		File file = new File(TheAura.instance().getDataFolder() + "/lang/", fileName + ".yml");
		InputStream in = this.getClass().getResourceAsStream("/lang/" + fileName + ".yml");
		OutputStream out = null;
		byte[] buffer = new byte[1024];
		int current = 0;
		
		if(!file.exists()) {
			TheAura.instance().getDataFolder().mkdirs();
			file.getParentFile().mkdirs();
			file.createNewFile();
			out = new FileOutputStream(file);
			log.notify("Creating " + fileName + ".yml language file...", false);
			
			while((current = in.read(buffer)) > -1) {
				out.write(buffer, 0, current);
			}
			
			out.close();
		}
		
		in.close();

		return YamlConfiguration.loadConfiguration(file);
	}
	
}