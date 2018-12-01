package com.iprogrammerr.algorithms_data_structures.heap;

public interface Heap<T extends Comparable<T>> {

    T peek() throws Exception;

    T pop() throws Exception;

    void insert(T value) throws Exception;

    void delete(T value) throws Exception;

    boolean isFull();

    boolean isEmpty();

    T[] sorted();

    void print();
}
