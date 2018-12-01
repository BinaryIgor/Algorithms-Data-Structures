package com.iprogrammerr.algorithms_data_structures.random;

public final class RandomizedInteger implements Randomized<Integer> {

    private final int min;
    private final int max;
    private int range;

    public RandomizedInteger(int min, int max) {
	this.min = min;
	this.max = max;
	this.range = Integer.MIN_VALUE;
    }

    @Override
    public Integer value() {
	if (this.range == Integer.MIN_VALUE) {
	    this.range = Math.abs(this.max - this.min);
	}
	return this.min + (int) (Math.round(Math.random() * range));
    }

}
