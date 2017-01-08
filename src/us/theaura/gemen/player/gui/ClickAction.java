package us.theaura.gemen.player.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Interface for providing abstract actions whenever a user clicks an item in a GUI.
 * 
 * @since 23 December 2016 9:54 PM
 * @author Shortninja
 */

public interface ClickAction
{
	public void click(Player player, ItemStack item, int slot);
	public boolean shouldClose();
}