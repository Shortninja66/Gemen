package us.theaura.gemen.player.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import us.theaura.gemen.player.gui.AbstractGui;
import us.theaura.gemen.player.gui.ClickAction;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.player.profile.data.UserKey;
import us.theaura.gemen.util.lib.hex.Items;

/**
 * Simple GUI that allows players to choose their theme in other GUIs.
 * 
 * @since 15 January 2017 8:54 PM
 * @author Shortninja
 */

public class ColorGui extends AbstractGui {

	private static final int SIZE = 27;

	public ColorGui(User user, String title) {
		super(SIZE, title);

		ClickAction action = new ClickAction() {
			@Override
			public void click(Player player, ItemStack item, int slot) {
				user.data().put(UserKey.GUI_COLOR.index(), Short.toString(item.getDurability()));
			}

			@Override
			public boolean shouldClose() {
				return true;
			}
		};

		for(short i = 0; i < 15; i++) {
			setItem(i, glassItem(i), action);
		}

		open(user.player());
	}

	private ItemStack glassItem(short data) {
		ItemStack item = Items.builder()
				.setMaterial(Material.STAINED_GLASS_PANE).setAmount(1).setData(data)
				.setName("&bColor #" + data)
				.addLore("&7Set this glass color as your GUI color.")
				.build();

		return item;
	}
	
}
