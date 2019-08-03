package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.Arrays;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class TreesTests {

	public static <T extends Comparable<T>> void returnsItems(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.insert(i);
		}
		MatcherAssert.assertThat(tree.items(), Matchers.contains(items));
	}

	public static <T extends Comparable<T>> void throwsExceptionOnDuplicate(Tree<T> tree, T item) {
		boolean thrown = false;
		try {
			tree.insert(item);
			tree.insert(item);
		} catch (Exception e) {
			thrown = true;
		}
		MatcherAssert.assertThat(thrown, Matchers.equalTo(true));
	}

	public static <T extends Comparable<T>> void deletes(Tree<T> tree, T[] items, T toDelete) {
		for (T i : items) {
			tree.insert(i);
		}
		tree.delete(toDelete);
		MatcherAssert.assertThat(tree.items(), Matchers.not(Matchers.hasItem(toDelete)));
	}

	public static <T extends Comparable<T>> void founds(Tree<T> tree, T[] items, T toFound) {
		for (T i : items) {
			tree.insert(i);
		}
		MatcherAssert.assertThat(tree.search(toFound), Matchers.equalTo(toFound));
	}

	public static <T extends Comparable<T>> void foundsMin(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.insert(i);
		}
		T min = Arrays.stream(items).min((f, s) -> f.compareTo(s)).get();
		MatcherAssert.assertThat(tree.min(), Matchers.equalTo(min));
	}

	public static <T extends Comparable<T>> void foundsMax(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.insert(i);
		}
		T max = Arrays.stream(items).max((f, s) -> f.compareTo(s)).get();
		MatcherAssert.assertThat(tree.max(), Matchers.equalTo(max));
	}
}
