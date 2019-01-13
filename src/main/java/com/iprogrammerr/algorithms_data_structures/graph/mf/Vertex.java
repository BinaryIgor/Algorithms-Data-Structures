package com.iprogrammerr.algorithms_data_structures.graph.mf;

public final class Vertex {

	private final String name;

	public Vertex(String name) {
		this.name = name;
	}

	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
