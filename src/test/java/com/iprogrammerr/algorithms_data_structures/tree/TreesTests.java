package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class TreesTests {

	public static <T extends Comparable<T>> void returnsItems(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.put(i);
		}
		MatcherAssert.assertThat(tree.items(), Matchers.contains(items));
	}

	public static <T extends Comparable<T>> void throwsExceptionOnDuplicate(Tree<T> tree, T item) {
		boolean thrown = false;
		try {
			tree.put(item);
			tree.put(item);
		} catch (Exception e) {
			thrown = true;
		}
		MatcherAssert.assertThat(thrown, Matchers.equalTo(true));
	}

	public static <T extends Comparable<T>> void deletes(Tree<T> tree, T[] items, T toDelete) {
		for (T i : items) {
			tree.put(i);
		}
		tree.delete(toDelete);
		MatcherAssert.assertThat(tree.items(), Matchers.not(Matchers.hasItem(toDelete)));
	}

	public static <T extends Comparable<T>> void founds(Tree<T> tree, T[] items, T toFound) {
		for (T i : items) {
			tree.put(i);
		}
		MatcherAssert.assertThat(tree.contains(toFound), Matchers.equalTo(true));
	}

	public static <T extends Comparable<T>> void foundsMin(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.put(i);
		}
		T min = Arrays.stream(items).min((f, s) -> f.compareTo(s)).get();
		MatcherAssert.assertThat(tree.min(), Matchers.equalTo(min));
	}

	public static <T extends Comparable<T>> void foundsMax(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.put(i);
		}
		T max = Arrays.stream(items).max((f, s) -> f.compareTo(s)).get();
		MatcherAssert.assertThat(tree.max(), Matchers.equalTo(max));
	}

	public static <T extends Comparable<T>> void traverses(Tree<T> tree, T[] items) {
		for (T i : items) {
			tree.put(i);
		}
		List<T> traversed = new ArrayList<>();
		tree.traverse(traversed::add);
		MatcherAssert.assertThat(tree.items(), Matchers.equalTo(traversed));
	}
}
