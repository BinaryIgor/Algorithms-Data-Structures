package com.iprogrammerr.algorithms_data_structures.sort;

public interface SortingAlgorithm<T extends Comparable<T>> {
	Iterable<T> sorted();
}
