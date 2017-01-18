package us.theaura.gemen.server;

import java.util.List;

import org.bukkit.command.CommandSender;

import us.theaura.gemen.Backend;
import us.theaura.gemen.TheAura;
import us.theaura.gemen.player.language.LanguageCategory;
import us.theaura.gemen.player.language.message.Prefix;
import us.theaura.gemen.util.lib.bukkit.annotate.PluginAnnotations;
import us.theaura.gemen.util.lib.bukkit.annotate.command.Command;
import us.theaura.gemen.util.lib.bukkit.annotate.command.Completion;
import us.theaura.gemen.util.lib.bukkit.annotate.command.JoinedArg;
import us.theaura.gemen.util.lib.bukkit.annotate.command.Permission;

/**
 * Basic command class that registers an example command.
 * 
 * @since 16 January 2017 8:54 PM
 * @author Shortninja
 */

public class CommandRegistry {

	public CommandRegistry() {
		PluginAnnotations.COMMAND.registerCommands(TheAura.instance(), this);
	}
	
	@Command(name = "broadcast", aliases = "bc", description = "&bBroadcasts message to the server", usage = "<message>", min = 1)
	@Permission("ta.broadcast")
	public void command(CommandSender sender, @JoinedArg String message) {
		Backend.instance().language.broadcast(Prefix.INFORMATION, LanguageCategory.create(message));
	}
	
	@Completion(name = "broadcast")
	public void complete(List<String> completions, CommandSender sender, String message) {
		if(message == null) {
			completions.add("Welcome!");
		}
	}
	
}