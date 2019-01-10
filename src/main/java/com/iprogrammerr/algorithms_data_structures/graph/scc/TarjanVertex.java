package com.iprogrammerr.algorithms_data_structures.graph.scc;

import java.util.ArrayList;
import java.util.List;

public final class TarjanVertex {

	private final String name;
	private final List<TarjanVertex> neighbors;
	private TarjanVertex predecessor;
	private boolean visited;
	private int lowLink;

	public TarjanVertex(String name) {
		this.name = name;
		this.neighbors = new ArrayList<>();
	}

	public String name() {
		return this.name;
	}

	public List<TarjanVertex> neighbors() {
		return this.neighbors;
	}

	public void addNeighbor(TarjanVertex neighbor) {
		this.neighbors.add(neighbor);
	}

	public TarjanVertex predecessor() {
		return this.predecessor;
	}

	public void setPredecessor(TarjanVertex predecessor) {
		this.predecessor = predecessor;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int lowLink() {
		return this.lowLink;
	}

	public void setLowLink(int lowLink) {
		this.lowLink = lowLink;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
