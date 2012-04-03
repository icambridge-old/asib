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
package asib

import org.scalatest.FunSuite
import asib.mocks.util.connector.MockIrcConnector
import asib.mocks.util.logger.MockLogger
import asib.tests.traits.AsibSetup

class AsibTest extends FunSuite with AsibSetup {

	test("connector is the same as the one Asib is using") {
		assert(Asib.connection == connector)
	}

	test("sendData relays the message to the connector") {
    	Asib.sendData("Hello World")
		assert(connector.lastSentMessage(0) == "Hello World")
    }

	test("sendData relays the message to the logger") {
		Asib.sendData("Hello World")
		assert(logger.lastLoggedMessage == "Hello World")
	}
	
	test("sendMsg relays valid PRIVMSG command") {
		Asib.sendMsg("#icambridge", "Hello #icambridge")
		assert(connector.lastSentMessage(0) == "PRIVMSG #icambridge :Hello #icambridge")
	}

	test("sendNotice relays valid NOTICE command") {
		val joke = "Why was the Energizer Bunny arrested?  He was charged with battery."
		Asib.sendNotice("icambridge", joke)
		assert(connector.lastSentMessage(0) == "NOTICE icambridge :" + joke)
	}

	test("setNick relays valid NICK command") {
    	Asib.setNick("newNick")
		assert(connector.lastSentMessage(0) == "NICK newNick")
	}

	test("joinChannel relays valid JOIN command") {
		Asib.joinChannel("#icambridge")
		assert(connector.lastSentMessage(0) == "JOIN #icambridge")
	}

	test("partChannel relays valid PART command") {
		Asib.partChannel("#icambridge")
		assert(connector.lastSentMessage(0) == "PART #icambridge")
	}

	test("kickUser relays valid KICK command with default message") {
		Asib.kickUser("#icambridge","loser")
		assert(connector.lastSentMessage(0) == "KICK #icambridge loser :Get out")
	}
	
	test("kickUser relays valid KICK command with passed message") {
		Asib.kickUser("#icambridge","loser","bye bye")
		assert(connector.lastSentMessage(0) == "KICK #icambridge loser :bye bye")
	}
}