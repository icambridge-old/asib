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

package asib.tests.command.user.news.search

import org.scalatest.FunSuite
import asib.Asib
import asib.tests.traits.AsibSetup

import asib.command.user.news.search.Guardian
import com.gu.openplatform.contentapi.Api

class GuardianTest extends FunSuite with AsibSetup {

	test("Api wrapper results the same five results") { 		
		val phrase   = "Football"
		val results  = Api.search.pageSize(5).q(phrase)
		val guardian = new Guardian
		val output   = guardian.search(phrase)
		
		results.zipWithIndex.foreach { case (rawItem,key) =>
			val parsedItem = output(key)
			assert(parsedItem.get("title").get == rawItem.webTitle)
			assert(parsedItem.get("url").get   == rawItem.webUrl)
		}
		
	}

}