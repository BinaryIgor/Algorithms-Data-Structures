package com.iprogrammerr.algorithms_data_structures.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class HeapSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

	private final List<T> source;
	private final boolean ascending;

	public HeapSort(List<T> source, boolean ascending) {
		this.source = source;
		this.ascending = ascending;
	}

	public HeapSort(List<T> source) {
		this(source, true);
	}

	public HeapSort(boolean ascending, T... source) {
		this(Arrays.asList(source), ascending);
	}

	public HeapSort(T... source) {
		this(true, source);
	}

	@Override
	public Iterable<T> sorted() {
		List<T> items = new ArrayList<>(this.source);
		sort(items);
		return items;
	}

	private void sort(List<T> items) {
		buildHeap(items);
		for (int i = items.size() - 1; i > 0; i--) {
			swap(items, 0, i);
			heapify(items, 0, i);
		}
	}

	private void buildHeap(List<T> items) {
		int n = items.size();
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(items, i, n);
		}
	}

	private void heapify(List<T> items, int root, int size) {
		int largest = root;
		int left = 2 * root + 1;
		int right = 2 * root + 2;
		if (left < size && shouldSwap(items, left, largest)) {
			largest = left;
		}
		if (right < size && shouldSwap(items, right, largest)) {
			largest = right;
		}
		if (largest != root) {
			swap(items, largest, root);
			heapify(items, largest, size);
		}
	}

	private boolean shouldSwap(List<T> items, int first, int second) {
		int cmp = items.get(first).compareTo(items.get(second));
		return (this.ascending && cmp > 0) || (!this.ascending && cmp < 0);
	}

	private void swap(List<T> items, int i, int j) {
		T tmp = items.get(i);
		items.set(i, items.get(j));
		items.set(j, tmp);
	}
}
