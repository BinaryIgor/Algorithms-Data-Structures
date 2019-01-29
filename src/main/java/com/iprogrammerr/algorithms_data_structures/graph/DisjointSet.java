package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.List;

public final class DisjointSet {

	private final List<VertexWithRank> items;

	public DisjointSet(List<VertexWithRank> items) {
		this.items = items;
	}

	public int index(VertexWithRank vertex) {
		VertexWithRank root = root(vertex);
		VertexWithRank current = vertex;
		while (current != root) {
			VertexWithRank tmp = current.parent();
			current.changeParent(root);
			current = tmp;
		}
		return root.id();
	}

	private VertexWithRank root(VertexWithRank vertex) {
		VertexWithRank v = vertex;
		while (v.parent() != null) {
			v = v.parent();
		}
		return v;
	}

	public void unite(VertexWithRank first, VertexWithRank second) {
		int idx1 = index(first);
		int idx2 = index(second);
		if (idx1 != idx2) {
			VertexWithRank root1 = this.items.get(idx1);
			VertexWithRank root2 = this.items.get(idx2);
			if (root1.rank() < root2.rank()) {
				root1.changeParent(root2);
			} else {
				root2.changeParent(root1);
				if (root2.rank() == root1.rank()) {
					root2.changeRank(root2.rank() + 1);
				}
			}
		}
	}
}
