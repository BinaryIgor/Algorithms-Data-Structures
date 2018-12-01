package com.iprogrammerr.algorithms_data_structures.initialization;

public final class NextPrimeNumber implements Initialization<Integer> {

	private int number;

	public NextPrimeNumber(int number) {
		if (number < 1) {
			throw new RuntimeException(String.format("%d is not a natural number", number));
		}
		this.number = number;
	}

	@Override
	public Integer value() {
		while (!isPrime(this.number)) {
			if (this.number == Integer.MAX_VALUE) {
				this.number = 2;
				break;
			} else {
				++this.number;
			}
		}
		return this.number++;
	}

	private boolean isPrime(int number) {
		boolean prime;
		if (number == 1) {
			prime = false;
		} else {
			prime = true;
			for (int i = 2; i <= (int) Math.sqrt(number); ++i) {
				if ((number % i) == 0) {
					prime = false;
					break;
				}
			}
		}
		return prime;
	}
}
