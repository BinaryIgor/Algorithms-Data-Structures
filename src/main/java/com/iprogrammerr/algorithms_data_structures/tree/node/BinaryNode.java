package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class BinaryNode<T extends Comparable<T>> {

	public BinaryNode<T> leftChild;
	public BinaryNode<T> rightChild;
	public T data;

	public BinaryNode(BinaryNode<T> leftChild, BinaryNode<T> rightChild, T data) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
	}

	public BinaryNode(T data) {
		this(null, null, data);
	}

	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}

	@Override
	public String toString() {
		return "BinaryNode [leftChild=" + leftChild + ", rightChild=" + rightChild + ", data=" + data + "]";
	}
}
