package us.theaura.gemen.player.language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.UserController;
import us.theaura.gemen.player.language.message.Broadcast;
import us.theaura.gemen.player.language.message.Message;
import us.theaura.gemen.player.language.message.Prefix;
import us.theaura.gemen.player.profile.User;
import us.theaura.gemen.server.Option;
import us.theaura.gemen.util.JavaUtils;
import us.theaura.gemen.util.lib.EnglishNumerals.Letters;
import us.theaura.gemen.util.lib.bukkit.BukkitUtils;

/**
 * Class for handling all message and language related functions. Also responsible for associating messages
 * with their corresponding locale.
 * 
 * @since 14 January 2017 8:33 PM
 * @author Shortninja
 */

public class LanguageService {

	private static final Map<String, String> COLORS = new HashMap<String, String>();
	private UserController user = Backend.instance().user;
	
	/**
	 * Initializes colors map that is responsible for mapping config color codes to their configured
	 * value.
	 */
	public LanguageService() {
		COLORS.put("&p", Option.PRIMARY.getValue());
		COLORS.put("&s", Option.SECONDARY.getValue());
		COLORS.put("&t", Option.TERTIARY.getValue());
	}
	
	/**
	 * Assigns all messages to their respective enum and locale. Also translates all config color codes,
	 * like "&p", "&s", and "&t", to their configured value.
	 */
	public void load() {
		for(Locale locale : Locale.values()) {
			addMessages(Prefix.class, locale, locale.file().getStringList(Prefix.identifier()));
			addMessages(Broadcast.class, locale, locale.file().getStringList(Broadcast.identifier()));
			addMessages(Message.class, locale, locale.file().getStringList(Message.identifier()));
		}
	}
	
	/**
	 * @param player Player to check for.
	 * @return The locale of the given player; defaulted to English.
	 */
	public Locale getLocale(Player player)
	{ 
		String code = BukkitUtils.getLocale(player);
		
		return Locale.has(code) ? Locale.get(code) : Locale.ENGLISH;
	}
	
	/**
	 * This method will message using a user if the sender is an actual player.
	 * 
	 * @param sender Player or console sender to message.
	 * @param prefix Prefix enum of the message.
	 * @param text Main body of message.
	 * @param replacements Optional parameter(s) to replace each placeholder with.
	 */
	public void send(CommandSender sender, Prefix prefix, LanguageCategory text, String ... replacements) {
		if(sender instanceof Player) {
			send(user.get(((Player) sender).getUniqueId()), prefix, text, replacements);
		}else sender.sendMessage(prefix.message(Locale.ENGLISH) + replace(text.message(Locale.ENGLISH), replacements));
	}
	
	/**
	 * Messages the user. Using the user instance is important for making locales work out correctly!
	 * 
	 * @param user User to send message to.
	 * @param prefix Prefix enum of the message.
	 * @param text Main body of message.
	 * @param replacements Optional parameter(s) to replace each placeholder with.
	 */
	public void send(User user, Prefix prefix, LanguageCategory text, String ... replacements) {
		if(canSend(user)) {
			user.player().sendMessage(prefix.message(user.locale()) + replace(text.message(user.locale()), replacements));
		}
	}
	
	/**
	 * Messages all users and the console.
	 * 
	 * @param prefix Prefix enum of the message.
	 * @param text Main body of message.
	 */
	public void broadcast(Prefix prefix, LanguageCategory text, String ... replacements) {
		for(User user : Backend.instance().user.getAll()) {
			send(user, prefix, text, replacements);
		}
		
		send(Bukkit.getConsoleSender(), prefix, text, replacements);
	}
	
	private String replace(String string, String ... replacements) {
		for(int i = 0; i < replacements.length; i++)
		{
			string = JavaUtils.replace(string, "%" + Letters.get(i) + "%", replacements[i]);
		}
		
		return string;
	}
	
	private boolean canSend(User user) {
		return user != null && user.player() != null;
	}
	
	private void addMessages(Class<? extends LanguageCategory> category, Locale locale, List<String> messages) {
		for(int i = 0; i < category.getEnumConstants().length; i++) {
			String message = messages.get(i);
			
			for(String color : COLORS.keySet()) {
				message = JavaUtils.replace(message, color, COLORS.get(color));
			}
			
			category.getEnumConstants()[i].setMessage(locale, message);
		}
	}
}