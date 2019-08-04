package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.iprogrammerr.algorithms_data_structures.tree.node.BinaryNode;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private BinaryNode<T> root;

	public BinarySearchTree(T data) {
		root = new BinaryNode<>(data);
	}

	public BinarySearchTree() {

	}

	@Override
	public void put(T data) {
		root = insert(root, data);
	}

	private BinaryNode<T> insert(BinaryNode<T> root, T data) {
		if (root == null) {
			return new BinaryNode<>(data);
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
	public boolean contains(T data) {
		return search(root, data) != null;
	}

	private BinaryNode<T> search(BinaryNode<T> root, T data) {
		int comparisonValue = root.data.compareTo(data);
		BinaryNode<T> foundNode;
		if (comparisonValue == 0) {
			foundNode = root;
		} else if (comparisonValue > 0 && root.hasLeftChild()) {
			foundNode = search(root.leftChild, data);
		} else if (comparisonValue < 0 && root.hasRightChild()) {
			foundNode = search(root.rightChild, data);
		} else {
			foundNode = null;
		}
		return foundNode;
	}

	@Override
	public void delete(T data) {
		BinaryNode<T> deleted = delete(root, data);
		boolean newRoot = deleted != null && deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	private BinaryNode<T> delete(BinaryNode<T> root, T data) {
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
	public List<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(BinaryNode<T> root, List<T> items) {
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

	private T min(BinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(BinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public void traverse(Consumer<T> itemConsumer) {
		if (root != null) {
			traverse(root, itemConsumer);
		}
	}

	private void traverse(BinaryNode<T> node, Consumer<T> itemConsumer) {
		if (node.hasLeftChild()) {
			traverse(node.leftChild, itemConsumer);
		}
		itemConsumer.accept(node.data);
		if (node.hasRightChild()) {
			traverse(node.rightChild, itemConsumer);
		}
	}
}
