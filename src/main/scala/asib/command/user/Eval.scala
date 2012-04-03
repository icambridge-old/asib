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

package asib.command.user

import asib.Asib
import asib.util.Config
import asib.net.api.Pastebin
import asib.util.Uri
import dispatch._


class Eval extends AbstractUserCommand {

	val helpMessage = "Fetches the local time usage - !eval <ode>"

	def execute(username: String, channel: String, args: String) = {

		val http = new Http()
		var finished = false

		do {
			val u      = url("http://www.simplyscala.com/interp?bot=irc&code=" + Uri.encode(args))
			val output = http(u as_str)

			val lines = output.split("\n")
			if (lines(0) == "New interpreter instance being created for you," +
						  "this may take a few seconds." ||
				lines(0) == "Please be patient.") {
				Asib.sendMsg(channel, username + ", I've been told to wait. I will try again in" +
									  " 10 seconds")
				Thread.sleep(10)
			} else {
            	if (lines.size < 4) {
					lines foreach { line =>
						Asib.sendMsg(channel, username + ": " + line)
					}
				} else {
					val pastebinUrl = Pastebin.create("Asib Eval output", output)
					Asib.sendMsg(channel, username + ", the output can be found " + pastebinUrl)
				}
				finished = true
			}

		} while(finished == false)
	}

}
