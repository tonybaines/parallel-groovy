package org.tonybaines.gpars.collections

import static groovyx.gpars.GParsPool.*

import org.tonybaines.gpars.HardWork

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Timeout

class ParallelCollectionsSpec extends Specification {

	def "processing results sequentially"() {
		given:
		 def sw = new StopWatch()
		when:
		sw.start()
		def results = (2000..2500).collect { i ->
			HardWork.factorial(i)
		}
		sw.stop()
		then:
		results.size() == 501
		println "${sw.elapsed} processing sequentially"
	}

	def "processing results in parallel"() {
		given:
		 def sw = new StopWatch()
		when:
		// Metaclass magic to add the parallel collection methods
		// Uses ParallelArray-based (from JSR-166y) implementation
		// (not Java Threads)
		sw.start()
		def results = []
		withPool {
			results = (2000..2500).collectParallel { i ->
				HardWork.factorial(i)
			}
		}
		sw.stop()
		then:
		results.size() == 501
		println "${sw.elapsed} processing concurrently"
	}

	def "using map/filter/reduce"() {
		when:
		def result = ''
		// Metaclass magic to add the parallel collection methods
		// Uses ParallelArray-based (from JSR-166y) implementation
		// (not Java Threads)
		withPool {
			result = "The quick brown fox jumps over the lazy dog".split(' ').parallel
                    .map {it.toUpperCase()}
                    .filter {it.contains 'O'}
                    .reduce {a,b -> "$a $b"}
		}
		then:
		result == 'BROWN FOX OVER DOG'
	}
}
