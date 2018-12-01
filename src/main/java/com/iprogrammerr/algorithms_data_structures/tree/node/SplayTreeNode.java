package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public final class SplayTreeNode<T extends Comparable<T>> implements WithParentBinaryNode<T> {

    private final Potential<WithParentBinaryNode<T>> parent;
    private final Potential<WithParentBinaryNode<T>> left;
    private final Potential<WithParentBinaryNode<T>> right;
    private T data;

    public SplayTreeNode(Potential<WithParentBinaryNode<T>> parent, Potential<WithParentBinaryNode<T>> left,
	    Potential<WithParentBinaryNode<T>> right, T data) {
	this.parent = parent;
	this.left = left;
	this.right = right;
	this.data = data;
    }

    public SplayTreeNode(T data) {
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
    public Potential<WithParentBinaryNode<T>> parent() {
	return this.parent;
    }

    @Override
    public void changeParent(WithParentBinaryNode<T> node) {
	this.parent.fill(node);
    }

    @Override
    public void changeParent(Potential<WithParentBinaryNode<T>> node) {
	this.parent.change(node);
    }

    @Override
    public Potential<WithParentBinaryNode<T>> left() {
	return this.left;
    }

    @Override
    public void changeLeft(WithParentBinaryNode<T> node) {
	this.left.fill(node);
    }

    @Override
    public void changeLeft(Potential<WithParentBinaryNode<T>> node) {
	this.left.change(node);
    }

    @Override
    public Potential<WithParentBinaryNode<T>> right() {
	return this.right;
    }

    @Override
    public void changeRight(WithParentBinaryNode<T> node) {
	this.right.fill(node);
    }

    @Override
    public void changeRight(Potential<WithParentBinaryNode<T>> node) {
	this.right.change(node);
    }

    @Override
    public String toString() {
	return "SplayTreeNode [data=" + data + "]";
    }

}
