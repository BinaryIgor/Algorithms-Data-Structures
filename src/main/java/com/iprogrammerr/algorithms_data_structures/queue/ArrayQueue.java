package com.iprogrammerr.algorithms_data_structures.queue;

public final class ArrayQueue<T> implements Queue<T> {

	private T[] items;
	private int front;
	private int back;

	public ArrayQueue(int initialCapacity) {
		this.items = (T[]) new Object[initialCapacity];
	}

	public ArrayQueue() {
		this(10);
	}

	@Override
	public void enqueue(T item) {
		if (size() == this.items.length - 1) {
			int itemsNumber = size();
			T[] newItems = (T[]) new Object[2 * this.items.length];
			System.arraycopy(this.items, this.front, newItems, 0, this.items.length - this.front);
			System.arraycopy(this.items, 0, newItems, this.items.length - this.front, this.back);
			this.items = newItems;
			this.front = 0;
			this.back = itemsNumber;
		}
		this.items[this.back] = item;
		if (this.back < this.items.length - 1) {
			this.back++;
		} else {
			this.back = 0;
		}
	}

	@Override
	public T dequeue() throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty!");
		}
		T item = this.items[this.front++];
		if (size() == 0) {
			this.front = this.back = 0;
		} else if (this.front == this.items.length) {
			this.front = 0;
		}
		return item;
	}

	@Override
	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty!");
		}
		return this.items[this.front];
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	private int size() {
		if (this.front <= this.back) {
			return this.back - this.front;
		}
		return this.back - this.front + this.items.length;
	}

}
