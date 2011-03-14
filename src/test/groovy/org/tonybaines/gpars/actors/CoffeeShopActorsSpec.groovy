package org.tonybaines.gpars.actors

import spock.lang.Specification;

class CoffeeShopActorsSpec extends Specification {
	/*
	 * Customers give their orders to the Sales clerk
	 * The Sales clerk order on to the Barrista and asks the Customer for payment
	 * The Customer waits for the Barrista to give them their coffee
	 */
	def "buying a cup of coffee"() {
		given:
		def barrista = new BarristaActor()
		def sales = new SalesActor(barrista)
		def customers = ["Latte", "Mocha", "Americano", "Instant"].collect {
			// new customer for each drink 
			new CustomerActor(it, sales)
		}
		when: "Actors are started"
		barrista.start()
		sales.start()
		customers*.start()
		then: "Wait for all the customers to get their drinks"
		customers*.join()
	}
}