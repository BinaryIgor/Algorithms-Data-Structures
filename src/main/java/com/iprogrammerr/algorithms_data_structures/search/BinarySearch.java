package com.iprogrammerr.algorithms_data_structures.search;

public final class BinarySearch<T extends Comparable<T>> implements Search<T> {

	@Override
	public int index(T[] array, T key) {
		return index(array, array.length, key);
	}

	@Override
	public int index(T[] array, int size, T key) {
		int start = 0, end = size;
		while (start < end) {
			int mid = (start + end) / 2;
			int comparisonValue = array[mid].compareTo(key);
			if (comparisonValue == 0) {
				return mid;
			} else if (comparisonValue < 0) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return -1;
	}
}
