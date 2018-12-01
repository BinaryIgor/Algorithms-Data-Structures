package com.iprogrammerr.algorithms_data_structures.stack;

public final class ArrayStack<T> implements Stack<T> {

	private T[] items;
	private int top;

	public ArrayStack(int initialCapacity) {
		this.items = (T[]) new Object[initialCapacity];
	}

	public ArrayStack() {
		this(10);
	}

	@Override
	public void push(T item) {
		if (this.top == this.items.length) {
			resize(2 * this.items.length);
		}
		this.items[this.top++] = item;
	}

	@Override
	public T pop() throws Exception {
		if (this.top == 0) {
			throw new Exception("Stack is empty!");
		}
		T item = this.items[--this.top];
		this.items[this.top] = null;
		if (this.top < (this.items.length / 4)) {
			resize(this.items.length / 2);
		}
		return item;
	}

	@Override
	public T peek() throws Exception {
		if (this.top == 0) {
			throw new Exception("Stack is empty!");
		}
		return this.items[this.top - 1];
	}

	@Override
	public boolean isEmpty() {
		return this.top == 0;
	}

	private void resize(int size) {
		T[] newItems = (T[]) new Object[size];
		System.arraycopy(this.items, 0, newItems, 0, this.top);
		this.items = newItems;
	}

}
