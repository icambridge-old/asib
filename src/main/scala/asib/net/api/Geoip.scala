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

package asib.net.api

import asib.util.Config
import dispatch._

object Geoip {

	def get(apiKey: String, ipAddress: String) = {
		val apiHttp  = new Http()
		val apiUrl   = url("http://geoip.maxmind.com/f?l=" + apiKey + "&i=" +
							ipAddress)
		val apiData  = apiHttp(apiUrl as_str)
		val apiSplit = apiData.split(",")
		val country  = apiSplit(0)
		val state    = apiSplit(1)
		val city     = apiSplit(2)
		val postCode = apiSplit(3)
		val lat      = apiSplit(4)
		val long     = apiSplit(5)
		val metCode  = apiSplit(6)
		val areaCode = apiSplit(7)
		val isp      = apiSplit(8)
		val org      = apiSplit(9)

		(country,state,city,postCode,lat,long,metCode,areaCode,isp, org)
	}

}