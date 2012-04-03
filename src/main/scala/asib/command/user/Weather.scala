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
import asib.util.Uri
import dispatch._
import net.liftweb.json.JsonParser
import net.liftweb.json.JsonAST._


class Weather extends AbstractUserCommand {

	val helpMessage = "Fetches the local time usage - !weather <location>"

	def execute(username: String, channel: String, args: String) = {

		val http = new Http()
		val weatherApiKey = Config.getString("weatherApiKey")

		// Took me a while to figure out but if the JSON value isn't there Config.getString returns
		// a string containing "None". This will be due to lift's JSON lib return None and me
		// turning it to a string.
		if (weatherApiKey == "None" || weatherApiKey == "") {
			Asib.sendMsg(channel, username + ", the weather api key is required. Goto " +
				"http://www.worldweatheronline.com/ to sign up and get one")
		} else {
			// Uses The url object's apply functionality.
			val u = url("http://free.worldweatheronline.com/feed/weather.ashx?key=" + weatherApiKey +
				"&q=" + Uri.encode(args) + "&format=json&num_of_days=1")
			val json = http(u >- JsonParser.parse)

			val desc     = (json \ "data" \ "current_condition" \ "weatherDesc" \ "value" values)
			val temp     = (json \ "data" \ "current_condition" \ "temp_C"  values)
			val location = (json \ "data" \ "request" \ "query" values)
			Asib.sendMsg(channel, username + " The weather in " + location + " is " + desc +
								  " and temperature is " + temp + "°C")
		}

	}
}
