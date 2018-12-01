package com.iprogrammerr.algorithms_data_structures.tst;

public final class SimpleTernarySearchTree<T> implements TernarySearchTree<T> {

	private TernarySearchTreeNode<T> root;

	@Override
	public void put(String key, T value) {
		this.root = put(this.root, key, value, 0);
	}

	private TernarySearchTreeNode<T> put(TernarySearchTreeNode<T> node, String key, T value, int index) {
		char c = key.charAt(index);
		if (node == null) {
			node = new TernarySearchTreeNode<>(c);
		}
		if (c < node.character()) {
			node.setLeft(put(node.left(), key, value, index));
		} else if (c > node.character()) {
			node.setRight(put(node.left(), key, value, index));
		} else if (index < key.length() - 1) {
			node.setMiddle(put(node.middle(), key, value, index + 1));
		} else {
			node.setValue(value);
		}
		return node;
	}

	@Override
	public T value(String key) throws Exception {
		T value = node(this.root, key, 0).value();
		if (value == null) {
			throw new Exception(String.format("There is no value associated with %s key", key));
		}
		return value;
	}

	private TernarySearchTreeNode<T> node(TernarySearchTreeNode<T> node, String key, int index) throws Exception {
		if (node == null) {
			throw new Exception(String.format("There is no node associated with %s key", key));
		}
		char c = key.charAt(index);
		if (c < node.character()) {
			node = node(node.left(), key, index);
		} else if (c > node.character()) {
			node = node(node.right(), key, index);
		} else if (index < key.length() - 1) {
			node = node(node.middle(), key, index + 1);
		}
		return node;
	}
}
