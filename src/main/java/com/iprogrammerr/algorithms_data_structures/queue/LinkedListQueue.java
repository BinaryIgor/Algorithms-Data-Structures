package com.iprogrammerr.algorithms_data_structures.queue;

import com.iprogrammerr.algorithms_data_structures.list.SingleLinkNode;

public class LinkedListQueue<T> implements Queue<T> {

	private SingleLinkNode<T> head;
	private SingleLinkNode<T> tail;

	@Override
	public void enqueue(T item) {
		SingleLinkNode<T> node = new SingleLinkNode<>(item);
		if (this.head == null) {
			this.head = this.tail = node;
		} else {
			this.tail.setNext(node);
			this.tail = node;
		}
	}

	@Override
	public T dequeue() throws Exception {
		if (this.head == null) {
			throw new Exception("Queue is empty!");
		}
		SingleLinkNode<T> node = this.head;
		this.head = node.next();
		return node.value();
	}

	@Override
	public T peek() throws Exception {
		if (this.head == null) {
			throw new Exception("Queue is empty!");
		}
		return this.head.value();
	}

	@Override
	public boolean isEmpty() {
		return this.head == null;
	}
}
