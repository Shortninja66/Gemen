package us.theaura.gemen.player.gui.base;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.theaura.gemen.player.gui.AbstractGui;
import us.theaura.gemen.player.gui.ClickAction;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.player.profile.data.entry.NumberDataEntry;
import us.theaura.gemen.util.lib.hex.Items;

/**
 * Class for adding the glass panes in inventories.
 * 
 * @since 13 January 2017 6:38 PM
 * @author Shortninja
 */

public class GlassConstructor implements ClickAction {

	@Override
	public void click(Player player, ItemStack item, int slot) {
		
	}
	
	@Override
	public boolean shouldClose() {
		return false;
	}
	
	/**
	 * Sets glass on the sides of a 27-slot inventory.
	 * 
	 * @param gui AbstractGui to add glass to.
	 * @param user User to get glass color from.
	 */
	public void setGlass(AbstractGui gui, User user) {
		NumberDataEntry data = UserKey.GUI_COLOR.numberEntry(user.data());
		ItemStack item = glassItem((short) data.value());
		
		for(int i = 0; i < 3; i++) {
			int slot = 9 * i;
			
			gui.setItem(slot, item, this);
			gui.setItem(slot + 8, item, this);
		}
	}
	
	private ItemStack glassItem(short data) {
		ItemStack item = Items.builder()
				.setMaterial(Material.STAINED_GLASS_PANE).setAmount(1).setData(data)
				.setName("&bColor #" + data)
				.addLore("&7Click to change your GUI color!")
				.build();
		
		return item;
	}
	
}