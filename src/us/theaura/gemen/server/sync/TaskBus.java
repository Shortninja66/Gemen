package us.theaura.gemen.server.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple bus that allows for modules to execute tasks without worrying about
 * runnables.
 * 
 * @since 10 November 2016 9:03 PM
 * @author Shortninja
 */

public class TaskBus {
	
	private final int IDENTIFIER;
	private final long DELAY;
	private final double BUFFER;
	private List<ITask> passengers = new ArrayList<ITask>();

	/**
	 * @param identifier ID for the given task. Rarely used, but should be unique.
	 * @param task Tick delay for the task that should be executed.
	 */
	public TaskBus(int identifier, TaskDelay task) {
		IDENTIFIER = identifier;
		DELAY = task.delay();
		BUFFER = DELAY - (DELAY * 0.3);
	}

	/**
	 * @return ID of the task bus.
	 */
	public int identifier() {
		return IDENTIFIER;
	}

	/**
	 * @return Tick delay that this bus uses.
	 */
	public long delay() {
		return DELAY;
	}

	/**
	 * @param task Task to add.
	 * @param delay Delay of the task. Usually not different than the bus's delay.
	 * @return Whether or not the task was added; false if the delay is too low for the bus.
	 */
	public boolean addTask(ITask task, long delay) {
		boolean added = false;

		if(Math.abs(DELAY - delay) - BUFFER <= 0) {
			passengers.add(task);
			added = true;
		}

		return added;
	}

	/**
	 * Exeutes all passenger tasks in the task bus.
	 */
	public void call() {
		for(ITask task : passengers) {
			task.execute();
		}
	}
	
}