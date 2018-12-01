package com.iprogrammerr.algorithms_data_structures.search;

public interface Search<T extends Comparable<T>> {

    int index(T[] array, T key);

    int index(T[] array, int size, T key);
}
