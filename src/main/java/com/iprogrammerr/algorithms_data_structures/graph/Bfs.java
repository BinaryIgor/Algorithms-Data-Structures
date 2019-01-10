package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.LinkedList;
import java.util.Queue;

public final class Bfs<T> implements Traversable {

	private final VertexWithData<T> root;

	public Bfs(VertexWithData<T> root) {
		this.root = root;
	}

	public Bfs(T root) {
		this(new VertexWithData<>(root));
	}

	@Override
	public void traverse() {
		Queue<VertexWithData<T>> queue = new LinkedList<>();
		queue.add(this.root);
		this.root.visit();
		while (!queue.isEmpty()) {
			VertexWithData<T> cv = queue.poll();
			System.out.println(cv);
			for (VertexWithData<T> v : cv.neighbors()) {
				if (!v.visited()) {
					v.visit();
					queue.add(v);
				}
			}
		}
	}
}
