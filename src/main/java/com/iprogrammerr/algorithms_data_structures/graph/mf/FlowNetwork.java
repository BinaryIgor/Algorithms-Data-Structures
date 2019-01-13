package com.iprogrammerr.algorithms_data_structures.graph.mf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FlowNetwork {

	private final Map<Vertex, List<Edge>> verticesEdges;
	private int edges;

	public FlowNetwork() {
		this.verticesEdges = new HashMap<>();
		this.edges = 0;
	}

	public int vertices() {
		return this.verticesEdges.size();
	}

	public int edges() {
		return this.edges;
	}

	public void addEdge(Edge edge) {
		this.verticesEdges.putIfAbsent(edge.start(), new ArrayList<>());
		this.verticesEdges.putIfAbsent(edge.target(), new ArrayList<>());
		this.verticesEdges.get(edge.start()).add(edge);
		this.verticesEdges.get(edge.target()).add(edge);
		++this.edges;
	}

	public List<Edge> edges(Vertex vertex) {
		if (!this.verticesEdges.containsKey(vertex)) {
			throw new RuntimeException(String.format("FlowNetwork does not contain edges of %s vertex", vertex));
		}
		return this.verticesEdges.get(vertex);
	}
}
