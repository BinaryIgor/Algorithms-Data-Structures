package com.iprogrammerr.algorithms_data_structures.graph.iddfs;

import java.util.Stack;

public final class Iddfs {

	private final IddfsNode source;
	private final int maxDepth;
	private boolean found;

	public Iddfs(IddfsNode source, int maxDepth) {
		this.source = source;
		this.maxDepth = maxDepth;
	}

	public Iddfs(IddfsNode source) {
		this(source, 10_000);
	}

	public void run(IddfsNode target) {
		int depth = 1;
		while (!this.found && depth <= this.maxDepth) {
			dfs(target, depth);
			++depth;
			System.out.println();
		}
	}

	private void dfs(IddfsNode target, int depth) {
		Stack<IddfsNode> stack = new Stack<>();
		stack.push(this.source);
		while (!stack.isEmpty()) {
			IddfsNode node = stack.pop();
			System.out.print(node + " ");
			if (node.name().equals(target.name())) {
				System.out.print(String.format("%sNode has been found...%s", System.lineSeparator(), target));
				this.found = true;
				break;
			}
			if (node.depth() < depth) {
				for (IddfsNode a : node.adjacencies()) {
					a.changeDepth(node.depth() + 1);
					stack.push(a);
				}
			}
		}
	}
}
