package org.tonybaines

class StopWatch {
	long start = 0
	long end = 0
	def start() {start = System.currentTimeMillis()}
	def stop() {end  = System.currentTimeMillis()}
	def getElapsed() {"${end - start}ms"}
}
