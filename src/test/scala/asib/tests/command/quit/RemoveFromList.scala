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

package asib.tests.command.quit

import org.scalatest.FunSuite
import asib.Asib
import asib.tests.traits.AsibSetup

import asib.command.quit.RemoveFromLists
import asib.util.UserList

class RemoveFromListTest extends FunSuite with AsibSetup {

	test("Quit command removes user from channel list") {
		UserList.reset
		assert(UserList.get("#icambridge") == List())

		UserList.addUser("#icambridge","icambridge")
		assert(UserList.get("#icambridge") == List("icambridge"))

		val command = new RemoveFromLists
		command.quit("icambridge","#icambridge","Quit: Quiting")

		assert(UserList.get("#icambridge") == List())
	}

}