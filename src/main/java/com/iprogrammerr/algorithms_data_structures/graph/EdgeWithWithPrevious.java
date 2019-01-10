package com.iprogrammerr.algorithms_data_structures.graph;

public class EdgeWithWithPrevious implements Comparable<EdgeWithWithPrevious> {

	private final VertexWithPrevious start;
	private final VertexWithPrevious end;
	private final double weight;

	public EdgeWithWithPrevious(VertexWithPrevious start, VertexWithPrevious end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public VertexWithPrevious start() {
		return this.start;
	}

	public VertexWithPrevious end() {
		return this.end;
	}

	public double weight() {
		return this.weight;
	}

	public int compareTo(EdgeWithWithPrevious other) {
		return Double.compare(this.weight, other.weight);
	}

}
