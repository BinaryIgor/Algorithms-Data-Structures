package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class WithWeightVertex implements Comparable<WithWeightVertex> {

	private String name;
	private List<EdgeWithWeighted> adjacencies;
	private WithWeightVertex predecessor;
	private double distance;

	public WithWeightVertex(String name, double distance) {
		this.name = name;
		this.adjacencies = new ArrayList<>();
		this.distance = distance;
	}

	public WithWeightVertex(String name) {
		this(name, Double.MAX_VALUE);
	}

	public void addNeighbor(EdgeWithWeighted neighbor) {
		this.adjacencies.add(neighbor);
	}

	public void changeDistance(double distance) {
		this.distance = distance;
	}

	public void changePredecessor(WithWeightVertex predecessor) {
		this.predecessor = predecessor;
	}

	public String name() {
		return this.name;
	}

	public double distance() {
		return this.distance;
	}

	public List<EdgeWithWeighted> adjacencies() {
		return this.adjacencies;
	}

	public WithWeightVertex predecessor() {
		return this.predecessor;
	}

	@Override
	public int compareTo(WithWeightVertex vertex) {
		return Double.compare(this.distance, vertex.distance);
	}
}
