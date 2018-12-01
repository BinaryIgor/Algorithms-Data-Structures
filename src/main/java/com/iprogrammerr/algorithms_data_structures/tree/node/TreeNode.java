package com.iprogrammerr.algorithms_data_structures.tree.node;

public final class TreeNode<T> implements Node<T> {

    private final Node<T> parent;
    private final T data;

    public TreeNode(Node<T> parent, T data) {
	this.parent = parent;
	this.data = data;
    }

    @Override
    public Node<T> parent() {
	return parent;
    }

    @Override
    public T data() {
	return data;
    }

    @Override
    public boolean hasParent() {
	return parent != null;
    }

}
