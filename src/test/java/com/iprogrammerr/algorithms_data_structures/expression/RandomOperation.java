package com.iprogrammerr.algorithms_data_structures.expression;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;

public class RandomOperation implements Initialization<String> {

	private final Random random;

	public RandomOperation(Random random) {
		this.random = random;
	}

	public RandomOperation() {
		this(new Random());
	}

	@Override
	public String value() {
		int r = this.random.nextInt(3);
		String op;
		if (r == 0) {
			op = "+";
		} else if (r == 1) {
			op = "-";
		} else if (r == 2) {
			op = "*";
		} else if (r == 3) {
			op = "/";
		} else {
			op = "^";
		}
		return op;
	}

}
