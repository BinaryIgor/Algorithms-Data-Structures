package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KruskalAlgorithm {

	private final List<VertexWithRank> vertices;
	private final List<Edge> edges;

	public KruskalAlgorithm(List<VertexWithRank> vertices, List<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public List<Edge> mst() {
		DisjointSet set = new DisjointSet(this.vertices);
		List<Edge> mst = new ArrayList<>();
		Collections.sort(this.edges);
		for (Edge e : this.edges) {
			VertexWithRank start = e.start();
			VertexWithRank end = e.end();
			if (set.index(start) != set.index(end)) {
				mst.add(e);
				set.unite(start, end);
			}
		}
		return mst;
	}
}
