package com.iprogrammerr.algorithms_data_structures.data;

public interface MinMax<T extends Comparable<T>> {

	T min() throws Exception;

	T max() throws Exception;
}
