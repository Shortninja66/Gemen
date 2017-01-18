package us.theaura.gemen.player.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Interface for providing abstract actions whenever a user clicks an item in a GUI.
 * 
 * @since 23 December 2016 9:54 PM
 * @author Shortninja
 */

public interface ClickAction {
	
	/**
	 * Action to occur when a player clicks on a certain item.
	 * 
	 * @param player Player that clicked.
	 * @param item Item that was clicked.
	 * @param slot Inventory slot of the clicked item.
	 */
	public void click(Player player, ItemStack item, int slot);
	
	/**
	 * @return Whether or not the inventory should be closed after a click has occurred.
	 */
	public boolean shouldClose();
	
}