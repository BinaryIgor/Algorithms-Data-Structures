package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MergeSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public MergeSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public MergeSort(List<T> source) {
		this(source, true);
	}

	public MergeSort(boolean ascending, T... source) {
		this(new ArrayList<>(Arrays.asList(source)), ascending);
	}

	public MergeSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> items = new ArrayList<>(this.source);
		sort(items, 0, items.size());
		return items;
	}

	private void sort(List<T> items, int start, int end) {
		if ((end - start) > 1) {
			int mid = (start + end) / 2;
			sort(items, start, mid);
			sort(items, mid, end);
			int cmp = items.get(mid - 1).compareTo(items.get(mid));
			if ((this.ascending && cmp > 0) || (!this.ascending && cmp < 0)) {
				merge(items, start, mid, end);
			}
		}
	}

	private void merge(List<T> items, int start, int mid, int end) {
		int fIndex = start, sIndex = mid;
		List<T> tmp = new ArrayList<>(end - start);
		while (fIndex < mid && sIndex < end) {
			int cmp = items.get(fIndex).compareTo(items.get(sIndex));
			if ((this.ascending && cmp <= 0) || (!this.ascending && cmp > 0)) {
				tmp.add(items.get(fIndex));
				++fIndex;
			} else {
				tmp.add(items.get(sIndex));
				++sIndex;
			}
		}
		while (fIndex < mid) {
			tmp.add(items.get(fIndex++));
		}
		for (int i = 0; i < tmp.size(); ++i) {
			items.set(start + i, tmp.get(i));
		}
	}
}
