package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class QuickSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public QuickSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public QuickSort(List<T> source) {
		this(source, true);
	}

	public QuickSort(boolean ascending, T... source) {
		this(new ArrayList<>(Arrays.asList(source)), ascending);
	}

	public QuickSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> toSort = new ArrayList<>(this.source);
		sort(toSort, 0, toSort.size() - 1);
		return toSort;
	}

	private void sort(List<T> items, int start, int end) {
		T pivot = pivot(items, start, end);
		int i = start, j = end;
		while (i < j) {
			while (shouldFoward(items, pivot, i)) {
				i++;
			}
			while (shouldBackward(items, pivot, j)) {
				j--;
			}
			if (i <= j) {
				if (i < j) {
					swap(items, i, j);
				}
				i++;
				j--;
			}
		}
		if (i < end) {
			sort(items, i, end);
		}
		if (start < j) {
			sort(items, start, j);
		}
	}

	private T pivot(List<T> items, int start, int end) {
		T pivot;
		if ((end - start) <= 2) {
			pivot = items.get(start);
		} else {
			T first = items.get(start), second = items.get((start + end + 1) / 2), third = items.get(end);
			if (first.compareTo(third) < 0) {
				pivot = second.compareTo(third) > 0 ? third : second;
			} else {
				pivot = second.compareTo(first) > 0 ? first : second;
			}
		}
		return pivot;
	}

	private void swap(List<T> items, int first, int second) {
		T tmp = items.get(first);
		items.set(first, items.get(second));
		items.set(second, tmp);
	}

	private boolean shouldFoward(List<T> items, T pivot, int curr) {
		int cmp = items.get(curr).compareTo(pivot);
		return (this.ascending && cmp < 0) || (!this.ascending && cmp > 0);
	}

	private boolean shouldBackward(List<T> items, T pivot, int curr) {
		int cmp = items.get(curr).compareTo(pivot);
		return (this.ascending && cmp > 0) || (!this.ascending && cmp < 0);
	}
}
