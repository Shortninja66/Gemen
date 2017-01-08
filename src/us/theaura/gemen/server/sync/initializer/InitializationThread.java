package us.theaura.gemen.server.sync.initializer;

import java.util.concurrent.CountDownLatch;

/**
 * Object class used to initialize and get certain instances statically.
 * 
 * @since 21 December 2016 2:01 PM
 * @author Shortninja
 */

public class InitializationThread extends Thread
{
	private final CountDownLatch LATCH = new CountDownLatch(1);
	private Initializable initializable;
	
	public InitializationThread(Initializable initializable)
	{
		this.initializable = initializable;
	}
	
	@Override
	public void run()
	{
		initializable.initialize();
		LATCH.countDown();
	}
	
	public Initializable getInitializable() throws InterruptedException
	{
		LATCH.await();
		return initializable;
	}
}