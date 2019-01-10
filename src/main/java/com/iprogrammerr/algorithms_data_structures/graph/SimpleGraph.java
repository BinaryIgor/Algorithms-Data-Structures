package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.Arrays;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class SimpleGraph implements Graph {

	private final Initialization<List<VertexWithRank>> vertices;
	private final Initialization<List<Edge>> edges;

	public SimpleGraph() {
		this.vertices = new StickyInitialization<>(
				() -> Arrays.asList(new VertexWithRank(0, "A"), new VertexWithRank(1, "B"), new VertexWithRank(2, "C"),
						new VertexWithRank(3, "D"), new VertexWithRank(4, "E"), new VertexWithRank(5, "F"),
						new VertexWithRank(6, "G"), new VertexWithRank(7, "H")));
		this.edges = new StickyInitialization<>(() -> {
			List<VertexWithRank> vertices = this.vertices.value();
			return Arrays.asList(new Edge(vertices.get(0), vertices.get(1), 3),
					new Edge(vertices.get(0), vertices.get(2), 2), new Edge(vertices.get(0), vertices.get(3), 5),
					new Edge(vertices.get(1), vertices.get(5), 13), new Edge(vertices.get(1), vertices.get(3), 2),
					new Edge(vertices.get(2), vertices.get(4), 5), new Edge(vertices.get(2), vertices.get(3), 2),
					new Edge(vertices.get(3), vertices.get(4), 4), new Edge(vertices.get(3), vertices.get(5), 6),
					new Edge(vertices.get(3), vertices.get(6), 3), new Edge(vertices.get(4), vertices.get(6), 6),
					new Edge(vertices.get(5), vertices.get(6), 2), new Edge(vertices.get(5), vertices.get(7), 3),
					new Edge(vertices.get(6), vertices.get(7), 6));
		});
	}

	@Override
	public List<VertexWithRank> vertices() {
		return this.vertices.value();
	}

	@Override
	public List<Edge> edges() {
		return this.edges.value();
	}
}
