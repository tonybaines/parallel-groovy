package org.tonybaines.gpars.actors

import groovyx.gpars.actor.DefaultActor;
import java.util.logging.Logger

class CustomerActor extends DefaultActor {
	final String drink
	final SalesActor sales
	Logger log
	
	public CustomerActor(String drink, SalesActor sales) {
		this.drink = drink
		this.sales = sales
		log = Logger.getLogger("$drink Customer")
	}
	
	void act() {
		log.info "Asking for my $drink"
		sales.send(new Order(customer: this, drink: drink))
		loop {
			react {
				// Waiting to pay
				log.info "[$drink Customer] Received a request to pay up $it"
				reply(1.0)
				react { coffee ->
					// Now waiting for my coffee
					if (coffee == drink) {log.info "[$drink Customer] Thanks for my $coffee"; stop()}
					else log.warning "[$drink Customer] No thanks, mine's a $drink.  Not a $coffee"
				}
			}
		}
	}

}
