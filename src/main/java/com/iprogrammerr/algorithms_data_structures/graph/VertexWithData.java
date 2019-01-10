package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public final class VertexWithData<T> {

	private final T data;
	private boolean visited;
	private final List<VertexWithData<T>> neighbors;

	public VertexWithData(T data, List<VertexWithData<T>> neighbors) {
		this.data = data;
		this.neighbors = neighbors;
	}

	public VertexWithData(T data) {
		this(data, new ArrayList<>());
	}

	public T data() {
		return this.data;
	}

	public void visit() {
		this.visited = true;
	}

	public boolean visited() {
		return this.visited;
	}

	@SafeVarargs
	public final void addNeighbors(VertexWithData<T>... neighbors) {
		for (VertexWithData<T> n : neighbors) {
			this.neighbors.add(n);
		}
	}

	public List<VertexWithData<T>> neighbors() {
		return this.neighbors;
	}

	@Override
	public String toString() {
		return "Vertex [data=" + data + "]";
	}
}
