package org.tonybaines.gpars

public class TwitterSearch {
	public static twitterMatchesFor(query) {
		new XmlSlurper().parseText("http://search.twitter.com/search.atom?q=$query&result_type=recent".toURL().text)
	}
}
