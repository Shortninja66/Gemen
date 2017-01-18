/*
 * Copyright 2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */

package us.theaura.gemen.util.lib.bukkit.annotate.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import us.theaura.gemen.Backend;
import us.theaura.gemen.player.language.LanguageService;
import us.theaura.gemen.player.language.message.Message;
import us.theaura.gemen.player.language.message.Prefix;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.ArgumentParseException;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.CommandException;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.IllegalSenderException;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.InvalidLengthException;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.PermissionException;
import us.theaura.gemen.util.lib.bukkit.annotate.command.exception.UnhandledCommandException;

public class FeedbackErrorHandler extends CommandErrorHandler {

	private LanguageService language = Backend.instance().language;
	
	@Override
	public void handleCommandException(CommandException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.ERROR, Message.INTERNAL_ERROR, exception.getMessage());
		throw exception;
	}

	@Override
	public void handlePermissionException(PermissionException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.RESTRICTED, Message.NO_PERMISSION, command.getName());
	}

	@Override
	public void handleIllegalSender(IllegalSenderException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.RESTRICTED, Message.ONLY_PLAYERS);
	}

	@Override
	public void handleUnhandled(UnhandledCommandException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.ERROR, Message.INTERNAL_ERROR, exception.getMessage());
	}

	@Override
	public void handleLength(InvalidLengthException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.ERROR, Message.INVALID_ARGUMENTS, command.getName() + " " + command.getUsage());
	}

	@Override
	public void handleArgumentParse(ArgumentParseException exception, CommandSender sender, Command command, String[] args) {
		language.send(sender, Prefix.ERROR, Message.INTERNAL_ERROR, exception.getMessage());
	}
}
