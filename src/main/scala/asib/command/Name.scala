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
import asib.command.part.AbstractPartCommand
import asib.util.UserList
import asib.util.Message

class Name extends AbstractCommand {
	/**
	 * Handles the taking of names when joining a channel
	 * @param line
	 */
	def handle(message: Message) = {
		val line  = message.raw
		val regex = """^:([a-z0-9\.]+) ([0-9]+) ([a-z0-9_-]+) @ (#[a-z0-9_-]+) :(.*)$""".r

		line match {
			case regex(server,digit,user,channel,names) => addNames(names, channel)
			case _ => false
		}                                              
	}

	def addNames(names: String, channel: String) = {
		names split(" ") foreach { nickname =>
			val nameRegex = """^([@\+~%&]+)?([a-z0-9-_]+)$""".r
			nickname match {
				case nameRegex(mode, username) => UserList.addUser(channel, username)
				case _ =>  false
			}

		}
	
	}

}