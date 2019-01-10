package com.iprogrammerr.algorithms_data_structures.graph.scc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class TarjanAlgorithm {

	private final Stack<TarjanVertex> stack;
	private final List<TarjanVertex> vertices;
	private final List<List<TarjanVertex>> stronglyConnectedComponents;
	private int lowLink;

	public TarjanAlgorithm(List<TarjanVertex> vertices) {
		this.stack = new Stack<>();
		this.vertices = vertices;
		this.stronglyConnectedComponents = new ArrayList<>();
	}

	public void run() {
		for (TarjanVertex v : this.vertices) {
			if (!v.isVisited()) {
				dfs(v);
			}
		}
		System.out.println(this.stronglyConnectedComponents);
	}

	private void dfs(TarjanVertex vertex) {
		vertex.setLowLink(this.lowLink++);
		System.out.println(String.format("Visiting vertex...%s with lowLink %d", vertex.name(), vertex.lowLink()));
		vertex.setVisited(true);
		this.stack.add(vertex);
		boolean root = true;
		for (TarjanVertex v : vertex.neighbors()) {
			if (!v.isVisited()) {
				System.out.println(String.format("Recursively visiting vertex = %s", v.name()));
				dfs(v);
			}
			if (vertex.lowLink() > v.lowLink()) {
				System.out.println(
						String.format("Because vertex %s lowLink(value: %d) is greater than %d it is not a scc)",
								vertex.name(), vertex.lowLink(), v.lowLink()));
				vertex.setLowLink(v.lowLink());
				root = false;
			}
		}
		if (root) {
			System.out.println(String.format("Vertex %s is the root of a scc", vertex.name()));
			List<TarjanVertex> sccs = new ArrayList<>();
			while (true) {
				TarjanVertex last = this.stack.pop();
				System.out.println(String.format("So vertex %s is in scc: %d", last.name(),
						this.stronglyConnectedComponents.size() + 1));
				sccs.add(last);
				last.setLowLink(Integer.MAX_VALUE);
				if (vertex.name().equals(last.name())) {
					break;
				}
			}
			this.stronglyConnectedComponents.add(sccs);
		}
	}
}
