package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class VertexWithPrevious {

	private final String name;
	private boolean visited;
	private VertexWithPrevious previous;
	private final List<EdgeWithWithPrevious> edges;

	public VertexWithPrevious(String name) {
		this.name = name;
		this.edges = new ArrayList<>();
	}

	public String name() {
		return this.name;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void addEdge(EdgeWithWithPrevious edge) {
		this.edges.add(edge);
	}

	public List<EdgeWithWithPrevious> edges() {
		return this.edges;
	}

	public void changePrevious(VertexWithPrevious previous) {
		this.previous = previous;
	}

	public VertexWithPrevious previous() {
		return this.previous;
	}
}
