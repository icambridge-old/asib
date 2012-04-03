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

package asib.util.logger

import asib.Asib
import asib.util.regex.IrcRegex

class ConsoleFormatted extends LoggerAbstract {

	def log(line: String) = {

		val quitRegex         = IrcRegex.quit
		val msgRegex          = IrcRegex.msg
		val noticeRegex       = IrcRegex.notice
		val kickRegex         = IrcRegex.kick
		val joinRegex         = IrcRegex.join
		val partRegex         = IrcRegex.part
		val userModeRegex     = IrcRegex.userMode
		val chanUserModeRegex = IrcRegex.chanUserMode
		val chanModeRegex     = IrcRegex.chanMode
		val outMsgRegex       = IrcRegex.outMsg
		val outNoticeRegex    = IrcRegex.outNotice

		var matched = "some"
		line match {
			case msgRegex(username,host,channel,message) => privmsg(username,channel,message)
			case noticeRegex(username,host,channel,message) => notice(username,channel,message)
			case joinRegex(username,host,channel) => join(username,channel)
			case partRegex(username,host,channel) => part(username,channel)
			case kickRegex(username,host,channel, victim, message) => kick(username,channel,
																		   victim,message)
			case quitRegex(username,host,message) => quit(username,message)
			case userModeRegex(username,victim,mode) => userMode(username,victim,mode)
			case chanUserModeRegex(username,host,channel,mode,victim) => chanUserMode(username,
																			channel, mode, victim)
			case _ => matched = "none"
		}
		
		// Have to use multiple matches due to https://issues.scala-lang.org/browse/SI-1133
		if (matched == "none") {
			line match {
				case chanModeRegex(username,host,channel,mode) => chanMode(username,channel,mode)
				case outMsgRegex(channel, message) => privmsg(Asib.nick, channel, message)
				case outNoticeRegex(channel, message) => notice(Asib.nick, channel, message)
				case _ => println(line)
			}
		}
	}

	def privmsg(username: String, channel: String,message: String) = {
		print("<")
		if (Asib.nick != channel) {
			print(channel+"/")
		}
		println(username + "> " + message)
	}

	def notice(username: String, channel: String,message: String) = {
		print("~")
		if (Asib.nick != channel) {
			print(channel+"/")
		}
		println(username + "~ " + message)
	}

	def join(username: String, channel: String) = {
		println(username + " has joined " + channel)
	}

	def part(username: String, channel: String) = {
		println(username + " has parted " + channel)
	}

	def kick(username: String, channel: String,victim: String,message: String) = {
	   	println(username + " has kicked " + victim + " from " + channel + " (" + message + ")")
	}

	def quit(username: String, message: String) = {
		println(username + " has quit (" + message + ")")
	}
	def userMode(username: String, victim: String, mode: String) = {
		println(username + " has set " + mode + " on " + victim)
	}

	def chanUserMode(username: String, channel: String, mode: String, victim: String) = {
		println(username + " has set " + mode + " in " + channel + " on " + victim)
	}

	def chanMode(username: String, channel: String, mode: String) {
		println(username + " has set " + mode + " on " + channel)
	}
}