package com.iprogrammerr.algorithms_data_structures.list;

public final class SingleLinkNode<T> {

	private T value;
	private SingleLinkNode<T> next;

	public SingleLinkNode(T value) {
		this.value = value;
	}

	public T value() {
		return this.value;
	}

	public void revalue(T value) {
		this.value = value;
	}

	public SingleLinkNode<T> next() {
		return this.next;
	}

	public void setNext(SingleLinkNode<T> next) {
		this.next = next;
	}
}
