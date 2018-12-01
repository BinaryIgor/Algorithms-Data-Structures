package com.iprogrammerr.algorithms_data_structures.tree;

public interface Tree<T extends Comparable<T>> {

    void insert(T data) throws Exception;

    void delete(T data);

    T search(T data) throws Exception;

    T min() throws Exception;

    T max() throws Exception;

    Iterable<T> items();

    void traverse();
}
