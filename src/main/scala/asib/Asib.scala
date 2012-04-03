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

package asib

import java.io.{BufferedWriter, BufferedReader, OutputStreamWriter, InputStreamReader}
import java.net.{InetAddress, Socket, SocketException}
import java.util.Random
import asib.util.Handler
import asib.util.logger._
import asib.command._
import asib.util.Config
import util.connector._


object Asib {

	protected var nickname = ""

	protected var currentChannels = List[String]()

	val version = "0.1"

	var triggers = Map[ String , List[AbstractCommand] ]()
	var loggers = List[LoggerAbstract]()
	protected var connector: AbstractConnector = null

	Handler.start
	reset

	/**
	 * Returns loggers and triggers to the default value
	 * @return
	 */
	def reset = {
		processLoggers
		processTriggers
	}

	/**
	 * Provides a nice easy way to fetch the current nickname of the bot.
	 *
	 * @return String
	 */
	def nick: String = nickname

	/**
	 * Provides a nice easy way to fetch the current channels
	 *
	 * @return
	 */
	def channels: List[String] = currentChannels


	def connection = connector
	def connection_=(newConnector: AbstractConnector) {connector = newConnector }

	/**
	 *
	 * @return
	 */
	def processTriggers = {
		for ((command, triggerList) <- Config.getMap("triggers")) {
			triggerList.asInstanceOf[List[String]].foreach { triggerClass =>
				addTrigger(command,
				Class.forName(triggerClass.toString).newInstance.asInstanceOf[AbstractCommand])
			}
		}
	}

	/**
	 *
	 * @return
	 */
	def processLoggers = {
		Config.getList("loggers") foreach { loggerClass =>
			addLogger(Class.forName(loggerClass.toString).
					  newInstance.asInstanceOf[LoggerAbstract])
		}
	}

	/**
	 *
	 * @param loggerObject
	 * @return
	 */
	def addLogger(loggerObject: LoggerAbstract) = {
		 loggers ::=  loggerObject
	}

	/**
	 *
	 * @param commandTrigger
	 * @param triggerObject
	 * @return
	 */
	def addTrigger(commandTrigger: String, triggerObject: AbstractCommand) = {
		if (false == triggers.isDefinedAt(commandTrigger)) {
			triggers += commandTrigger ->  List[AbstractCommand](triggerObject)
		} else {
			triggers.get(commandTrigger).get :: List[AbstractCommand](triggerObject)
		}
	}

	/**
	 *
	 * @param ircDataOutput
	 * @return
	 */
	def sendData(message: String) = {
		loggers.foreach(_.log(message))
		connector.send(message)
	}

	/**
	 *
	 * @param channel
	 * @param message
	 * @return
	 */
	def sendMsg(channel: String, message: String) = {
		connector.msg(channel, message)
	}

	/**
	 *
	 * @param channel
	 * @param message
	 * @return
	 */
	def sendNotice(channel: String, message: String) = {
		connector.notice(channel,message)
	}

	/**
	 * Sets the current nickname of the bot.
	 *
	 * @param nick  String
	 */
	def setNick(nick: String) = {
		nickname = nick
		connector.nick(nick)
	}

	/**
	 *
	 * @param channel
	 * @return
	 */
	def joinChannel(channel: String) = {
		currentChannels = channel :: currentChannels
		connector.join(channel)
	}

	/**
	 *
	 * @param channel
	 * @return
	 */
	def partChannel(channel: String) = {
		// Removes the current Channel
		currentChannels = currentChannels diff List(channel)
		connector.part(channel)
	}

	/**
	 *
	 * @param channel
	 * @param username
	 * @param message
	 * @return
	 */
	def kickUser(channel: String, username: String, message: String = "Get out") = {
		connector.kick(channel, username, message)
	}

	/**
	 *
	 * @param args
	 */
	def main(args: Array[String]) = {
		val connectorClass = Config.getString("connector")
		nickname           = Config.getString("nickname")
		connector          = Class.forName(connectorClass).newInstance
																	.asInstanceOf[AbstractConnector]
		connector.connect()
		currentChannels = Config.getString("channel") :: currentChannels
		while (connector.isConnected) {
			val message = connector.receive()
			loggers.foreach(_.log(message.raw))
			if (connector.stayAlive(message) == false) {
				Handler ! message
			}
		}
	}
}