package us.theaura.gemen;

import us.theaura.gemen.player.queue.QueueService;
import us.theaura.gemen.server.DataController;
import us.theaura.gemen.server.sync.Tasks;
import us.theaura.gemen.server.sync.initializer.InitializationThread;
import us.theaura.gemen.util.lib.thunderbolt.io.ThunderFile;

/**
 * Enum singleton that handles all of our public instances. I decided to go with
 * the enum singleton because of its compile time constants, thread safety, and the
 * overall simplicity.
 * 
 * @since 21 December 2016 9:35 PM
 * @author Shortninja
 */

public enum Backend
{
	INSTANCE;
	
	public InitializationThread initialization;
	public ThunderFile dataFile;
	public DataController data;
	public QueueService queue;
	public Tasks tasks;
	
	public static Backend instance()
	{
		return INSTANCE;
	}
}