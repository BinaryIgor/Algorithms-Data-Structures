package com.iprogrammerr.algorithms_data_structures.tree;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RedBlackTreeTest {

	@Test
	public void returnsItems() {
		RedBlackTree<String> tree = new RedBlackTree<>();
		TreesTests.returnsItems(tree, new String[] { "a", "b", "c", "e" });
		checkViolations(tree);
	}

	private void checkViolations(RedBlackTree<?> tree) {
		MatcherAssert.assertThat(tree.hasViolations(), Matchers.equalTo(false));
	}

	@Test
	public void traverses() {
		RedBlackTree<String> tree = new RedBlackTree<>();
		TreesTests.traverses(tree, new String[] { "ad", "cd", "fg" });
		checkViolations(tree);
	}

	@Test
	public void throwsExceptionOnDuplicate() {
		TreesTests.throwsExceptionOnDuplicate(new RedBlackTree<>(), "rbt");
	}

	@Test
	public void deletes() {
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		TreesTests.deletes(tree, new Integer[] { 1, 3, 5, 6, 9, 10, 11, 44, 33 }, 6);
	}

	@Test
	public void founds() {
		TreesTests.founds(new RedBlackTree<>(), new Boolean[] { true, false }, false);
	}

	@Test
	public void foundsMin() {
		TreesTests.foundsMin(new RedBlackTree<>(), new Integer[] { 1, 4, -5 });
	}

	@Test
	public void foundsMax() {
		TreesTests.foundsMax(new RedBlackTree<>(), new Integer[] { 2, 5, 6 });
	}
}
