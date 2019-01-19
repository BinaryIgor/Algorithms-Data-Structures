package com.iprogrammerr.algorithms_data_structures.graph.ts;

import java.util.Random;

public final class City {

	private final int x;
	private final int y;

	public City(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public City(Random random) {
		this.x = random.nextInt(100);
		this.y = random.nextInt(100);
	}

	public City() {
		this(new Random());
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public double distanceTo(City other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

	@Override
	public String toString() {
		return String.format("City: %d, %d", this.x, this.y);
	}
}
