package com.iprogrammerr.algorithms_data_structures.graph.mf;

public final class Edge {

	private Vertex start;
	private Vertex target;
	private double flow;
	private final double capacity;

	public Edge(Vertex start, Vertex target, double capacity) {
		this.start = start;
		this.target = target;
		this.flow = 0;
		this.capacity = capacity;
	}

	public Edge(Edge source) {
		this(source.start(), source.target(), source.capacity());
	}

	public Vertex start() {
		return this.start;
	}

	public Vertex target() {
		return this.target;
	}

	public double flow() {
		return this.flow;
	}

	public double capacity() {
		return this.capacity;
	}

	public Vertex other(Vertex vertex) {
		return this.start.equals(vertex) ? this.target : this.start;
	}

	public double residualFlow(Vertex vertex) {
		double rc;
		if (this.start.equals(vertex)) {
			rc = this.flow;
		} else {
			rc = this.capacity - this.flow;
		}
		return rc;
	}

	public void addResidualFlow(Vertex vertex, double delta) {
		if (this.start.equals(vertex)) {
			this.flow = this.flow - delta;
		} else {
			this.flow = this.flow + delta;
		}
	}
}
