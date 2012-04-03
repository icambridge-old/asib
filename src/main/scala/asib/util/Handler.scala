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

import scala.actors._
import scala.actors.Actor._
import asib.Asib

object Handler extends Actor {

	def handle(message: Message) = {
		val command = message.command

		if (Asib.triggers.isDefinedAt("_")) {
			Asib.triggers.get("_").get.foreach{
				_.handle(message)
			}
		}

		if (Asib.triggers.isDefinedAt(command)) {
			Asib.triggers.get(command).get.foreach{
				_.handle(message)
			}
		}

	}

	def act() = {
		loop {
			receive {
				case message: Message => actor {
					handle(message)
				}
			}
		}
	}
}