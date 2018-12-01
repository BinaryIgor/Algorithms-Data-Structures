package com.iprogrammerr.algorithms_data_structures.list;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> next;

    public LinkedListIterator(Node<T> first) {
	this.next = first;
    }

    public boolean hasNext() {
	return next != null;
    }

    public T next() {
	Node<T> current = next;
	next = current.getNext();
	return current != null ? current.getItem() : null;
    }

}
