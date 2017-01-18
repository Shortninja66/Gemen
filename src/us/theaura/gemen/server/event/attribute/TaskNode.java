package us.theaura.gemen.server.event.attribute;

import java.util.ArrayList;
import java.util.List;

import us.theaura.gemen.server.event.EventTask;

/**
 * Object class for containing a set of tasks.
 * 
 * @since Jan 14, 2017 7:43:13 PM
 * @author Shortninja
 */

public class TaskNode {

	private List<EventTask> tasks = new ArrayList<EventTask>();
	
	/**
	 * @param tasks All tasks that will be associated with this node.
	 */
	public TaskNode(EventTask ... tasks) {
		for(EventTask task : tasks) {
			this.tasks.add(task);
		}
	}
	
	/**
	 * @param task Associates the given task with this node.
	 */
	public void addTask(EventTask task) {
		tasks.add(task);
	}
	
	/**
	 * Executes all registered tasks with the given parameters.
	 * 
	 * @param parameters Parameters from the event.
	 */
	public void call(EventParameters parameters) {
		for(EventTask task : tasks) {
			task.call(parameters);
		}
	}
	
}