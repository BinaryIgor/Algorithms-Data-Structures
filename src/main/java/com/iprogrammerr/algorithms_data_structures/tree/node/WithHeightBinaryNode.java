package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class WithHeightBinaryNode<T extends Comparable<T>> {

	public WithHeightBinaryNode<T> leftChild;
	public WithHeightBinaryNode<T> rightChild;
	public T data;
	public int height;

	public WithHeightBinaryNode(WithHeightBinaryNode<T> leftChild, WithHeightBinaryNode<T> rightChild, T data,
			int height) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
		this.height = height;
	}

	public WithHeightBinaryNode(T data, int height) {
		this(null, null, data, height);
	}

	public WithHeightBinaryNode(T data) {
		this(data, 0);
	}

	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}
}
