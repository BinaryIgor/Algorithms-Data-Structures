package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.List;
import java.util.PriorityQueue;

public class PrimsEagerAlgorithm {

	private final List<PrimsEagerVertex> vertices;
	private PriorityQueue<PrimsEagerVertex> heap;

	public PrimsEagerAlgorithm(List<PrimsEagerVertex> vertices) {
		this.vertices = vertices;
		this.heap = new PriorityQueue<>();
	}

	private void createMst() {
		for (PrimsEagerVertex v : this.vertices) {
			if (!v.isVisited()) {
				makePrimsJarnik(v);
			}
		}
	}

	private void makePrimsJarnik(PrimsEagerVertex vertex) {
		vertex.changeDistance(0);
		this.heap.add(vertex);
		while (!this.heap.isEmpty()) {
			PrimsEagerVertex v = this.heap.remove();
			scanVertex(v);
		}
	}

	private void scanVertex(PrimsEagerVertex vertex) {
		vertex.setVisited(true);
		for (PrimsEagerEdge e : vertex.edges()) {
			PrimsEagerVertex v = e.end();
			if (v.isVisited()) {
				continue;
			}
			if (e.weight() < v.distance()) {
				v.changeDistance(e.weight());
				v.changeMinEdge(e);
				if (this.heap.contains(v)) {
					this.heap.remove(v);
				}
				this.heap.add(v);
			}
		}
	}

	public void showMst() {
		createMst();
		for (PrimsEagerVertex vertex : this.vertices) {
			if (vertex.minEdge() != null) {
				PrimsEagerEdge e = vertex.minEdge();
				System.out.println(String.format("Edge: %s - %s", e.start().name(), e.end().name()));
			}
		}
	}
}
