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
import asib.command.quit.AbstractQuitCommand
import asib.util.UserList
import asib.util.Message

class Quit extends AbstractCommand {

	proccessCommands("quits")

	def handle(message: Message) = {
		val channels = UserList.getChannelsIn(message.username)
		channels.foreach { channelName =>
			for ((commandName, commandObject: AbstractQuitCommand) <- userCommands) {
				commandObject.quit(message.username, channelName, message.content)
			}
		}

	}

}