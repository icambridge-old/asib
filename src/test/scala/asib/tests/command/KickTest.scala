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

package asib.tests.command

import org.scalatest.FunSuite
import asib.Asib
import asib.util.Message
import asib.command.PrivMsg
import asib.tests.traits.AsibSetup

import asib.mocks.command.MockKick
import asib.mocks.command.kick.MockKickCommand

class KickTest extends FunSuite with AsibSetup {

	test("event reaches kick command") {
		val mockKickCommand = new MockKickCommand
		val mockKickTrigger = new MockKick
		val sentMessage     = Message("JOIN","asib","#icambridge","Bye Bye")
		sentMessage.subject = "idiot"
		mockKickTrigger.userCommands = Map("mock" -> mockKickCommand)

		mockKickTrigger.handle(sentMessage)

		val commandInfo = mockKickCommand.lastCommandPayload
		val username    = commandInfo.get("username").getOrElse("")
		val victim      = commandInfo.get("victim").getOrElse("")
		val channel     = commandInfo.get("channel").getOrElse("")
		val message     = commandInfo.get("message").getOrElse("")

		assert(username == "asib")
		assert(victim	== "idiot")
		assert(channel  == "#icambridge")
		assert(message  == "Bye Bye")

	}
}