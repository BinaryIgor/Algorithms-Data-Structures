package com.iprogrammerr.algorithms_data_structures.stack;

public interface Stack<T> {

    void push(T item);

    T pop() throws Exception;

    T peek() throws Exception;

    boolean isEmpty();

}
