package us.theaura.gemen;

import org.bukkit.plugin.java.JavaPlugin;

import us.theaura.gemen.player.TeleportService;
import us.theaura.gemen.player.UserController;
import us.theaura.gemen.player.language.LanguageService;
import us.theaura.gemen.player.profile.store.LoadingService;
import us.theaura.gemen.player.profile.store.NameHistoryService;
import us.theaura.gemen.player.profile.store.SavingService;
import us.theaura.gemen.player.queue.QueueService;
import us.theaura.gemen.server.CommandRegistry;
import us.theaura.gemen.server.data.DataController;
import us.theaura.gemen.server.event.EventService;
import us.theaura.gemen.server.event.observe.Observatory;
import us.theaura.gemen.server.log.LogService;
import us.theaura.gemen.server.sync.Tasks;

/**
 * Serves as the Gemen's main class as well as the accessor for other modules.
 * 
 * TODO: JSON hover for information (@RankMap)
 * 
 * @since December 21 2016 9:35 PM
 * @author Shortninja
 */

public class TheAura extends JavaPlugin {
	
	private static TheAura plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		initializeBackend();
	}

	@Override
	public void onDisable() {
		Backend.instance().shutdown();
		plugin = null;
	}

	/**
	 * @return TheAura commons plugin instance.
	 */
	public static TheAura instance() {
		return plugin;
	}
	
	/**
	 * Initializes all backend instances in the order that they should be.
	 */
	private void initializeBackend() {
		Backend backend = Backend.instance();
		
		backend.log = new LogService();
		backend.language = new LanguageService();
		backend.data = new DataController();
		backend.event = new EventService();
		backend.observatory = new Observatory();
		backend.command = new CommandRegistry();
		backend.queue = new QueueService();
		backend.teleport = new TeleportService();
		backend.user = new UserController();
		backend.nameHistory = new NameHistoryService();
		backend.load = new LoadingService();
		backend.save = new SavingService();
		backend.tasks = new Tasks();
		backend.initialize();
	}
	
}