package com.iprogrammerr.algorithms_data_structures.stack;

import java.util.LinkedList;
import java.util.List;

public final class LinkedListStack<T> implements Stack<T> {

	private final List<T> items;
	private int top;

	private LinkedListStack(List<T> items) {
		this.items = items;
	}

	public LinkedListStack() {
		this(new LinkedList<>());
	}

	@Override
	public void push(T item) {
		this.items.add(item);
		++this.top;
	}

	@Override
	public T pop() throws Exception {
		if (this.top == 0) {
			throw new Exception("Stack is empty!");
		}
		T item = this.items.get(--this.top);
		this.items.remove(this.top);
		return item;
	}

	@Override
	public T peek() throws Exception {
		if (this.top == 0) {
			throw new Exception("Stack is empty!");
		}
		return this.items.get(this.top - 1);
	}

	@Override
	public boolean isEmpty() {
		return this.top == 0;
	}

}
