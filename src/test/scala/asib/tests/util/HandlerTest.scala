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
import asib.util.Handler
import asib.tests.traits.AsibSetup

class HandlerTest extends FunSuite with AsibSetup {

	test("triggers equals right value") {
		assert(Asib.triggers == Map("PRIVMSG" -> List(privMsgTrigger)))
	}

	test("trigger accepts message") {
		privMsgTrigger.handle(helloWorldMessage)
		assert(privMsgTrigger.lastMessage == helloWorldMessage)
	}
	
	test("Handler relays message to the mock privmsg trigger") {
		Handler.handle(helloWorldMessage)
		assert(privMsgTrigger.lastMessage == helloWorldMessage)
	}

}