package com.iprogrammerr.algorithms_data_structures.graph;

public final class EdgeWithWeighted {

	private final double weight;
	private WithWeightVertex start;
	private WithWeightVertex target;

	public EdgeWithWeighted(double weight, WithWeightVertex start, WithWeightVertex target) {
		this.weight = weight;
		this.start = start;
		this.target = target;
	}

	public double weight() {
		return this.weight;
	}

	public WithWeightVertex start() {
		return this.start;
	}

	public WithWeightVertex target() {
		return this.target;
	}
}
