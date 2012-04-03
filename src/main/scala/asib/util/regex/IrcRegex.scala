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

package asib.util.regex

object IrcRegex {
	val quit         = """:([a-zA-Z0-9_-|]+)!(.*) QUIT :(.*)""".r
	val msg          = """:([a-zA-Z0-9_-|]+)!(.*) PRIVMSG (.*) :(.*)""".r
	val notice       = """:([a-zA-Z0-9_-|]+)!(.*) NOTICE (.*) :(.*)""".r
	val kick         = """:([a-zA-Z0-9_-|]+)!(.*) KICK (.*) (.*) :(.*)""".r
	val join         = """:([a-zA-Z0-9_-|]+)!(.*) JOIN (.*)""".r
	val part         = """:([a-zA-Z0-9_-|]+)!(.*) PART (.*)""".r
	val userMode     = """:([a-zA-Z0-9_-|]+) MODE (.*) ([-+][a-zA-Z])""".r
	val chanUserMode = """:([a-zA-Z0-9_-|]+)!(.*) MODE (.*) (.*) (.*)""".r
	val chanMode     = """:([a-zA-Z0-9_-|]+)!(.*) MODE (.*) ([-+][a-zA-Z])""".r
	val outMsg       = """PRIVMSG (.*) :(.*)""".r
	val outNotice    = """NOTICE (.*) :(.*)""".r
}
