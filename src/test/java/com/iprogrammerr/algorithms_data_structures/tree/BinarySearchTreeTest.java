package com.iprogrammerr.algorithms_data_structures.tree;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void returnsItems() {
		TreesTests.returnsItems(new BinarySearchTree<>(), new Integer[] { 1, 2, 3 });
	}

	@Test
	public void throwsExceptionOnDuplicate() {
		TreesTests.throwsExceptionOnDuplicate(new BinarySearchTree<>(), 2);
	}

	@Test
	public void deletes() {
		TreesTests.deletes(new BinarySearchTree<>(), new Integer[] { 4, 5, 6 }, 5);
	}

	@Test
	public void founds() {
		TreesTests.founds(new BinarySearchTree<>(), new Integer[] { 4, 2, 6, 22 }, 22);
	}

	@Test
	public void foundsMin() {
		TreesTests.foundsMin(new BinarySearchTree<>(), new Integer[] { 4, 2, 6, 22 });
	}

	@Test
	public void foundsMax() {
		TreesTests.foundsMax(new BinarySearchTree<>(), new Integer[] { 4, -2 });
	}
}
