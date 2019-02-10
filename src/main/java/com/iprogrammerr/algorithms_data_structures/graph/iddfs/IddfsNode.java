package com.iprogrammerr.algorithms_data_structures.graph.iddfs;

import java.util.ArrayList;
import java.util.List;

public final class IddfsNode {

	private final String name;
	private int depth;
	private final List<IddfsNode> adjacencies;

	public IddfsNode(String name) {
		this.name = name;
		this.depth = 0;
		this.adjacencies = new ArrayList<>();
	}

	public String name() {
		return this.name;
	}

	public int depth() {
		return this.depth;
	}

	public void changeDepth(int depth) {
		this.depth = depth;
	}

	public List<IddfsNode> adjacencies() {
		return this.adjacencies;
	}

	public void addNeighbors(IddfsNode... nodes) {
		for (IddfsNode n : nodes) {
			this.adjacencies.add(n);
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
