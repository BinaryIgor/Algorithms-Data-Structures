package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class UndirectedGraph {

	private final List<PrimsEagerVertex> vertices;
	private final List<PrimsEagerEdge> edges;

	public UndirectedGraph() {
		this.vertices = new ArrayList<>();
		this.edges = new ArrayList<>();
	}

	public void addVertex(PrimsEagerVertex vertex) {
		this.vertices.add(vertex);
	}

	public void addVertices(PrimsEagerVertex... vertices) {
		for (PrimsEagerVertex v : vertices) {
			addVertex(v);
		}
	}

	public void addEdge(PrimsEagerEdge edge) {
		this.vertices.get(this.vertices.indexOf(edge.start()))
				.addEdge(new PrimsEagerEdge(edge.start(), edge.end(), edge.weight()));
		this.vertices.get(this.vertices.indexOf(edge.end()))
				.addEdge(new PrimsEagerEdge(edge.end(), edge.start(), edge.weight()));
	}

	public void addEdges(PrimsEagerEdge... edges) {
		for (PrimsEagerEdge e : edges) {
			addEdge(e);
		}
	}

	public List<PrimsEagerVertex> vertices() {
		return this.vertices;
	}

	public List<PrimsEagerEdge> edges() {
		return this.edges;
	}
}
