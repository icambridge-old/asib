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

import asib.Asib
import dispatch._
import net.liftweb.json.JsonParser
import net.liftweb.json.JsonAST._
import scala.io.Source

object Config {

	protected val json = JsonParser.parse(Source.fromFile("config.json").mkString)

	def getString(name: String): String = {
		(json \ name values).toString
	}

	def getInt(name: String): Int = {
		(json \ name values).toString.toInt
	}

	def getMap(name: String): Map[String, Any] = {
		(json \\ name).asInstanceOf[JObject].values
	}

	def getList(name: String): List[String] = {
		(json \ name).values.asInstanceOf[List[String]]
	}

}