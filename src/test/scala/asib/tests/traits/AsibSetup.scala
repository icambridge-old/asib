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

package asib.tests.traits

import asib.Asib

import asib.mocks.util.connector.MockIrcConnector
import asib.mocks.util.logger.MockLogger
import asib.mocks.command.MockPrivMsg
import asib.mocks.command.user.MockUserCommand
import asib.util.Message

trait AsibSetup  {

	val connector          = new MockIrcConnector
	val logger             = new MockLogger
	val privMsgTrigger     = new MockPrivMsg
	val mockUserCommand    = new MockUserCommand
	val helloWorldMessage  = Message("PRIVMSG","asib","#icambridge","hello world")

	privMsgTrigger.userCommands = Map("test" -> mockUserCommand)

	Asib.reset
	Asib.connection             = connector
	Asib.loggers                = List(logger)
	Asib.triggers               = Map("PRIVMSG" -> List(privMsgTrigger))
}