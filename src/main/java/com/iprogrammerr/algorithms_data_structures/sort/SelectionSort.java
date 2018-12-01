package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SelectionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public SelectionSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public SelectionSort(List<T> source) {
		this(source, true);
	}

	public SelectionSort(boolean ascending, T... source) {
		this(Arrays.asList(source), ascending);
	}

	public SelectionSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> toSort = new ArrayList<>(this.source);
		for (int i = toSort.size() - 1; i >= 0; --i) {
			int minOrMax = 0;
			for (int j = 1; j <= i; ++j) {
				if (isNewMinOrMax(toSort.get(j).compareTo(toSort.get(minOrMax)))) {
					minOrMax = j;
				}
			}
			if (isNewMinOrMax(toSort.get(minOrMax).compareTo(toSort.get(i)))) {
				swap(toSort, i, minOrMax);
			}
		}
		return toSort;
	}

	private boolean isNewMinOrMax(int cmp) {
		return (this.ascending && cmp > 0) || (!this.ascending && cmp < 0);
	}

	private void swap(List<T> items, int first, int second) {
		T tmp = items.get(first);
		items.set(first, items.get(second));
		items.set(second, tmp);
	}
}
