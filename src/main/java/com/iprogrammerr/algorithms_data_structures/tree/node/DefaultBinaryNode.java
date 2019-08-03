package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class DefaultBinaryNode<T extends Comparable<T>> extends BinaryNode<T> {

	public DefaultBinaryNode<T> leftChild;
	public DefaultBinaryNode<T> rightChild;
	public T data;

	public DefaultBinaryNode(DefaultBinaryNode<T> leftChild, DefaultBinaryNode<T> rightChild, T data) {
		super(leftChild, rightChild, data);
	}

	public DefaultBinaryNode(T data) {
		this(null, null, data);
	}

	@Override
	public String toString() {
		return "BinaryNode [leftChild=" + leftChild + ", rightChild=" + rightChild + ", data=" + data + "]";
	}
}
