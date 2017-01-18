package us.theaura.gemen.server.sync.initializer;

import java.util.concurrent.CountDownLatch;

import us.theaura.gemen.Backend;

/**
 * Object class used to initialize and get certain instances statically.
 * 
 * @since 21 December 2016 2:01 PM
 * @author Shortninja
 */

public class InitializationThread extends Thread {
	
	private final CountDownLatch LATCH = new CountDownLatch(1);
	private Initializable initializable;

	/**
	 * @param initializable Initializable instance.
	 */
	public InitializationThread(Initializable initializable) {
		this.initializable = initializable;
		run();
	}

	@Override
	public void run() {
		initializable.initialize();
		LATCH.countDown();
	}

	/**
	 * @return The initializable instance AFTER it has been initialized by the run block.
	 */
	public Initializable getInitializable() {
		try {
			LATCH.await();
		}catch (InterruptedException exception) {
			Backend.instance().log.notify("InterruptedException occurred in initialization thread!", true);
		}

		return initializable;
	}
	
}