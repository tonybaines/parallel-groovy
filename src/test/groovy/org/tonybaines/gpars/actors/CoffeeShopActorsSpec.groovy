package org.tonybaines.gpars.actors

import spock.lang.Specification;

class CoffeeShopActorsSpec extends Specification {
	def "buying a cup of coffee"() {
		given:
		def barrista = new BarristaActor()
		def sales = new SalesActor(barrista)
		def customers = ["Latte", "Mocha", "Americano", "Instant"].collect {
			// new customer for each drink 
			new CustomerActor(it, sales)
		}
		when:
		barrista.start()
		sales.start()
		customers*.start()
		then:
		customers*.join()
	}
}