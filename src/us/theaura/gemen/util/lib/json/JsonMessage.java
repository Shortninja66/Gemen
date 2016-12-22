package us.theaura.gemen.util.lib.json;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

/**
 * Message sender for JSON strings.
 * 
 * @author JustisR, Shortninja
 */

public class JsonMessage
{
	String msg;
	
	/**
	 * Create a new json message!
	 */
	public JsonMessage()
	{
		msg = "[{\"text\":\"\",\"extra\":[{\"text\": \"\"}";
	}
	
	/**
	 * Send the json string to all players on the server.
	 */
	public void send()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			send(player);
		}
	}
	
	/**
	 * Send the json string to specified player(s)
	 * 
	 * @param player to send the message to.
	 */
	public void send(Player ... players)
	{
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(msg + "]}]"));
		
		for(Player player : players)
		{
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	/**
	 * Append text to the json message.
	 * 
	 * @param text to be appended
	 * @return json string builder
	 */
	public JsonStringBuilder append(String text)
	{
		return new JsonStringBuilder(this, esc(text));
	}
	
	/**
	 * Appends text and saves at the same time
	 * 
	 * @param text to be appended
	 * @return json message
	 */
	public JsonMessage appendSave(String text)
	{
		return append(text).save();
	}
	
	private String esc(String s)
	{
		return JSONObject.escape(s);
	}
}