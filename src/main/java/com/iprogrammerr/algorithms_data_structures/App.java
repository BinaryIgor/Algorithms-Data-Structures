package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.tree.SplayTree;
import com.iprogrammerr.algorithms_data_structures.tree.Tree;

public class App {

	public static void main(String[] args) throws Exception {
		Tree<Integer> tree = new SplayTree<>();
		tree.insert(1);
		tree.insert(5);
		tree.insert(6);
		tree.insert(-1);
		// tree.search(-1);
		for (int i : tree.items()) {
			System.out.println(i);
		}
	}
}
