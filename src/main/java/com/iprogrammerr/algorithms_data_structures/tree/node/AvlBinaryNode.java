package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class AvlBinaryNode<T extends Comparable<T>> {

	public AvlBinaryNode<T> leftChild;
	public AvlBinaryNode<T> rightChild;
	public T data;
	public int height;

	public AvlBinaryNode(AvlBinaryNode<T> leftChild, AvlBinaryNode<T> rightChild, T data,
			int height) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
		this.height = height;
	}

	public AvlBinaryNode(T data, int height) {
		this(null, null, data, height);
	}

	public AvlBinaryNode(T data) {
		this(data, 0);
	}

	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}
}
