package com.iprogrammerr.algorithms_data_structures.model;

public final class Pair<T> {

	private final T first;
	private final T second;

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T first() {
		return this.first;
	}

	public T second() {
		return this.second;
	}

	@Override
	public String toString() {
		return String.format("{%s, %s}", this.first, this.second);
	}

}
