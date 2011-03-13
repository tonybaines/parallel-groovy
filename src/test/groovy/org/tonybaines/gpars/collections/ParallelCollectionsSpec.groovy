package org.tonybaines.gpars.collections

import org.tonybaines.gpars.BingNews;

import spock.lang.Specification;
import spock.lang.Timeout;
import static groovyx.gpars.GParsPool.*

class ParallelCollectionsSpec extends Specification {

	@Timeout(5) // should fail
	def "processing results sequentially from the Bing news feed"() {
		given:
		def feed = BingNews.feed
		def characterCount = 0
		when:
		def linkTexts = feed.channel.item.collect { item ->
			item.link.text().toURL().text
		}
		characterCount = linkTexts.sum { it.size() }
		then:
		characterCount > 100
	}

	@Timeout(5)
	def "processing results in parallel from the Bing news feed"() {
		given:
		def feed = BingNews.feed
		def characterCount = 0
		when:
		// Metaclass magic to add the parallel collection methods
		withPool {
			def linkTexts = feed.channel.item.collectParallel { item ->
				item.link.text().toURL().text
			}
			characterCount = linkTexts.sum { it.size() }
		}
		
		then:
		characterCount > 100
	}
}
