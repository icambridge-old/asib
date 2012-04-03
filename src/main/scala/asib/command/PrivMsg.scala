/*
	ASIB - A Scala IRC Bot
    Copyright (C) 2012  Iain Cambridge

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package asib.command

import asib.Asib
import asib.command.user._
import asib.util.Message

class PrivMsg extends AbstractCommand {

	proccessCommands("privmsgs")

	def handle(message: Message) = {
		val username = message.username
		val content  = message.content
		val channel  = message.channel

		val ctcpRegex = """([a-zA-Z]+)""".r
		val commandWithArgsRegex = """\!([a-zA-Z]+) (.*)""".r
		val commandWithoutArgsRegex = """\!([a-zA-Z]+)""".r

		content match {
			case ctcpRegex(command) => userCtcp(username, command)
			case commandWithArgsRegex(command, args) => userCommand(username, channel,
																	command, args)
			case commandWithoutArgsRegex(command) => userCommand(username, channel, command)
			case _ => None
		}

	}

	def userCommand(username: String, channel: String, command: String, args: String = "") = {
		if (userCommands.isDefinedAt(command)) {
			userCommands.get(command).get.asInstanceOf[AbstractUserCommand].execute(username,
																					channel, args)
		} else {
			Asib.sendMsg(channel, username + ", the \"" + command + "\" command not recognized")
		}
	}

	def userCtcp(username: String, command: String) = {
		command match {
			case "VERSION" => Asib.sendMsg(username, "VERSION ASIB " + Asib.version + "")
			case "CLIENTINFO" => Asib.sendMsg(username, "CLIENTINFO VERSION")
		}

	}
}
