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
import asib.util.UserList
import asib.tests.traits.AsibSetup

class UserListTest extends FunSuite with AsibSetup {

	test("Add user adds user to the list") {
		UserList.reset()
		UserList.addUser("#icambridge", "icambridge")

		val channelUsers = UserList.get("#icambridge")

		assert(channelUsers.size == 1)
		assert(channelUsers(0)   == "icambridge")
		assert(channelUsers.contains("icambridge"))
	}

	test("Remove users removes users from the list") {
		UserList.reset()
		UserList.addUser("#icambridge","icambridge")
		
		assert(UserList.get("#icambridge").size == 1)

		UserList.removeUser("#icambridge","icambridge")
		
		assert(UserList.get("#icambridge").size == 0)
	}

	test("getChannel returns the correct results") {
		UserList.reset()

		assert(UserList.getChannelsIn("icambridge") == List())
		
		UserList.addUser("#icambridge","icambridge")
		UserList.addUser("#scala","icambridge")

		assert(UserList.getChannelsIn("icambridge") == List("#icambridge","#scala"))
		
		UserList.removeUser("#icambridge","icambridge")

		assert(UserList.getChannelsIn("icambridge") == List("#scala"))
	}
}