package com.iprogrammerr.algorithms_data_structures.list;

import java.util.ListIterator;

import com.iprogrammerr.algorithms_data_structures.exception.NullValueNotAllowedException;

public class DoublyLinkedListIterator<T> implements ListIterator<T> {

    private int index;
    private Node<T> current;

    public DoublyLinkedListIterator(int index, Node<T> current) {
	this.index = index;
	this.current = current;
    }

    @Override
    public boolean hasNext() {
	return current.getNext() != null;
    }

    @Override
    public T next() {
	Node<T> next = current;
	if (current.getNext() != null) {
	    current = current.getNext();
	    ++index;
	}
	return next.getItem();
    }

    @Override
    public boolean hasPrevious() {
	return current.getPrevious() != null;
    }

    @Override
    public T previous() {
	Node<T> previous = current;
	if (current.getPrevious() != null) {
	    current = current.getPrevious();
	    --index;
	}
	return previous.getItem();
    }

    @Override
    public int nextIndex() {
	return index;
    }

    @Override
    public int previousIndex() {
	return index;
    }

    @Override
    public void remove() {
	Node<T> previous = current.getPrevious();
	Node<T> next = current.getNext();
	if (previous != null) {
	    previous.setNext(next);
	}
	if (next != null) {
	    next.setPrevious(previous);
	}
    }

    @Override
    public void set(T element) {
	if (element == null) {
	    throw new NullValueNotAllowedException();
	}
	current.setItem(element);

    }

    @Override
    public void add(T element) {
	Node<T> newNode = new Node<>(element);
	newNode.setPrevious(current);
	newNode.setNext(current.getNext());
	current.setNext(newNode);
    }

}
