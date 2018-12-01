package com.iprogrammerr.algorithms_data_structures.random;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class UniqueRandomizedIntegers implements RandomizedMultiplicity<Integer> {

    private final Randomized<Integer> randomized;

    public UniqueRandomizedIntegers(Randomized<Integer> randomized) {
	this.randomized = randomized;
    }

    public UniqueRandomizedIntegers(int min, int max) {
	this(new RandomizedInteger(min > max ? max : min, max < min ? min : max));
    }

    public UniqueRandomizedIntegers() {
	this((-Integer.MAX_VALUE / 2), (Integer.MAX_VALUE / 2));
    }

    @Override
    public Iterator<Integer> values(int size) {
	Set<Integer> random = new HashSet<>(size);
	for (int i = 0; i < size; ++i) {
	    int value = randomized.value();
	    while (random.contains(value)) {
		value = randomized.value();
	    }
	    random.add(value);
	}
	return random.iterator();
    }

}
