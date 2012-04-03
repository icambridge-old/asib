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

import asib.mocks.command.MockJoin
import asib.mocks.command.join.MockJoinCommand

class JoinTest extends FunSuite with AsibSetup {

	test("command reaches join command") {
		val mockJoinCommand = new MockJoinCommand
		val mockJoinTrigger = new MockJoin
		val sentMessage     = Message("JOIN","asib","#icambridge")
		mockJoinTrigger.userCommands = Map("mock" -> mockJoinCommand)
		mockJoinTrigger.handle(sentMessage)

		val commandInfo = mockJoinCommand.lastCommandPayload
		val username    = commandInfo.get("username").getOrElse("")
		val channel     = commandInfo.get("channel").getOrElse("")
		assert(username == "asib")
		assert(channel  == "#icambridge")

	}
}