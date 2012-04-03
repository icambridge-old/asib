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

class Message {
	/**
	 * The username of the user who sends
	 * the command
	 */
	protected var messageUsername = ""
	/**
	 * The content of the message.
	 * - PrivMsg content
	 * - Quit message
	 * - Kick message
	 */
	protected var messageContent  = ""
	/**
	 * The command that is being used in
	 * message
	 */
	protected var messageCommand  = ""
	/**
	 * The username of the subject of the
	 * message.
	 *
	 * - Username of kicked person
	 * - Username of mode modifed person
	 */
	protected var messageSubject   = ""
	/**
	 * The channel in which the message
	 * is from.
	 */
	protected var messageChannel   = ""
	/**
	 * If the message is a private message
	 */
	protected var messagePrivate   = false

	protected var messageMode      = ""

	protected var messageRaw      = ""

	def username = messageUsername
	def username_=(newUsername: String) {messageUsername = newUsername}

	def content = messageContent
	def content_=(newContent: String) {messageContent = newContent}

	def subject = messageSubject
	def subject_=(newSubject: String) {messageSubject = newSubject}

	def channel = messageChannel
	def channel_=(newChannel: String) {messageChannel = newChannel}

	def pm = messagePrivate
	def pm_=(newPm: Boolean) {messagePrivate = newPm}

	def command = messageCommand
	def command_=(newCommand: String) {messageCommand = newCommand}

	def mode = messageMode
	def mode_=(newMode: String) {messageMode = newMode}

	def raw = messageRaw
	def raw_=(newRaw: String) {messageRaw = newRaw}

}

object Message {

	def apply(command: String): Message = {
		val message = new Message
		message.command = command
		message
	}

	def apply(command: String, username: String): Message = {
		val message = apply(command)
		message.username  = username
		message
	}

	def apply(command: String, username: String, channel: String): Message = {
		val message = apply(command,username)
		message.channel = channel
		message
	}

	def apply(command: String, username: String, channel: String,content: String): Message = {
		val message = apply(command,username,channel)
		message.content = content
		message
	}

	def apply(command: String, username: String, channel: String, content: String,
			  subject: String): Message = {
		val message = apply(command,username,channel,content)
		message.subject = subject
		message
	}

}