package com.iprogrammerr.algorithms_data_structures.graph.scc;

import java.util.ArrayList;
import java.util.List;

public final class KosarajuGraph {

	private final List<KosarajuVertex> vertices;
	private final List<KosarajuEdge> edges;

	public KosarajuGraph(List<KosarajuVertex> vertices, List<KosarajuEdge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public List<KosarajuVertex> vertices() {
		return this.vertices;
	}

	public List<KosarajuEdge> edges() {
		return this.edges;
	}

	public KosarajuGraph transposed() {
		List<KosarajuVertex> vertices = new ArrayList<>(this.vertices);
		List<KosarajuEdge> edges = new ArrayList<>(this.edges);
		for (KosarajuEdge e : this.edges) {
			vertices.get(vertices.indexOf(e.target())).addNeighbor(e.start());
		}
		return new KosarajuGraph(vertices, edges);
	}
}
