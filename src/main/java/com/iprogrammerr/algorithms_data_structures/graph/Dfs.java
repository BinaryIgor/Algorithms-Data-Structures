package com.iprogrammerr.algorithms_data_structures.graph;

public final class Dfs<T> implements Traversable {

	private final VertexWithData<T> root;

	public Dfs(VertexWithData<T> root) {
		this.root = root;
	}

	public Dfs(T root) {
		this(new VertexWithData<>(root));
	}

	@Override
	public void traverse() {
		traverse(this.root);
	}

	private void traverse(VertexWithData<T> vertex) {
		vertex.visit();
		for (VertexWithData<T> v : vertex.neighbors()) {
			if (!v.visited()) {
				traverse(v);
			}
		}
		System.out.println(vertex);
	}
}
