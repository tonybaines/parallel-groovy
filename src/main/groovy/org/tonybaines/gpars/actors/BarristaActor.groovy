package org.tonybaines.gpars.actors

import java.util.logging.Logger;

import groovyx.gpars.actor.DefaultActor;

class BarristaActor extends DefaultActor {
	Logger log = Logger.getLogger("Barrista")
	void act() {
        loop {
			react {
				log.info "Received a request to make: ${it.drink}"
				make(it)
				log.info "Made: ${it.drink}."
				it.customer << it.drink
			}
		}
	}

	def make(drink) {
		switch (drink) {
			case 'Latte': Thread.sleep(2000); break
			case 'Mocha': Thread.sleep(2000); break
			case 'American': Thread.sleep(1000); break
			case 'Instant': Thread.sleep(200); break
		}
	}
}
