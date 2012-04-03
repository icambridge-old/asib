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

package asib.util

object UserList {

	protected var userList = scala.collection.mutable.Map[String,List[String]]()

	def reset() = {
		userList = scala.collection.mutable.Map[String,List[String]]()
	}

	def addUser(channel: String, username: String) = {
		channelExists(channel)
		removeUser(channel, username)
		userList(channel) = username :: userList(channel)
	}

	protected def channelExists(channel: String) = {
		if (userList.isDefinedAt(channel) == false) {
			userList +=	channel -> List[String]()
		}
	}

	def removeUser(channel: String, username: String) = {
		channelExists(channel)
		userList(channel) = userList(channel) diff List[String](username)
	}

	def getChannelsIn(username: String) = {
		var channelList = List[String]()
		userList foreach { case (channel, list) =>
			if (list.indexOf(username) != -1) {
				channelList ::= channel
			}
		}
		channelList
	}

	def get(channel: String) = {
		channelExists(channel)
		userList(channel)
	}

}
