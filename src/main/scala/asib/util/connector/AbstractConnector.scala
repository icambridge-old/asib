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

package asib.util.connector

import asib.util.Message

abstract class AbstractConnector {


	def connect()

	def send(message: String)

	def receive(): Message

	def nick(nick: String)

	def join(channel: String)

	def part(channel: String, message: String = "")

	def kick(channel: String, user: String, message: String = "")

	def msg(channel: String, message: String)

	def notice(channel: String, message: String)

	def stayAlive(message: Message) : Boolean

	def format(line: String): Message

	def isConnected: Boolean
}