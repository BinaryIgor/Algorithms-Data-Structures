package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class VisitableVertex<T> {

	private final T data;
	private boolean visited;
	private boolean beingVisited;
	private final List<VisitableVertex<T>> neighbors;

	public VisitableVertex(T data, List<VisitableVertex<T>> neighbors) {
		this.data = data;
		this.neighbors = neighbors;
	}

	public VisitableVertex(T data) {
		this(data, new ArrayList<>());
	}

	public void visit() {
		this.visited = true;
	}

	public boolean visited() {
		return this.visited;
	}

	@SafeVarargs
	public final void addNeighbors(VisitableVertex<T>... neighbors) {
		for (VisitableVertex<T> n : neighbors) {
			this.neighbors.add(n);
		}
	}

	public List<VisitableVertex<T>> neighbors() {
		return this.neighbors;
	}

	@Override
	public String toString() {
		return "VisitableVertex [data=" + data + "]";
	}

	public boolean beingVisited() {
		return this.beingVisited;
	}

	public void beginVisiting() {
		this.beingVisited = true;
	}

	public void endVisiting() {
		this.beingVisited = false;
	}
}
