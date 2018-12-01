package com.iprogrammerr.algorithms_data_structures.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class ArrayIterator<T> implements Iterator<T> {

    private final T[] array;
    private int current;

    public ArrayIterator(T[] array) {
	this.array = array;
    }

    @Override
    public boolean hasNext() {
	return current < this.array.length && this.array[current] != null;
    }

    @Override
    public T next() {
	if (current == this.array.length) {
	    throw new NoSuchElementException("There is not any element left");
	}
	return this.array[current++];
    }

}
