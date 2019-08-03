package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.tree.node.DefaultBinaryNode;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private DefaultBinaryNode<T> root;

	public BinarySearchTree(T data) {
		root = new DefaultBinaryNode<>(data);
	}

	public BinarySearchTree() {

	}

	@Override
	public void insert(T data) {
		root = insert(root, data);
	}

	private DefaultBinaryNode<T> insert(DefaultBinaryNode<T> root, T data) {
		if (root == null) {
			return new DefaultBinaryNode<>(data);
		}
		int comparisonValue = data.compareTo(root.data);
		if (comparisonValue == 0) {
			throw new RuntimeException("Duplicated values are not allowed");
		}
		if (comparisonValue < 0) {
			root.leftChild = insert(root.leftChild, data);
		} else {
			root.rightChild = insert(root.rightChild, data);
		}
		return root;
	}

	@Override
	public T search(T data) {
		return search(root, data).data;
	}

	private DefaultBinaryNode<T> search(DefaultBinaryNode<T> root, T data) {
		int comparisonValue = root.data.compareTo(data);
		DefaultBinaryNode<T> foundNode;
		if (comparisonValue == 0) {
			foundNode = root;
		} else if (comparisonValue > 0 && root.hasLeftChild()) {
			foundNode = search(root.leftChild, data);
		} else if (comparisonValue < 0 && root.hasRightChild()) {
			foundNode = search(root.rightChild, data);
		} else {
			throw new RuntimeException("Needed data is not present");
		}
		return foundNode;
	}

	@Override
	public void delete(T data) {
		DefaultBinaryNode<T> deleted = delete(root, data);
		boolean newRoot = deleted != null && deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	private DefaultBinaryNode<T> delete(DefaultBinaryNode<T> root, T data) {
		if (root == null) {
			return root;
		}
		int comparisonValue = root.data.compareTo(data);
		if (comparisonValue > 0) {
			root.leftChild = delete(root.leftChild, data);
		} else if (comparisonValue < 0) {
			root.rightChild = delete(root.rightChild, data);
		} else if (!root.hasLeftChild()) {
			root = root.rightChild;
		} else if (!root.hasRightChild()) {
			root = root.leftChild;
		} else {
			T maxFromLeftSubTree = max(root.leftChild);
			root.leftChild = delete(root.leftChild, maxFromLeftSubTree);
			root.data = maxFromLeftSubTree;
		}
		return root;
	}

	@Override
	public Iterable<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(DefaultBinaryNode<T> root, List<T> items) {
		if (root.hasLeftChild()) {
			items(root.leftChild, items);
		}
		items.add(root.data);
		if (root.hasRightChild()) {
			items(root.rightChild, items);
		}
		return items;
	}

	@Override
	public T min() {
		return min(root);
	}

	private T min(DefaultBinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(DefaultBinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public void traverse() {
		if (root != null) {
			traverse(root);
		}
	}

	private void traverse(DefaultBinaryNode<T> node) {
		if (node.hasLeftChild()) {
			traverse(node.leftChild);
		}
		System.out.println(node.data);
		if (node.hasRightChild()) {
			traverse(node.rightChild);
		}
	}
}
