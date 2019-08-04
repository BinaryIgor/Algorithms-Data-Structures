package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class SplayBinaryNode<T extends Comparable<T>> {

	public SplayBinaryNode<T> parent;
	public SplayBinaryNode<T> leftChild;
	public SplayBinaryNode<T> rightChild;
	public T data;

	public SplayBinaryNode(SplayBinaryNode<T> parent, SplayBinaryNode<T> leftChild, SplayBinaryNode<T> rightChild,
		T data) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.data = data;
	}

	public SplayBinaryNode(T data) {
		this(null, null, null, data);
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}

}
