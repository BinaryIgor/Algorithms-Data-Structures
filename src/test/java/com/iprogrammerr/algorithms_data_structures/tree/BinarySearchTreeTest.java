package com.iprogrammerr.algorithms_data_structures.tree;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void returnsItems() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		tree.insert(1);
		tree.insert(3);
		tree.insert(4);
		tree.insert(-1);

		MatcherAssert.assertThat(tree.items(), Matchers.contains(-1, 1, 3, 4));
	}

	@Test
	public void deletes() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		tree.insert(1);
		tree.insert(2);
		tree.delete(2);

		MatcherAssert.assertThat(tree.items(), Matchers.contains(1));
	}

	@Test
	public void founds() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		int wanted = 22;
		tree.insert(wanted);
		tree.insert(3);

		MatcherAssert.assertThat(tree.search(wanted), Matchers.equalTo(wanted));
	}
}
