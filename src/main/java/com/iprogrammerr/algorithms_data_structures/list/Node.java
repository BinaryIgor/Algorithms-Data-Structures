package com.iprogrammerr.algorithms_data_structures.list;

public class Node<T> {

	private T item;
	private Node<T> previous;
	private Node<T> next;

	public Node(T item) {
		this.item = item;
	}

	public T getItem() {
		return item;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public void setItem(T item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((previous == null) ? 0 : previous.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Node)) {
			return false;
		}
		if (object == this) {
			return true;
		}
		Node<T> other = (Node<T>) object;
		boolean previousEqual = previous == null && other.previous == null
				|| (previous != null && !previous.equals(other.previous));
		if (!previousEqual) {
			return false;
		}
		boolean nextEqual = next == null && other.next == null
				|| (next != null && !next.equals(other.next));
		if (!nextEqual) {
			return false;
		}
		return item == null && other.item == null || (item != null && item.equals(other.item));
	}

}
