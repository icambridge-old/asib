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

package asib.mocks.command.kick

import asib.command.kick.AbstractKickCommand

class MockKickCommand extends AbstractKickCommand {

	val helpMessage = "Fake command for testing purposes"

	var lastCommandPayload = Map[String,String]()

	def kick(username: String, channel: String, victim: String, message: String) = {
		lastCommandPayload = Map("username" -> username,
								 "channel"  -> channel,
								 "victim"   -> victim,
								 "message"  -> message)
	}

}