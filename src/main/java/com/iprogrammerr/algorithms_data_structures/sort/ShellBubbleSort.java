package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ShellBubbleSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public ShellBubbleSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public ShellBubbleSort(List<T> source) {
		this(source, true);
	}

	public ShellBubbleSort(boolean ascending, T... source) {
		this(Arrays.asList(source), ascending);
	}

	public ShellBubbleSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> toSort = new ArrayList<>(this.source);
		for (int gap = toSort.size() / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < toSort.size(); ++i) {
				T newElement = toSort.get(i);
				int j = i;
				while (shouldKeepMoving(j, gap, toSort, newElement)) {
					swap(toSort, j, j - gap);
					j -= gap;
				}
			}
		}
		return toSort;
	}

	private boolean shouldKeepMoving(int current, int gap, List<T> items, T value) {
		boolean keepMoving = current >= gap;
		if (keepMoving) {
			int cmp = value.compareTo(items.get(current - gap));
			keepMoving = (this.ascending && cmp < 0) || (!this.ascending && cmp > 0);
		}
		return keepMoving;
	}

	private void swap(List<T> items, int first, int second) {
		T tmp = items.get(first);
		items.set(first, items.get(second));
		items.set(second, tmp);
	}
}
