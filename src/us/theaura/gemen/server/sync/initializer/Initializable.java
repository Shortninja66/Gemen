package us.theaura.gemen.server.sync.initializer;

/**
 * Interface used for certain classes that need to be manually initialized in a certain order for stable
 * startups.
 * 
 * @since 22 December 2016 1:20 PM
 * @author Shortninja
 */

public interface Initializable
{
	public void initialize();
}