package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.tree.node.WithHeightBinaryNode;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {

	private WithHeightBinaryNode<T> root;

	public AvlTree(T root) {
		this.root = new WithHeightBinaryNode<>(root);
	}

	public AvlTree() {

	}

	@Override
	public void insert(T data) {
		root = insert(root, data);
	}

	private WithHeightBinaryNode<T> insert(WithHeightBinaryNode<T> root, T data) {
		if (root == null) {
			return new WithHeightBinaryNode<>(data);
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

	private WithHeightBinaryNode<T> settleInsertion(WithHeightBinaryNode<T> node, T data) {
		int balance = balance(node);
		if (balance >= -1 && balance <= 1) {
			return node;
		}
		WithHeightBinaryNode<T> settled;
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

	private int balance(WithHeightBinaryNode<T> node) {
		if (node == null) {
			return 0;
		}
		return height(node.leftChild) - height(node.rightChild);
	}

	private int height(WithHeightBinaryNode<T> node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = node.hasLeftChild() ? node.leftChild.height : -1;
		int rightHeight = node.hasRightChild() ? node.rightChild.height : -1;
		return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
	}

	private WithHeightBinaryNode<T> rightRotation(WithHeightBinaryNode<T> node) {
		WithHeightBinaryNode<T> leftNode = node.leftChild;
		WithHeightBinaryNode<T> leftRightNode = leftNode.rightChild;

		node.leftChild = leftRightNode;
		leftNode.rightChild = node;

		node.height = height(node);
		leftNode.height = height(leftNode);
		return leftNode;
	}

	private WithHeightBinaryNode<T> leftRotation(WithHeightBinaryNode<T> node) {
		WithHeightBinaryNode<T> rightNode = node.rightChild;
		WithHeightBinaryNode<T> rightLeftNode = rightNode.leftChild;

		node.rightChild = rightLeftNode;
		rightNode.leftChild = node;

		node.height = height(node);
		rightNode.height = height(rightNode);
		return rightNode;
	}

	@Override
	public void delete(T data) {
		if (root == null) {
			return;
		}
		WithHeightBinaryNode<T> deleted = delete(root, data);
		boolean newRoot = deleted != null && deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	private WithHeightBinaryNode<T> delete(WithHeightBinaryNode<T> root, T data) {
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
		root.height = height(root);
		return root == null ? null : settleDeletion(root);
	}

	private WithHeightBinaryNode<T> settleDeletion(WithHeightBinaryNode<T> node) {
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
	public T search(T data) {
		return search(root, data).data;
	}

	private WithHeightBinaryNode<T> search(WithHeightBinaryNode<T> root, T data) {
		int comparisonValue = root.data.compareTo(data);
		WithHeightBinaryNode<T> foundNode;
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
	public T min() {
		return min(root);
	}

	private T min(WithHeightBinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(WithHeightBinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public Iterable<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(WithHeightBinaryNode<T> root, List<T> items) {
		if (root.hasLeftChild()) {
			items(root.leftChild, items);
		}
		items.add(root.data);
		if (root.hasLeftChild()) {
			items(root.rightChild, items);
		}
		return items;
	}

	@Override
	public void traverse() {
		if (root != null) {
			System.out.println("Root = " + root);
			traverse(root);
		}
	}

	private void traverse(WithHeightBinaryNode<T> node) {
		if (node.hasLeftChild()) {
			traverse(node.leftChild);
		}
		System.out.println(node.data);
		if (node.hasRightChild()) {
			traverse(node.rightChild);
		}
	}
}
