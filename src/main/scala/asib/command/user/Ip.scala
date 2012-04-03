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
import asib.net.api.Geoip

class Ip extends AbstractUserCommand {

	val helpMessage = "Returns geo info on an IP - !ip <ipaddress>"

	def execute(username: String, channel: String, args: String) = {
		val ipAddress = """^([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3})$""".r

		args match {
			case ipAddress(address) => checkIp(username, channel, address)
			case _ => Asib.sendMsg(channel, username + ", invalid IP")
		}
	}

	def checkIp(username: String, channel: String, ipAddress: String) = {
		val apiKey = Config.getString("geoipApiKey")

		val (country,state,city,postCode,lat,long,metCode,areaCode,isp, org) =
			Geoip.get(apiKey, ipAddress)

		val messageOne = username + ", that IP's country is " + country +
			", state is " + state + " in the city of " + city
		val messageTwo = username + ", with the post code of " + postCode +
			", the ISP is " + isp + " and belongs to " + org

		Asib.sendMsg(channel, messageOne)
		Asib.sendMsg(channel, messageTwo)
	}

}

