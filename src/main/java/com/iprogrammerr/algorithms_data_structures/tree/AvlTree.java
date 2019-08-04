package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.iprogrammerr.algorithms_data_structures.tree.node.AvlBinaryNode;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {

	private AvlBinaryNode<T> root;

	public AvlTree(T data) {
		root = new AvlBinaryNode<>(data);
	}

	public AvlTree() {

	}

	@Override
	public void put(T data) {
		root = insert(root, data);
	}

	private AvlBinaryNode<T> insert(AvlBinaryNode<T> root, T data) {
		if (root == null) {
			return new AvlBinaryNode<>(data);
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
		root.height = height(root);
		return settleInsertion(root, data);
	}

	private AvlBinaryNode<T> settleInsertion(AvlBinaryNode<T> node, T data) {
		int balance = balance(node);
		if (balance >= -1 && balance <= 1) {
			return node;
		}
		AvlBinaryNode<T> settled;
		int comparisonValue;
		if (balance > 1) {
			comparisonValue = data.compareTo(node.leftChild.data);
			if (comparisonValue > 0) {
				node.leftChild = leftRotation(node.leftChild);
			}
			settled = rightRotation(node);
		} else {
			comparisonValue = data.compareTo(node.rightChild.data);
			if (comparisonValue < 0) {
				node.rightChild = rightRotation(node.rightChild);
			}
			settled = leftRotation(node);
		}
		return settled;
	}

	private int balance(AvlBinaryNode<T> node) {
		if (node == null) {
			return 0;
		}
		return height(node.leftChild) - height(node.rightChild);
	}

	public int balance() {
		return balance(root);
	}

	private int height(AvlBinaryNode<T> node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = node.hasLeftChild() ? node.leftChild.height : -1;
		int rightHeight = node.hasRightChild() ? node.rightChild.height : -1;
		return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
	}

	private AvlBinaryNode<T> rightRotation(AvlBinaryNode<T> node) {
		AvlBinaryNode<T> leftNode = node.leftChild;
		AvlBinaryNode<T> leftRightNode = leftNode.rightChild;

		node.leftChild = leftRightNode;
		leftNode.rightChild = node;

		node.height = height(node);
		leftNode.height = height(leftNode);
		return leftNode;
	}

	private AvlBinaryNode<T> leftRotation(AvlBinaryNode<T> node) {
		AvlBinaryNode<T> rightNode = node.rightChild;
		AvlBinaryNode<T> rightLeftNode = rightNode.leftChild;

		node.rightChild = rightLeftNode;
		rightNode.leftChild = node;

		node.height = height(node);
		rightNode.height = height(rightNode);
		return rightNode;
	}

	@Override
	public void delete(T data) {
		AvlBinaryNode<T> deleted = delete(root, data);
		boolean newRoot = deleted != null && deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	private AvlBinaryNode<T> delete(AvlBinaryNode<T> root, T data) {
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
		if (root == null) {
			return null;
		}
		root.height = height(root);
		return settleDeletion(root);
	}

	private AvlBinaryNode<T> settleDeletion(AvlBinaryNode<T> node) {
		int balance = balance(node);
		if (balance >= -1 && balance <= 1) {
			return node;
		}
		if (balance > 1) {
			if (balance(node.leftChild) < 0) {
				node.leftChild = leftRotation(node.leftChild);
			}
			node = rightRotation(node);
		} else if (balance < -1) {
			if (balance(node.rightChild) > 0) {
				node.rightChild = rightRotation(node.rightChild);
			}
			node = leftRotation(node);
		}
		return node;
	}

	@Override
	public boolean contains(T data) {
		return search(root, data) != null;
	}

	private AvlBinaryNode<T> search(AvlBinaryNode<T> root, T data) {
		int comparisonValue = root.data.compareTo(data);
		AvlBinaryNode<T> foundNode;
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
	public T min() {
		return min(root);
	}

	private T min(AvlBinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(AvlBinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public List<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(AvlBinaryNode<T> root, List<T> items) {
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
	public void traverse(Consumer<T> itemConsumer) {
		if (root != null) {
			traverse(root, itemConsumer);
		}
	}

	private void traverse(AvlBinaryNode<T> node, Consumer<T> itemConsumer) {
		if (node.hasLeftChild()) {
			traverse(node.leftChild, itemConsumer);
		}
		itemConsumer.accept(node.data);
		if (node.hasRightChild()) {
			traverse(node.rightChild, itemConsumer);
		}
	}
}
