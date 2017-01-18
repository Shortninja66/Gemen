package us.theaura.gemen;

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
import us.theaura.gemen.util.lib.bukkit.event.BukkitEventBus;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Enum singleton that handles all of our public instances. I decided to go with
 * the enum singleton because of its compile time constants, thread safety, and the
 * overall simplicity.
 * 
 * @since 21 December 2016 9:35 PM
 * @author Shortninja
 */

public enum Backend {
	
	INSTANCE;
	
	public LogService log;
	public LanguageService language;
	public DataController data;
	public EventService event;
	public Observatory observatory;
	public CommandRegistry command;
	public QueueService queue;
	public TeleportService teleport;
	public UserController user;
	public NameHistoryService nameHistory;
	public LoadingService load;
	public SavingService save;
	public Tasks tasks;
	public ThunderFile dataFile;
	
	public static Backend instance() {
		return INSTANCE;
	}
	
	public void initialize() {
		dataFile = data.load("data", "data");
		data.initialize();
		event.initialize(new BukkitEventBus(TheAura.instance()));
	}
	
	public void shutdown() {
		data.saveAll();
	}
	
}