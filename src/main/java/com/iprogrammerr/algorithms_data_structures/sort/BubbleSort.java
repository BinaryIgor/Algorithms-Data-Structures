package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public BubbleSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public BubbleSort(List<T> source) {
		this(source, true);
	}

	public BubbleSort(boolean ascending, T... source) {
		this(new ArrayList<>(Arrays.asList(source)), ascending);
	}

	public BubbleSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> toSort = new ArrayList<>(this.source);
		boolean swap;
		int size;
		do {
			swap = false;
			size = this.source.size();
			for (int i = 1; i < size; ++i) {
				int cmp = toSort.get(i).compareTo(toSort.get(i - 1));
				if ((cmp < 0 && this.ascending) || (cmp > 0 && !this.ascending)) {
					swap(toSort, i - 1, i);
					swap = true;
				}
			}
			--size;
		} while (swap);
		return toSort;
	}

	private void swap(List<T> items, int first, int second) {
		T tmp = items.get(first);
		items.set(first, items.get(second));
		items.set(second, tmp);
	}
}
