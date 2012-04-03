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

package asib.tests.command.user

import org.scalatest.FunSuite
import asib.Asib
import asib.tests.traits.AsibSetup

import asib.command.user.NewsLatest
import com.gu.openplatform.contentapi.Api

class NewsLatestTest extends FunSuite with AsibSetup {

	test("NewsLatest notices to user who requests the latest results") {
		val command = new NewsLatest
		command.execute("icambridge","#icambridge","")
		val rawResults = Api.search.pageSize(5).response.results
		val output     = connector.lastSentMessage.slice(0,5).reverse

		rawResults.zipWithIndex.foreach { case (item,key) =>
			val sentMessage = output(key)
			assert(sentMessage == "NOTICE icambridge :" + item.webTitle + " - " + item.webUrl)
		}
	}

}