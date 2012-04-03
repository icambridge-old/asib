
package asib.command.user.news.search

import com.gu.openplatform.contentapi.Api

class Guardian extends AbstractSite {

	def search(phrase: String): List[Map[String,String]] = {
		val results = Api.search.pageSize(5).q(phrase)
		var output = List[Map[String,String]]()

		results foreach { item =>
	    	output ::= Map[String,String]( "title" -> item.webTitle,
										   "url"   -> item.webUrl)
		}

		output.reverse
	}

}