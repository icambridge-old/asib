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

package asib.command.user

import asib.Asib
import asib.command.PrivMsg

class Help extends AbstractUserCommand {

	val helpMessage = "The help command"

	def execute(username: String, channel: String, args: String) = {

		Asib.triggers.get("PRIVMSG").get.foreach(
			_.userCommands.foreach {
				case (commandName, commandObject) => Asib.sendNotice(username, "!" + commandName +
					" - " + commandObject.asInstanceOf[AbstractUserCommand].helpMessage)
			}
		)
	}

}