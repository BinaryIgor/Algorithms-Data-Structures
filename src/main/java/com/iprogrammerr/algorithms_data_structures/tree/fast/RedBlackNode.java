package com.iprogrammerr.algorithms_data_structures.tree.fast;

public final class RedBlackNode<T> {

	private T data;
	private boolean red;
	private RedBlackNode<T> parent;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;

	public RedBlackNode(T data, boolean red) {
		this.data = data;
		this.red = red;
	}

	public RedBlackNode(T data) {
		this(data, true);
	}

	public T data() {
		return this.data;
	}

	public boolean isRed() {
		return this.red;
	}

	public RedBlackNode<T> parent() {
		return this.parent;
	}

	public RedBlackNode<T> left() {
		return this.left;
	}

	public RedBlackNode<T> right() {
		return this.right;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	public void setLeft(RedBlackNode<T> left) {
		this.left = left;
	}

	public void setRight(RedBlackNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "RedBlackNode [data=" + data + ", red=" + red + ", parent=" + parent + "]";
	}

}
