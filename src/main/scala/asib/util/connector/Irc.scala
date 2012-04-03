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

package asib.util.connector

import java.io.{BufferedWriter, BufferedReader, OutputStreamWriter, InputStreamReader}
import java.net.{InetAddress, Socket, SocketException}
import asib.util.Config
import asib.util.Message
import asib.util.regex.IrcRegex

class Irc extends AbstractConnector {

	protected var out: BufferedWriter = null

	protected var in:  BufferedReader = null

	protected var connection: Socket     = null

	def isConnected: Boolean =  { connection.isConnected() }

	/**
	 *
	 */
	def connect() = {
		connection  = new Socket(Config.getString("server"), Config.getInt("port"))
		out         = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))
		in          = new BufferedReader(new InputStreamReader(connection.getInputStream()))

		nick(Config.getString("nickname"))
		val ircUser = Config.getString("ident") + " \"\" \"\" :" + Config.getString("realName")
		send("USER " + ircUser)
		join(Config.getString("channel"))
	}

	/**
	 *
	 * @param message
	 */
	def send(message: String) = {

		out.write(message)
		out.newLine()
		out.flush()
	}

	/**
	 *
	 */
	def receive(): Message = {
		format(in.readLine())
	}

	/**
	 *
	 * @param nick
	 */
	def nick(nick: String) = {
		send("NICK " + nick)
	}

	/**
	 *
	 * @param channel
	 */
	def join(channel: String) = {
		send("JOIN " + channel)
	}

	/**
	 *
	 * @param channel
	 * @param message
	 */
	def part(channel: String, message: String) = {
		send("PART " + channel)
	}

	/**
	 *
	 * @param channel
	 * @param username
	 * @param message
	 */
	def kick(channel: String, username: String, message: String = "") = {
		// Set a default lulz comment
		var outMessage = message
		if (outMessage == "") {
			outMessage = "Homer no function beer well without."
		}
		send("KICK " + channel + " " + username + " :" + outMessage)
	}

	/**
	 *
	 * @param channel
	 * @param message
	 * @return
	 */
	def msg(channel: String, message: String) = {
		send("PRIVMSG " + channel + " :" + message)
	}

	/**
	 *
	 * @param channel
	 * @param message
	 * @return
	 */
	def notice(channel: String, message: String) = {
		send("NOTICE " + channel + " :" + message)
	}

	/**
	 *
	 * @param line
	 * @return Boolean
	 */
	def stayAlive(message: Message): Boolean = {
		if (message.raw.substring(0, 4).equalsIgnoreCase("ping")) {
			send("PONG " + message.raw.substring(5))
		}
		message.raw.substring(0, 4).equalsIgnoreCase("ping")
	}

	def format(line: String): Message = {
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

		val message = line match {
			case msgRegex(username,host,channel,content) => Message("PRIVMSG", username, channel,
				content)
			case noticeRegex(username,host,channel,content) => Message("NOTICE", username ,
				channel,content)
			case joinRegex(username,host,channel) => Message("JOIN", username, channel)
			case partRegex(username,host,channel) => Message("PART", username, channel)
			case kickRegex(username,host,channel, victim, content) => 
												Message("PART", username, channel,victim,content)
			case quitRegex(username,host,content) => Message("QUIT",username,content)
			case userModeRegex(username,subject,mode) => {
				val message = Message("MODE",username)
			   	message.subject = subject
				message.mode
				message
			}
			case chanUserModeRegex(username,host,channel,mode,subject) =>{
				val message = Message(username, channel)
				message.subject = subject
				message.mode
				message
			} 
			case _ => new Message
		}
		message.raw = line
		message
	}
}