package com.iprogrammerr.algorithms_data_structures.graph.scc;

public final class KosarajuEdge {

	private KosarajuVertex start;
	private KosarajuVertex target;
	private double weight;

	public KosarajuEdge(KosarajuVertex start, KosarajuVertex target, double weight) {
		this.start = start;
		this.target = target;
		this.weight = weight;
	}

	public KosarajuVertex start() {
		return this.start;
	}

	public KosarajuVertex target() {
		return this.target;
	}

	public double weight() {
		return this.weight;
	}
}
