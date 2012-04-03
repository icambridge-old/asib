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
import asib.util.UserList
import asib.command.PrivMsg
import asib.tests.traits.AsibSetup

import asib.mocks.command.MockQuit
import asib.mocks.command.quit.MockQuitCommand

class QuitTest extends FunSuite with AsibSetup {

	test("command reaches quit command") {
		val mockQuitCommand = new MockQuitCommand
		val mockQuitTrigger = new MockQuit
		mockQuitTrigger.userCommands = Map("mock" -> mockQuitCommand)

		val sentMessage     = Message("QUIT","asib")
		sentMessage.content = "Bye Bye"
		UserList.addUser("#icambridge", "asib")
		mockQuitTrigger.handle(sentMessage)

		val commandInfo = mockQuitCommand.lastCommandPayload
		val username    = commandInfo.get("username").getOrElse("")
		val message     = commandInfo.get("message").getOrElse("")
		val channel     = commandInfo.get("channel").getOrElse("")

		assert(username == "asib")
		assert(message  == "Bye Bye")
		assert(channel  == "#icambridge")
	}
}