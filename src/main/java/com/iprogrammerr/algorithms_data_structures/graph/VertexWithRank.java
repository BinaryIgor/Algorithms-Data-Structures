package com.iprogrammerr.algorithms_data_structures.graph;

public final class VertexWithRank implements Vertex {

	private final int id;
	private final String name;
	private int rank;
	private VertexWithRank parent;

	public VertexWithRank(int id, String name, int rank) {
		this.id = id;
		this.name = name;
		this.rank = rank;
	}

	public VertexWithRank(int id, String name) {
		this(id, name, 0);
	}

	public int id() {
		return this.id;
	}

	@Override
	public String name() {
		return this.name;
	}

	public int rank() {
		return this.rank;
	}

	public void changeRank(int rank) {
		this.rank = rank;
	}

	public VertexWithRank parent() {
		return this.parent;
	}

	public void changeParent(VertexWithRank parent) {
		this.parent = parent;
	}
}
