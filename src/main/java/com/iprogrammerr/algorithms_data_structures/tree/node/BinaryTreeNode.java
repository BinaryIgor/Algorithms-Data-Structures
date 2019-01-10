package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public final class BinaryTreeNode<T extends Comparable<T>> implements BinaryNode<T> {

    private final Potential<BinaryNode<T>> left;
    private final Potential<BinaryNode<T>> right;
    private T data;

    public BinaryTreeNode(Potential<BinaryNode<T>> left, Potential<BinaryNode<T>> right, T data) {
	this.left = left;
	this.right = right;
	this.data = data;
    }

    public BinaryTreeNode(T data) {
	this(new Potential<>(), new Potential<>(), data);
    }

    @Override
    public T data() {
	return this.data;
    }

    @Override
    public Potential<BinaryNode<T>> left() {
	return this.left;
    }

    @Override
    public Potential<BinaryNode<T>> right() {
	return this.right;
    }

    @Override
    public void changeLeft(BinaryNode<T> node) {
	this.left.revalue(node);
    }

    @Override
    public void changeLeft(Potential<BinaryNode<T>> node) {
	this.left.revalue(node);
    }

    @Override
    public void changeRight(BinaryNode<T> node) {
	this.right.revalue(node);
    }

    @Override
    public void changeRight(Potential<BinaryNode<T>> node) {
	this.right.revalue(node);
    }

    @Override
    public void data(T data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return "BinaryTreeNode [data=" + data + "]";
    }

}
