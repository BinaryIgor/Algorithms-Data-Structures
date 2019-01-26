package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.Stack;

public final class TopologicalOrdering<T> {

	private final VertexWithData<T> root;

	public TopologicalOrdering(VertexWithData<T> root) {
		this.root = root;
	}

	public TopologicalOrdering(T root) {
		this(new VertexWithData<>(root));
	}

	private void traverse(VertexWithData<T> root, Stack<VertexWithData<T>> stack) {
		root.visit();
		for (VertexWithData<T> v : root.neighbors()) {
			if (!v.visited()) {
				v.visit();
				traverse(v, stack);
			}
		}
		stack.push(root);
	}

	public Stack<VertexWithData<T>> stack() {
		Stack<VertexWithData<T>> stack = new Stack<>();
		traverse(this.root, stack);
		return stack;
	}
}
