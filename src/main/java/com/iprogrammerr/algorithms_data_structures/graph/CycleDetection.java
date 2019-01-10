package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.List;

public final class CycleDetection<T> {

	private final List<VisitableVertex<T>> vertices;

	public CycleDetection(List<VisitableVertex<T>> vertices) {
		this.vertices = vertices;
	}

	public void detect() {
		for (VisitableVertex<T> v : this.vertices) {
			if (!v.visited()) {
				dfs(v);
			}
		}
	}

	private void dfs(VisitableVertex<T> root) {
		root.visit();
		root.beginVisiting();
		System.out.println("Visiting vertex..." + root);
		for (VisitableVertex<T> v : root.neighbors()) {
			if (v.beingVisited()) {
				System.out.println("There is a backward edge, with means cycle");
				break;
			}
			if (!v.visited()) {
				dfs(v);
			}
		}
		root.endVisiting();
	}
}
