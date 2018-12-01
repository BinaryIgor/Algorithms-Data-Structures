package com.iprogrammerr.algorithms_data_structures.queue;

public interface Queue<T> {

    void enqueue(T item);

    T dequeue() throws Exception;

    T peek() throws Exception;

    boolean isEmpty();

}
