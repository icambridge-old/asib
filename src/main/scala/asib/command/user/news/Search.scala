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

package asib.command.user.news

import asib.Asib
import asib.command.user.AbstractUserCommand
import asib.command.user.news.search._

class Search extends AbstractUserCommand {

	val helpMessage = "Searches the news for the keyword - !nsearch <site> <phrase> - Do " +
					  "!nsearch list to list news site."

	val newsSites   = List("guardian")

	def execute(username: String, channel: String, args: String) = {

		val argsSplit = args.split(" ")

		if ((argsSplit.isDefinedAt(0) == false || argsSplit(0) == "") ||
			(argsSplit.isDefinedAt(1) == false && argsSplit(0) != "list")) {
			Asib.sendMsg(channel, username + ", the usage of this command is !nsearch <site> " +
				"<phrase> do !nsearch list to list the sites")

		} else {
			val siteName = argsSplit(0).toLowerCase
			val phrase = argsSplit.reverse.dropRight(1).reverse.reduceRight(_ + " " + _)

			siteName match {
				case "guardian" => doSearch(channel, username, new Guardian, phrase)
				case "list" =>  list(username)
				case _ => Asib.sendMsg(channel, username + ", Invalid site \"" + siteName + "\"")
			}
		}
	}

	def list(username: String) = {
		newsSites foreach { siteName =>
			Asib.sendNotice(username, "- " + siteName)
		}
	}

	def doSearch(channel: String, username: String, site: AbstractSite, phrase: String) = {
		val results = site.search(phrase)

		if (results.size == 0) {
			Asib.sendMsg(channel, username + ", there were no results for " + phrase)	
		} else {
			Asib.sendMsg(channel, username + ", There was " + results.size + " results for \"" +
								  phrase + "\" which will be send via notice.")
			results foreach { item =>
				Asib.sendNotice(username, item.get("title").get + " - " + item.get("url").get)
			}
		}
	}

}