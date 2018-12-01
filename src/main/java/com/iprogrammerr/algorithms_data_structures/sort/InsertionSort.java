package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class InsertionSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public InsertionSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public InsertionSort(List<T> source) {
		this(source, true);
	}

	public InsertionSort(boolean ascending, T... source) {
		this(new ArrayList<>(Arrays.asList(source)), ascending);
	}

	public InsertionSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> toSort = new ArrayList<>(this.source);
		for (int i = 1; i < toSort.size(); ++i) {
			T curr = toSort.get(i);
			int j;
			for (j = i; shouldKeepMoving(j, toSort, curr); --j) {
				toSort.set(j, toSort.get(j - 1));
			}
			toSort.set(j, curr);
		}
		return toSort;
	}

	private boolean shouldKeepMoving(int currentIndex, List<T> items, T value) {
		boolean keepMoving = currentIndex > 0;
		if (keepMoving) {
			int cmp = value.compareTo(items.get(currentIndex - 1));
			keepMoving = (this.ascending && cmp < 0) || (!this.ascending && cmp > 0);
		}
		return keepMoving;
	}
}
