package com.iprogrammerr.algorithms_data_structures.graph;

public final class PrimsEagerEdge implements Comparable<PrimsEagerEdge> {

	private final PrimsEagerVertex start;
	private final PrimsEagerVertex end;
	private final double weight;

	public PrimsEagerEdge(PrimsEagerVertex start, PrimsEagerVertex end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public PrimsEagerVertex start() {
		return this.start;
	}

	public PrimsEagerVertex end() {
		return this.end;
	}

	public double weight() {
		return this.weight;
	}

	@Override
	public int compareTo(PrimsEagerEdge other) {
		return Double.compare(this.weight, other.weight);
	}
}
