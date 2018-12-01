package com.iprogrammerr.algorithms_data_structures.random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class RandomizedIntegers implements RandomizedMultiplicity<Integer> {

    private final Randomized<Integer> randomized;

    public RandomizedIntegers(Randomized<Integer> randomized) {
	this.randomized = randomized;
    }

    public RandomizedIntegers(int min, int max) {
	this(new RandomizedInteger(min, max));
    }

    public RandomizedIntegers() {
	this(-1000, 1000);
    }

    @Override
    public Iterator<Integer> values(int size) {
	List<Integer> random = new ArrayList<>(size);
	for (int i = 0; i < size; ++i) {
	    random.add(randomized.value());
	}
	return random.iterator();
    }

}
