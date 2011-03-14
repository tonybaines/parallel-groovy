package org.tonybaines.gpars.actors

import java.util.logging.Logger;

import groovyx.gpars.actor.DefaultActor;

class SalesActor extends DefaultActor {
	Logger log = Logger.getLogger("Sales")
	BarristaActor barrista
	
	public SalesActor(BarristaActor barrista) {
		this.barrista = barrista
	}
	
	void act() {
        loop {
			react {
				// This model is stateless - choose how to handle each type of message as it arrives
				if (it instanceof Order) {
					log.info "[Sales] Recieved an order for: ${it.drink}"
					barrista << it
					reply(1.0)
				}
				else if (it instanceof BigDecimal) {
					log.info "[Sales] Thanks, wait for your coffee over there"
				}
			}
		}
	}

}
