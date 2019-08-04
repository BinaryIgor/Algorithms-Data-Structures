package com.iprogrammerr.algorithms_data_structures.tree;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class SplayTreeTest {

	@Test
	public void returnsItems() {
		SplayTree<String> tree = new SplayTree<>();
		TreesTests.returnsItems(tree, new String[] { "a", "b", "c", "e", "g" });
		checkRoot(tree, "g");
	}

	private void checkRoot(SplayTree<?> tree, Object expected) {
		MatcherAssert.assertThat(tree.rootData(), Matchers.equalTo(expected));
	}

	@Test
	public void traverses() {
		TreesTests.traverses(new SplayTree<>(), new Character[] { 'a', 'c', 'e' });
	}

	@Test
	public void throwsExceptionOnDuplicate() {
		TreesTests.throwsExceptionOnDuplicate(new SplayTree<>(), "X");
	}

	@Test
	public void deletes() {
		TreesTests.deletes(new SplayTree<>(), new Long[] { 1L, 3L, -44L }, 1L);
	}

	@Test
	public void founds() {
		SplayTree<Double> tree = new SplayTree<>();
		TreesTests.founds(tree, new Double[] { 1.0, 3.33, 5.0 }, 5.0);
		checkRoot(tree, 5.0);
	}

	@Test
	public void foundsMin() {
		SplayTree<Integer> tree = new SplayTree<>();
		TreesTests.foundsMin(tree, new Integer[] { 1, 4, -5, 6 });
		checkRoot(tree, -5);
	}
}
