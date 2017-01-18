package us.theaura.gemen.player.gui;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.gui.base.GlassConstructor;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.util.lib.bukkit.BukkitUtils;

/**
 * Abstract base class for all GUIs that makes click events much simpler.
 * 
 * @since 23 December 2016 9:53 PM
 * @author Shortninja
 */

public abstract class AbstractGui {
	
	private static final GlassConstructor GLASS = new GlassConstructor();
	private Inventory inventory;
	private String title;
	private Map<Integer, ClickAction> actions = new HashMap<Integer, ClickAction>();
	
	/**
	 * @param size Size of the inventory, Bukkit restrictions apply.
	 * @param title Title of the inventory, colorized.
	 */
	public AbstractGui(int size, String title) {
		this.title = title;
		inventory = Bukkit.createInventory(null, size, BukkitUtils.colorize(title));
	}
	
	/**
	 * @return Title of the current inventory.
	 */
	public String title() {
		return title;
	}
	
	/**
	 * @param slot Slot of the clicked item.
	 * @return ClickAction associated with the given slot; may be null.
	 */
	public ClickAction action(int slot) {
		return actions.get(slot);
	}
	
	/**
	 * @param slot Slot of the clickable item.
	 * @param item Clickable item to use.
	 * @param action Action to carry out when item is clicked; null is allowed.
	 */
	public void setItem(int slot, ItemStack item, ClickAction action) {
		inventory.setItem(slot, item);
		actions.put(slot, action);
	}
	
	/**
	 * Attempts to set glass on the sides of an inventory. Will emit warning if inventory size is greater
	 * than 27.
	 * 
	 * @param user User to get glass color from.
	 */
	public void setGlass(User user) {
		if(inventory.getSize() > 27) {
			Backend.instance().log.warn("Inventory '" + title + "' is too large to be themed!", false);
			return;
		}
		
		GLASS.setGlass(this, user);
	}
	
	/**
	 * @param player Player to open inventory for.
	 */
	public void open(Player player) {
		player.openInventory(inventory);
	}
	
}