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

package us.theaura.gemen.util.lib.bukkit.annotate;

import org.bukkit.plugin.Plugin;

import us.theaura.gemen.util.lib.bukkit.annotate.command.CommandAnnotations;
import us.theaura.gemen.util.lib.bukkit.annotate.config.ConfigAnnotations;
import us.theaura.gemen.util.lib.bukkit.annotate.message.MessageAnnotations;

public class PluginAnnotations {

	public static final ConfigAnnotations  CONFIG  = new ConfigAnnotations();
	public static final MessageAnnotations MESSAGE = new MessageAnnotations();
	public static final CommandAnnotations COMMAND = new CommandAnnotations();

	public static final AnnotationsAbstract[] ALL_ANNOTATIONS = {
			CONFIG,
			MESSAGE,
			COMMAND };

	public static void loadAll(Plugin plugin, Object classToLoad) {
		for (AnnotationsAbstract annotation : ALL_ANNOTATIONS) {
			annotation.load(plugin, classToLoad);
		}
	}

}
