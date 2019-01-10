package com.iprogrammerr.algorithms_data_structures.graph.scc;

public final class KosarajuAlgorithm {

	private int[] componentsIds;
	private int count;
	private boolean[] marked;

	public KosarajuAlgorithm(KosarajuGraph graph) {
		this.marked = new boolean[graph.vertices().size()];
		this.componentsIds = new int[graph.vertices().size()];
		for (KosarajuVertex vertex : new DepthFirstOrder(graph.transposed()).stack()) {
			if (!this.marked[vertex.id()]) {
				dfs(vertex);
				++this.count;
			}
		}
	}

	private void dfs(KosarajuVertex vertex) {
		this.marked[vertex.id()] = true;
		this.componentsIds[vertex.id()] = this.count;
		vertex.setComponentId(this.count);
		for (KosarajuVertex v : vertex.neighbors()) {
			if (!this.marked[v.id()]) {
				dfs(v);
			}
		}
	}

	public int count() {
		return this.count;
	}
}
