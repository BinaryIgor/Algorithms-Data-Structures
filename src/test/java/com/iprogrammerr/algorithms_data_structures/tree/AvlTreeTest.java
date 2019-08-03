package com.iprogrammerr.algorithms_data_structures.tree;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class AvlTreeTest {

	@Test
	public void returnsItems() {
		AvlTree<String> tree = new AvlTree<>();
		TreesTests.returnsItems(tree, new String[] { "a", "b", "c", "e", "g", "h" });
		checkBalance(tree);
	}

	private void checkBalance(AvlTree<?> tree) {
		MatcherAssert.assertThat(tree.balance(),
				Matchers.allOf(Matchers.greaterThanOrEqualTo(-1), Matchers.lessThanOrEqualTo(1)));
	}

	@Test
	public void throwsExceptionOnDuplicate() {
		TreesTests.throwsExceptionOnDuplicate(new AvlTree<>(), "abc");
	}

	@Test
	public void deletes() {
		AvlTree<Long> tree = new AvlTree<>();
		TreesTests.deletes(tree, new Long[] { 1L, 3L, -4L }, 3L);
		checkBalance(tree);
	}

	@Test
	public void founds() {
		TreesTests.founds(new AvlTree<>(), new Double[] { 1.0, 3.33, 5.0 }, 5.0);
	}

	@Test
	public void foundsMin() {
		TreesTests.foundsMin(new AvlTree<>(), new Integer[] { 1, 4, -5, 6 });
	}

	@Test
	public void foundsMax() {
		TreesTests.foundsMax(new AvlTree<>(), new Integer[] { 9, 5, 6 });
	}
}
