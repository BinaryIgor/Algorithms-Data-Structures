package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.List;
import java.util.function.Consumer;

public interface Tree<T extends Comparable<T>> {

	void put(T data);

	void delete(T data);

	boolean contains(T data);

	T min();

	T max();

	List<T> items();

	void traverse(Consumer<T> itemConsumer);
}
