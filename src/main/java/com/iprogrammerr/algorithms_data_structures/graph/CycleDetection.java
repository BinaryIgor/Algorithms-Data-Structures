package com.iprogrammerr.algorithms_data_structures.graph;

public final class CycleDetection<T> {

	private final VisitableVertex<T> root;

	public CycleDetection(VisitableVertex<T> root) {
		this.root = root;
	}

	public void detect() {
		dfs(this.root);
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
