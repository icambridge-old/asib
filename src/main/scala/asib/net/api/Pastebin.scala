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

object Pastebin {

	def create(title: String, code: String): String = {
		val pasteHttp   = new Http()
		val postVars    = Map("api_dev_key" -> Config.getString("pastebinApiKey"),
			"api_paste_private" -> "1",
			"api_paste_expire_date" -> "1M",
			"api_paste_format" -> "text",
			"api_user_key" -> "",
			"api_paste_name" -> title,
			"api_paste_code" -> code,
			"api_option" -> "paste")
		val pasteUrl    = url("http://pastebin.com/api/api_post.php") << postVars
		val pastebinUrl = pasteHttp(pasteUrl as_str)

		pastebinUrl
	}

}