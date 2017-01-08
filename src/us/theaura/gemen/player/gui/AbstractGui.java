package us.theaura.gemen.player.gui;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.util.lib.hex.Items;

/**
 * Abstract base class for all GUIs that makes click events much simpler.
 * 
 * @since 23 December 2016 9:53 PM
 * @author Shortninja
 */

public abstract class AbstractGui
{
	private Inventory inventory;
	private String title;
	private Map<Integer, ClickAction> actions = new HashMap<Integer, ClickAction>();
	
	public AbstractGui(int size, String title)
	{
		this.title = title;
		inventory = Bukkit.createInventory(null, size, title);
	}
	
	public String title()
	{
		return title;
	}
	
	public Inventory inventory()
	{
		return inventory;
	}
	
	public ClickAction action(int slot)
	{
		return actions.get(slot);
	}
	
	public void setItem(int slot, ItemStack item, ClickAction action)
	{
		inventory.setItem(slot, item);
		
		if(action != null)
		{
			actions.put(slot, action);
		}
	}
	
	public void setGlass(User user)
	{
		ItemStack item = glassItem((short) 14);
		
		ClickAction action = new ClickAction()
		{
			@Override
			public void click(Player player, ItemStack item, int slot)
			{
				// TODO: ColorGui
			}
			
			@Override
			public boolean shouldClose()
			{
				return false;
			}
		};
		
		for(int i = 0; i < 3; i++)
		{
			int slot = 9 * i;
			
			setItem(slot, item, action);
			setItem(slot + 8, item, action);
		}
	}
	
	private ItemStack glassItem(short data)
	{
		ItemStack item = Items.builder()
				.setMaterial(Material.STAINED_GLASS_PANE).setAmount(1).setData(data)
				.setName("&bColor #" + data)
				.addLore("&7Click to change your GUI color!")
				.build();
		
		return item;
	}
}