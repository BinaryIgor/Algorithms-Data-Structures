package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BellmanFordAlgorithm implements ShortestPathAlgorithm {

	private final WithWeightVertex start;
	private final List<EdgeWithWeighted> edges;
	private final List<WithWeightVertex> vertices;

	public BellmanFordAlgorithm(WithWeightVertex start, List<EdgeWithWeighted> edges, List<WithWeightVertex> vertices) {
		this.start = start;
		this.edges = edges;
		this.vertices = vertices;
	}

	private void compute() {
		this.start.changeDistance(0);
		for (int i = 0; i < this.vertices.size() - 1; ++i) {
			for (EdgeWithWeighted e : this.edges) {
				if (e.start().distance() == Double.MAX_VALUE) {
					continue;
				}
				double distance = e.start().distance() + e.weight();
				if (distance < e.target().distance()) {
					e.target().changeDistance(distance);
					e.target().changePredecessor(e.start());
				}
			}
		}
		for (EdgeWithWeighted e : this.edges) {
			if (e.start().distance() != Double.MAX_VALUE) {
				if (hasCycle(e)) {
					System.out.println("There has been a negative cycle detected!");
					break;
				}
			}
		}
	}

	private boolean hasCycle(EdgeWithWeighted edge) {
		return edge.start().distance() + edge.weight() < edge.target().distance();
	}

	@Override
	public List<WithWeightVertex> solution(WithWeightVertex target) {
		compute();
		if (target.distance() == Double.MAX_VALUE) {
			throw new RuntimeException(
					String.format("There is no path from %s do %s", this.start.name(), target.name()));
		}
		List<WithWeightVertex> solution = new ArrayList<>();
		solution.add(target);
		WithWeightVertex vertex = target.predecessor();
		while (vertex != null) {
			solution.add(vertex);
			vertex = vertex.predecessor();
		}
		Collections.reverse(solution);
		System.out.println(String.format("Found path distance = %.3f", target.distance()));
		return solution;
	}
}