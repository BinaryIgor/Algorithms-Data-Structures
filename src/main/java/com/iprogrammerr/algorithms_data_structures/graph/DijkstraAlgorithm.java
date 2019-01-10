package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public final class DijkstraAlgorithm implements ShortestPathAlgorithm {

	private final WithWeightVertex source;

	public DijkstraAlgorithm(WithWeightVertex source) {
		this.source = source;
	}

	private void compute() {
		this.source.changeDistance(0);
		PriorityQueue<WithWeightVertex> queue = new PriorityQueue<>();
		queue.add(this.source);
		while (!queue.isEmpty()) {
			WithWeightVertex vertex = queue.poll();
			System.out.println("Considering vertex = " + vertex.name());
			for (EdgeWithWeighted e : vertex.adjacencies()) {
				WithWeightVertex v = e.target();
				double distance = vertex.distance() + e.weight();
				if (distance < v.distance()) {
					queue.remove(v);
					v.changeDistance(distance);
					v.changePredecessor(vertex);
					queue.add(v);
				}
			}
		}
	}

	@Override
	public List<WithWeightVertex> solution(WithWeightVertex target) {
		compute();
		List<WithWeightVertex> shortestPath = new ArrayList<>();
		for (WithWeightVertex v = target; v != null; v = v.predecessor()) {
			shortestPath.add(v);
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}
}
