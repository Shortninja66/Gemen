package us.theaura.gemen.server.sync;

import java.io.Serializable;

/**
 * Interface that allows for executing anonymous functions in sync with Bukkit API or
 * just to pass a bit of code as a parameter.
 * 
 * @since 28 March 2016 4:53 AM
 * @author Shortninja
 */

public interface ITask extends Serializable {
	
	/**
	 * Abstract code to execute later.
	 */
	public void execute();
	
}