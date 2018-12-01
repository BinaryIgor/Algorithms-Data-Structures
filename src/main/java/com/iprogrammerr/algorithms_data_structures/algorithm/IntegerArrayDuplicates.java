package com.iprogrammerr.algorithms_data_structures.algorithm;

public final class IntegerArrayDuplicates implements Algorithm<Integer[]> {

	private final Integer[] source;

	public IntegerArrayDuplicates(Integer[] source) {
		this.source = source;
	}

	@Override
	public Integer[] solution() {
		for (int i = 0; i < this.source.length; ++i) {
			int index = Math.abs(this.source[i]);
			if (index < this.source.length && this.source[index] > 0) {
				this.source[index] = -this.source[index];
			}
		}
		return this.source;
	}
}
