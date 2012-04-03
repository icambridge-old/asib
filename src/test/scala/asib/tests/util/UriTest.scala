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
package asib.tests.util

import org.scalatest.FunSuite
import asib.Asib
import asib.util.Uri
import asib.tests.traits.AsibSetup

class UriTest extends FunSuite with AsibSetup {

	test("Test alphanumerical numbers return as is") {
		val message = "abcdefghijklmnopqrstvqxyz0123456789"
		assert(Uri.encode(message) == message)
	}

	test("Test non alphanumerical chars return uri encoded") {
		val message = "@~]+[!£$+£$£"
		assert(Uri.encode(message) == "%40%7E%5D%2B%5B%21%C2%A3%24%2B%C2%A3%24%C2%A3")
	}
}