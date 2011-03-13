package org.tonybaines.gpars

class BingNews {
	public static getFeed() {
		new XmlSlurper().parseText("http://api.bing.com/rss.aspx?Source=News&Market=en-GB&Version=2.0&Query=".toURL().text)
	}
}
