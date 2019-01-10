package com.iprogrammerr.algorithms_data_structures.graph.scc;

import java.util.ArrayList;
import java.util.List;

public final class KosarajuVertex {

	private final int id;
	private final String name;
	private boolean visited;
	private final List<KosarajuVertex> neighbors;
	private int componentId;

	public KosarajuVertex(int id, String name) {
		this.id = id;
		this.name = name;
		this.neighbors = new ArrayList<>();
	}

	public int id() {
		return this.id;
	}

	public String name() {
		return this.name;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public List<KosarajuVertex> neighbors() {
		return this.neighbors;
	}

	public int componentId() {
		return this.componentId;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public void addNeighbor(KosarajuVertex vertex) {
		this.neighbors.add(vertex);
	}
}
