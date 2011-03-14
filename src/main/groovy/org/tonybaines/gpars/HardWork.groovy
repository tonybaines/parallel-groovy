package org.tonybaines.gpars

class HardWork {
	public static factorial(BigInteger n) {
		(n==1) ? 1 : (n*factorial(n-1))
	}

}
