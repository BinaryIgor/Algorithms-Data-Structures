package com.iprogrammerr.algorithms_data_structures.tst;

public class TernarySearchTreeNode<T> {

	private final char character;
	private T value;
	private TernarySearchTreeNode<T> left;
	private TernarySearchTreeNode<T> middle;
	private TernarySearchTreeNode<T> right;

	public TernarySearchTreeNode(char character) {
		this.character = character;
	}

	public char character() {
		return this.character;
	}

	public T value() {
		return this.value;
	}

	public TernarySearchTreeNode<T> left() {
		return this.left;
	}

	public TernarySearchTreeNode<T> middle() {
		return this.middle;
	}

	public TernarySearchTreeNode<T> right() {
		return this.right;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setLeft(TernarySearchTreeNode<T> left) {
		this.left = left;
	}

	public void setMiddle(TernarySearchTreeNode<T> middle) {
		this.middle = middle;
	}

	public void setRight(TernarySearchTreeNode<T> right) {
		this.right = right;
	}
}
