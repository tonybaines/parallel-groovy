package org.tonybaines.gpars.collections

import org.tonybaines.gpars.HardWork;

import spock.lang.Specification;
import spock.lang.Timeout;
import static groovyx.gpars.GParsPool.*

class ParallelCollectionsSpec extends Specification {

	@Timeout(5) // should fail
	def "processing results sequentially"() {
		expect:
		def results = (2000..2500).collect { i ->
			HardWork.factorial(i)
		}
	}

	@Timeout(5)
	def "processing results in parallel"() {
		expect:
		// Metaclass magic to add the parallel collection methods
		// Uses ParallelArray-based (from JSR-166y) implementation
		// (not Java Threads)
		withPool {
			def results = (2000..2500).collect { i ->
				HardWork.factorial(i)
			}
		}
	}
}
