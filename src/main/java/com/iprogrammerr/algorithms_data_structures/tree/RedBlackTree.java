package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.iprogrammerr.algorithms_data_structures.tree.node.RedBlackBinaryNode;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

	private RedBlackBinaryNode<T> root;

	public RedBlackTree(T data) {
		root = new RedBlackBinaryNode<T>(data);
	}

	public RedBlackTree() {

	}

	@Override
	public void put(T data) {
		RedBlackBinaryNode<T> node = new RedBlackBinaryNode<>(data);
		root = insert(root, node);
		if (hasGrandParent(node)) {
			fixViolations(node);
		}
	}

	private RedBlackBinaryNode<T> insert(RedBlackBinaryNode<T> root, RedBlackBinaryNode<T> node) {
		if (root == null) {
			return node;
		}
		int comparisonValue = node.data.compareTo(root.data);
		if (comparisonValue == 0) {
			throw new RuntimeException("Duplicated values are not allowed");
		}
		if (comparisonValue < 0) {
			root.leftChild = insert(root.leftChild, node);
			root.leftChild.parent = root;
		} else {
			root.rightChild = insert(root.rightChild, node);
			root.rightChild.parent = root;
		}
		return root;
	}

	private boolean hasGrandParent(RedBlackBinaryNode<T> node) {
		return !node.isRoot() && !node.parent.isRoot();
	}

	private void fixViolations(RedBlackBinaryNode<T> node) {
		while (shouldFixViolation(node)) {
			RedBlackBinaryNode<T> parent = node.parent;
			RedBlackBinaryNode<T> grandParent = parent.parent;
			if (grandParent.hasLeftChild() && parent.equals(grandParent.leftChild)) {
				node = fixLeftChildViolation(grandParent, parent, node);
			} else {
				node = fixRightChildViolation(grandParent, parent, node);
			}
		}
		if (root.isRed()) {
			root.setBlack();
		}
	}

	private boolean shouldFixViolation(RedBlackBinaryNode<T> node) {
		return !node.equals(root) && node.isRed() && isRed(node.parent);
	}

	public boolean hasViolations() {
		boolean has = false;
		if (root != null) {
			try {
				traverseNodes(root, n -> {
					if (shouldFixViolation(n)) {
						throw new RuntimeException("Violation!");
					}
				});
			} catch (Exception e) {
				has = true;
			}
		}
		return has;
	}

	private RedBlackBinaryNode<T> fixLeftChildViolation(RedBlackBinaryNode<T> grandParent, RedBlackBinaryNode<T> parent,
		RedBlackBinaryNode<T> node) {
		RedBlackBinaryNode<T> uncle = grandParent.rightChild;
		if (isRed(uncle)) {
			grandParent.setRed();
			parent.setBlack();
			uncle.setBlack();
			node = grandParent;
		} else {
			if (parent.hasRightChild() && node.equals(parent.rightChild)) {
				rotateLeft(parent);
				node = parent;
				parent = node.parent;
			}
			rotateRight(grandParent);
			boolean redParent = parent.isRed();
			parent.setRed(grandParent.isRed());
			grandParent.setRed(redParent);
			node = parent;
		}
		return node;
	}

	private RedBlackBinaryNode<T> fixRightChildViolation(RedBlackBinaryNode<T> grandParent,
		RedBlackBinaryNode<T> parent, RedBlackBinaryNode<T> node) {
		RedBlackBinaryNode<T> uncle = grandParent.leftChild;
		if (isRed(uncle)) {
			grandParent.setRed();
			parent.setBlack();
			uncle.setBlack();
			node = grandParent;
		} else {
			if (parent.hasLeftChild() && node.equals(parent.leftChild)) {
				rotateRight(parent);
				node = parent;
				parent = node.parent;
			}
			rotateLeft(grandParent);
			boolean redParent = parent.isRed();
			parent.setRed(grandParent.isRed());
			grandParent.setRed(redParent);
			node = parent;
		}
		return node;
	}

	private void rotateRight(RedBlackBinaryNode<T> node) {
		RedBlackBinaryNode<T> leftNode = node.leftChild;
		node.leftChild = leftNode.rightChild;
		if (node.hasLeftChild()) {
			node.leftChild.parent = node;
		}

		RedBlackBinaryNode<T> parent = node.parent;
		leftNode.parent = parent;
		if (leftNode.isRoot()) {
			root = leftNode;
		} else if (parent.hasLeftChild() && node.equals(parent.leftChild)) {
			parent.leftChild = leftNode;
		} else {
			parent.rightChild = leftNode;
		}

		leftNode.rightChild = node;
		node.parent = leftNode;
	}

	private void rotateLeft(RedBlackBinaryNode<T> node) {
		RedBlackBinaryNode<T> rightNode = node.rightChild;
		node.rightChild = rightNode.leftChild;
		if (node.hasRightChild()) {
			node.rightChild.parent = node;
		}

		RedBlackBinaryNode<T> parent = node.parent;
		rightNode.parent = parent;
		if (rightNode.isRoot()) {
			root = rightNode;
		} else if (parent.hasLeftChild() && node.equals(parent.leftChild)) {
			parent.leftChild = rightNode;
		} else {
			parent.rightChild = rightNode;
		}

		rightNode.leftChild = node;
		node.parent = rightNode;
	}

	@Override
	public void delete(T data) {
		RedBlackBinaryNode<T> deleted = delete(root, data);
		if (deleted == null) {
			return;
		}
		boolean newRoot = deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	// TODO in future - proper red black tree delete
	private RedBlackBinaryNode<T> delete(RedBlackBinaryNode<T> root, T data) {
		if (root == null) {
			return root;
		}
		int comparisonValue = root.data.compareTo(data);
		if (comparisonValue > 0) {
			root.leftChild = delete(root.leftChild, data);
		} else if (comparisonValue < 0) {
			root.rightChild = delete(root.rightChild, data);
		} else if (!root.hasLeftChild()) {
			if (root.hasRightChild()) {
				root.rightChild.parent = root.parent;
			}
			root = root.rightChild;
		} else if (!root.hasRightChild()) {
			if (root.hasLeftChild()) {
				root.leftChild.parent = root.parent;
			}
			root = root.leftChild;
		} else {
			T successor = min(root.rightChild);
			root.rightChild = delete(root.rightChild, successor);
			root.data = successor;
		}
		return root;
	}

	private boolean isRed(RedBlackBinaryNode<T> node) {
		return node != null && node.isRed();
	}

	@Override
	public boolean contains(T data) {
		return search(root, data) != null;
	}

	private RedBlackBinaryNode<T> search(RedBlackBinaryNode<T> root, T data) {
		int comparisonValue = root.data.compareTo(data);
		RedBlackBinaryNode<T> foundNode;
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

	private T min(RedBlackBinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(RedBlackBinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public List<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(RedBlackBinaryNode<T> root, List<T> items) {
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
			traverseNodes(root, n -> itemConsumer.accept(n.data));
		}
	}

	private void traverseNodes(RedBlackBinaryNode<T> node, Consumer<RedBlackBinaryNode<T>> nodeConsumer) {
		if (node.hasLeftChild()) {
			traverseNodes(node.leftChild, nodeConsumer);
		}
		nodeConsumer.accept(node);
		if (node.hasRightChild()) {
			traverseNodes(node.rightChild, nodeConsumer);
		}
	}

}
