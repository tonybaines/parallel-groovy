// This is a starting point for a parallel collections example
def result = "http://search.twitter.com/search.atom?q=gpars&result_type=recent".toURL().text
def feed = new XmlSlurper().parseText(result)
def gParsTweets = feed.entry.collect { entry ->
  entry.title.text()
}

gParsTweets.sum { tweet ->
  tweet.size()
}
