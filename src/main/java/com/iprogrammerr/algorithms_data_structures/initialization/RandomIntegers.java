package com.iprogrammerr.algorithms_data_structures.initialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomIntegers implements Initialization<List<Integer>> {

	private final Random random;
	private final int size;

	public RandomIntegers(Random random, int size) {
		this.random = random;
		this.size = size;
	}

	public RandomIntegers(int size) {
		this(new Random(), size);
	}

	@Override
	public List<Integer> value() {
		List<Integer> ints = new ArrayList<>();
		for (int i = 0; i < this.size; ++i) {
			ints.add(this.random.nextInt());
		}
		return ints;
	}
}
