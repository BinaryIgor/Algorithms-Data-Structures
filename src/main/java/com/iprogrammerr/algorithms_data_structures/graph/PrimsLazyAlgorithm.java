package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public final class PrimsLazyAlgorithm {

	private final List<VertexWithPrevious> unvisited;
	private final List<EdgeWithWithPrevious> mst;
	private final PriorityQueue<EdgeWithWithPrevious> edges;
	private double cost;

	public PrimsLazyAlgorithm(List<VertexWithPrevious> vertices) {
		this.unvisited = new ArrayList<>(vertices);
		this.mst = new ArrayList<>();
		this.edges = new PriorityQueue<>();
	}

	public List<EdgeWithWithPrevious> mst(VertexWithPrevious vertex) {
		this.unvisited.remove(vertex);
		while (!this.unvisited.isEmpty()) {
			for (EdgeWithWithPrevious e : vertex.edges()) {
				if (this.unvisited.contains(e.end())) {
					this.edges.add(e);
				}
			}
			EdgeWithWithPrevious me = this.edges.remove();
			this.mst.add(me);
			this.cost += me.weight();
			vertex = me.end();
			this.unvisited.remove(vertex);
		}
		return this.mst;
	}

	public double cost() {
		return this.cost;
	}
}
