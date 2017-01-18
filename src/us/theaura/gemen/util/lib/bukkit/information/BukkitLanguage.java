package us.theaura.gemen.util.lib.bukkit.information;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.theaura.gemen.util.lib.RomanNumeral;

/**
 * Class that allows for creating legible messages from Bukkit's enum names.
 * 
 * @since 2 April 2016 5:54 AM
 * @author Shortninja
 */

public class BukkitLanguage {
	
	public String format(String string) {
		StringBuilder builder = new StringBuilder();
		string = string.replace("_", " ").toLowerCase();

		for(String word : string.split(" ")) {
			builder.append(word.substring(0, 1).toUpperCase() + word.substring(1) + " ");
		}

		return builder.toString().trim();
	}

	public String getItemName(ItemStack item) {
		String itemName = getTypeName(item.getType());
		ItemMeta itemMeta = item.getItemMeta();

		if(itemMeta.hasDisplayName()) {
			itemName = itemMeta.getDisplayName();
		}

		return itemName;
	}

	public String getTypeName(Material type) {
		return format(type.name());
	}

	public String convertToRomanNumerals(int number) {
		return new RomanNumeral(number).toString();
	}

	public String convertEnchantmentName(String enchantmentName) {
		String converted = "null";

		switch(enchantmentName) {
			case "ARROW_DAMAGE":
			converted = "Power";
				break;
			case "ARROW_FIRE":
			converted = "Flame";
				break;
			case "ARROW_INFINITE":
			converted = "Infinity";
				break;
			case "ARROW_KNOCKBACK":
			converted = "Punch";
				break;
			case "DAMAGE_ALL":
			converted = "Sharpness";
				break;
			case "DAMAGE_ARTHROPODS":
			converted = "Bane of Arthropods";
				break;
			case "DAMAGE_UNDEAD":
			converted = "Smite";
				break;
			case "DIG_SPEED":
			converted = "Efficiency";
				break;
			case "DURABILITY":
			converted = "Unbreaking";
				break;
			case "FIRE_ASPECT":
			converted = "Fire Aspect";
				break;
			case "KNOCKBACK":
			converted = "Knockback";
				break;
			case "LOOT_BONUS_BLOCKS":
			converted = "Fortune";
				break;
			case "LOOT_BONUS_MOBS":
			converted = "Looting";
				break;
			case "LUCK":
			converted = "Luck of the Sea";
				break;
			case "LURE":
			converted = "Lure";
				break;
			case "OXYGEN":
			converted = "Respiration";
				break;
			case "PROTECTION_ENVIRONMENTAL":
			converted = "Protection";
				break;
			case "PROTECTION_EXPLOSIONS":
			converted = "Blast Protection";
				break;
			case "PROTECTION_FALL":
			converted = "Feather Falling";
				break;
			case "PROTECTION_FIRE":
			converted = "Fire Protection";
				break;
			case "PROTECTION_PROJECTILE":
			converted = "Projectile Protection";
				break;
			case "SILK_TOUCH":
			converted = "Silk Touch";
				break;
			case "THORNS":
			converted = "Thorns";
				break;
			case "WATER_WORKER":
			converted = "Aqua Affinity";
				break;
		}

		return converted;
	}
	
}