package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class PrimsEagerVertex implements Comparable<PrimsEagerVertex> {

	private final String name;
	private PrimsEagerEdge minEdge;
	private boolean visited;
	private PrimsEagerVertex previous;
	private double distance;
	private final List<PrimsEagerEdge> edges;

	public PrimsEagerVertex(String name) {
		this.name = name;
		this.distance = Double.POSITIVE_INFINITY;
		this.edges = new ArrayList<>();
	}

	public String name() {
		return this.name;
	}

	public PrimsEagerEdge minEdge() {
		return this.minEdge;
	}

	public void changeMinEdge(PrimsEagerEdge minEdge) {
		this.minEdge = minEdge;
	}

	public double distance() {
		return this.distance;
	}

	public void changeDistance(double distance) {
		this.distance = distance;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public PrimsEagerVertex previous() {
		return this.previous;
	}

	public void setPrevious(PrimsEagerVertex previous) {
		this.previous = previous;
	}

	public List<PrimsEagerEdge> edges() {
		return this.edges;
	}

	public void addEdge(PrimsEagerEdge edge) {
		this.edges.add(edge);
	}

	@Override
	public int compareTo(PrimsEagerVertex other) {
		return Double.compare(this.distance, other.distance);
	}
}
