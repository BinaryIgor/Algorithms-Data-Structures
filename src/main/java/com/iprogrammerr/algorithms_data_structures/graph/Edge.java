package com.iprogrammerr.algorithms_data_structures.graph;

public final class Edge implements Comparable<Edge> {

	private final VertexWithRank start;
	private final VertexWithRank end;
	private final double weight;

	public Edge(VertexWithRank start, VertexWithRank end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public VertexWithRank start() {
		return this.start;
	}

	public VertexWithRank end() {
		return this.end;
	}

	public double weight() {
		return this.weight;
	}

	@Override
	public int compareTo(Edge other) {
		return Double.compare(this.weight, other.weight);
	}
}
