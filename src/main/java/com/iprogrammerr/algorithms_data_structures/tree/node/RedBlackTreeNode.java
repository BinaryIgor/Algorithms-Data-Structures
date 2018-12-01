package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public final class RedBlackTreeNode<T extends Comparable<T>> implements ColoredBinaryNode<T> {

	private static final String RED = "red";
	private static final String BLACK = "black";
	private final Potential<ColoredBinaryNode<T>> parent;
	private final Potential<ColoredBinaryNode<T>> left;
	private final Potential<ColoredBinaryNode<T>> right;
	private String color;
	private T data;

	public RedBlackTreeNode(Potential<ColoredBinaryNode<T>> parent,
			Potential<ColoredBinaryNode<T>> left, Potential<ColoredBinaryNode<T>> right, T data) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.color = RED;
		this.data = data;
	}

	public RedBlackTreeNode(T data) {
		this(new Potential<>(), new Potential<>(), new Potential<>(), data);
	}

	@Override
	public T data() {
		return this.data;
	}

	@Override
	public void data(T data) {
		this.data = data;
	}

	@Override
	public String color() {
		return this.color;
	}

	@Override
	public void changeColor(String color) {
		if (RED.equalsIgnoreCase(color)) {
			this.color = RED;
		} else {
			this.color = BLACK;
		}
	}

	@Override
	public Potential<ColoredBinaryNode<T>> parent() {
		return this.parent;
	}

	@Override
	public Potential<ColoredBinaryNode<T>> left() {
		return this.left;
	}

	@Override
	public void changeLeft(Potential<ColoredBinaryNode<T>> node) {
		this.left.change(node);
	}

	@Override
	public void changeLeft(ColoredBinaryNode<T> node) {
		this.left.fill(node);
	}

	@Override
	public Potential<ColoredBinaryNode<T>> right() {
		return this.right;
	}

	@Override
	public void changeRight(Potential<ColoredBinaryNode<T>> node) {
		this.right.change(node);
	}

	@Override
	public void changeRight(ColoredBinaryNode<T> node) {
		this.right.fill(node);
	}

	@Override
	public String toString() {
		return "RedBlackTreeNode [color=" + color + ", data=" + data + ", parent=" + parent + "]";
	}

	@Override
	public void changeParent(ColoredBinaryNode<T> node) {
		this.parent.fill(node);
	}

	@Override
	public void changeParent(Potential<ColoredBinaryNode<T>> node) {
		this.parent.change(node);
	}

}
