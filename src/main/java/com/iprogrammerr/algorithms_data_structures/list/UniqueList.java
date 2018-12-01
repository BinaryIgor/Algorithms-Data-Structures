package com.iprogrammerr.algorithms_data_structures.list;

public interface UniqueList<T> {

    void add(T item);

    T get(int index);

    int size();

    boolean empty();
}
