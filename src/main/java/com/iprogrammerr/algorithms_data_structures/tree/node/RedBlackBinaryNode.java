package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class RedBlackBinaryNode<T extends Comparable<T>> {

	public RedBlackBinaryNode<T> parent;
	public RedBlackBinaryNode<T> leftChild;
	public RedBlackBinaryNode<T> rightChild;
	private boolean red;
	public T data;

	public RedBlackBinaryNode(RedBlackBinaryNode<T> parent, RedBlackBinaryNode<T> leftChild,
		RedBlackBinaryNode<T> rightChild, T data) {
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.red = true;
		this.data = data;
	}

	public RedBlackBinaryNode(T data) {
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

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public void setRed() {
		red = true;
	}

	public void setBlack() {
		red = false;
	}

	@Override
	public String toString() {
		return "RedBlackBinaryNode [red=" + red + ", data=" + data + "]";
	}

}
