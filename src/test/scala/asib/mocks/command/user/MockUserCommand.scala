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

package asib.mocks.command.user

import asib.command.user.AbstractUserCommand

class MockUserCommand extends AbstractUserCommand {

	val helpMessage = "Fake command for testing purposes"

	var lastCommandPayload = Map[String,String]()
	
	def execute(username: String, channel: String, args: String) = {
	  	lastCommandPayload = Map("username" -> username,
		  						 "channel"  -> channel,
		  						 "args"     -> args)

	}

}