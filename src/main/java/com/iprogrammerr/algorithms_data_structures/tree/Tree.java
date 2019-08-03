package com.iprogrammerr.algorithms_data_structures.tree;

public interface Tree<T extends Comparable<T>> {

	void insert(T data);

	void delete(T data);

	T search(T data);

	T min();

	T max();

	Iterable<T> items();

	void traverse();
}
