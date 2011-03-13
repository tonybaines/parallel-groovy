package org.tonybaines.gpars.collections

import org.tonybaines.gpars.BingNews;

import spock.lang.Specification;
import spock.lang.Timeout;
import static groovyx.gpars.GParsPool.*

class ParallelCollectionsSpec extends Specification {

	@Timeout(5) // should fail
	def "processing results sequentially from the Bing news feed"() {
		expect:
		def feed = BingNews.feed
		def linkTexts = feed.channel.item.collect { item ->
			item.link.text().toURL().text
		}
	}

	@Timeout(5)
	def "processing results in parallel from the Bing news feed"() {
		expect:
		def feed = BingNews.feed
		// Metaclass magic to add the parallel collection methods
		// Uses ParallelArray-based (from JSR-166y) implementation
		// (not Java Threads)
		withPool {
			def linkTexts = feed.channel.item.collectParallel { item ->
				item.link.text().toURL().text
			}
		}
	}
}
