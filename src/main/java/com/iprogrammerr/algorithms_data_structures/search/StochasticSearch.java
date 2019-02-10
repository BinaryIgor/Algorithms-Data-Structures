package com.iprogrammerr.algorithms_data_structures.search;

import java.util.List;
import java.util.Random;

public class StochasticSearch<T extends Comparable<T>> {

	private final Random random;
	private final boolean min;
	private final int part;

	public StochasticSearch(Random random, boolean min, int part) {
		this.random = random;
		this.min = min;
		this.part = part;
	}

	public StochasticSearch(Random random, int part) {
		this(random, false, part);
	}

	public StochasticSearch(Random random, boolean min) {
		this(random, min, 3);
	}

	public StochasticSearch(Random random) {
		this(random, false);
	}

	public T value(List<T> items) {
		if (items.isEmpty()) {
			throw new IllegalArgumentException("Can't find value in empty data set");
		}
		T minOrMax = items.get(0);
		for (int i = 1; i < items.size() / this.part; ++i) {
			T value = items.get(this.random.nextInt(items.size()));
			if (shouldSwap(minOrMax, value)) {
				minOrMax = value;
			}
		}
		return minOrMax;
	}

	private boolean shouldSwap(T oldValue, T newValue) {
		int cmp = oldValue.compareTo(newValue);
		return (this.min && cmp > 0) || (!this.min && cmp < 0);
	}
}
