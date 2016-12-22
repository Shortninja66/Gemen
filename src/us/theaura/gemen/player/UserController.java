package us.theaura.gemen.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import us.theaura.gemen.player.profile.User;

/**
 * Class that manages all functions regarding user's and their instances.
 * 
 * @since December 2016 9:40 PM
 * @author Shortninja
 */

public class UserController
{
	private static Map<UUID, User> users = new HashMap<UUID, User>();
	
	public User get(UUID uuid)
	{
		User user = users.get(uuid);
		
		if(user == null)
		{
			user = generate(uuid);
		}
		
		return user;
	}
	
	public User generate(UUID uuid)
	{
		return new User(uuid, "null");
	}
	
	public boolean has(UUID uuid)
	{
		return users.containsKey(uuid);
	}
	
	public void add(User user)
	{
		users.put(user.uuid(), user);
	}
	
	public void remove(UUID uuid)
	{
		users.remove(uuid);
	}
}