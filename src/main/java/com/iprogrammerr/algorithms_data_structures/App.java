package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.tree.RedBlackTree;
import com.iprogrammerr.algorithms_data_structures.tree.Tree;

public class App {

	public static void main(String[] args) throws Exception {
		Tree<Integer> tree = new RedBlackTree<>(2);
		tree.insert(1);
		tree.insert(4);
		tree.insert(6);
		tree.delete(4);
		tree.search(1);
		tree.traverse();
	}
}
