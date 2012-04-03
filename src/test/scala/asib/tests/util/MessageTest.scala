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
package asib.tests.util

import org.scalatest.FunSuite
import asib.Asib
import asib.util.Message
import asib.tests.traits.AsibSetup

class MessageTest extends FunSuite with AsibSetup {

	test("Message with no constructor object starts of blank") {
		val message = new Message
		assert(""    == message.channel )
		assert(""    == message.username)
		assert(""    == message.content)
		assert(""    == message.command)
		assert(""    == message.subject)
		assert(false == message.pm)
	}

	test("Message updates channel") {
		val message = new Message
		message.channel = "#icambridge"
		assert("#icambridge" == message.channel)
	}

	test("Message updates username") {
		val message = new Message
		message.username = "icambridge"
		assert("icambridge" == message.username)
	}
	
	test("Message updates content") {
		val message = new Message
		message.content = "Hello World"
		assert("Hello World" == message.content)
	}

	test("message updates command") {
		val message = new Message
		message.command = "PRIVMSG"
		assert("PRIVMSG" == message.command)
	}

	test("Message update subject") {
		val message = new Message
		message.subject = "asib"
		assert("asib" == message.subject)
	}

}