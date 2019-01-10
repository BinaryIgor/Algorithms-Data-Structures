package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public final class AvlTreeNode<T extends Comparable<T>> implements WithHeightBinaryNode<T> {

    private final Potential<WithHeightBinaryNode<T>> left;
    private final Potential<WithHeightBinaryNode<T>> right;
    private T data;
    private int height;

    public AvlTreeNode(Potential<WithHeightBinaryNode<T>> left, Potential<WithHeightBinaryNode<T>> right, T data,
	    int height) {
	this.left = left;
	this.right = right;
	this.data = data;
	this.height = height;
    }

    public AvlTreeNode(T data) {
	this(new Potential<>(), new Potential<>(), data, 0);
    }

    @Override
    public T data() {
	return this.data;
    }

    @Override
    public void changeData(T data) {
	this.data = data;
    }

    @Override
    public Potential<WithHeightBinaryNode<T>> left() {
	return this.left;
    }

    @Override
    public void changeLeft(WithHeightBinaryNode<T> node) {
	this.left.revalue(node);
    }

    @Override
    public void changeLeft(Potential<WithHeightBinaryNode<T>> node) {
	this.left.revalue(node);
    }

    @Override
    public Potential<WithHeightBinaryNode<T>> right() {
	return this.right;
    }

    @Override
    public void changeRight(Potential<WithHeightBinaryNode<T>> node) {
	this.right.revalue(node);
    }

    @Override
    public void changeRight(WithHeightBinaryNode<T> node) {
	this.right.revalue(node);
    }

    @Override
    public int height() {
	return this.height;
    }

    @Override
    public void changeHeight(int height) {
	this.height = height;
    }

    @Override
    public String toString() {
	return "AvlTreeNode [data=" + data + ", height=" + height + "]";
    }

}
