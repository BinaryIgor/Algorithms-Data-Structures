package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.iprogrammerr.algorithms_data_structures.tree.node.SplayBinaryNode;

public final class SplayTree<T extends Comparable<T>> implements Tree<T> {

	private SplayBinaryNode<T> root;

	public SplayTree(T data) {
		root = new SplayBinaryNode<>(data);
	}

	public SplayTree() {

	}

	@Override
	public void put(T data) {
		SplayBinaryNode<T> node = new SplayBinaryNode<>(data);
		root = insert(root, node);
		splay(node);
	}

	private SplayBinaryNode<T> insert(SplayBinaryNode<T> root, SplayBinaryNode<T> node) {
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

	private void splay(SplayBinaryNode<T> node) {
		while (!node.isRoot()) {
			SplayBinaryNode<T> parent = node.parent;
			if (parent.isRoot()) {
				if (parent.hasLeftChild() && parent.leftChild.equals(node)) {
					rotateRight(parent);
				} else {
					rotateLeft(parent);
				}
			} else if (isLeftZigZig(parent, node)) {
				rotateRight(parent.parent);
				rotateRight(parent);
			} else if (isRightZigZig(parent, node)) {
				rotateLeft(parent.parent);
				rotateLeft(parent);
			} else if (isRightLeftZigZag(parent, node)) {
				rotateRight(parent);
				rotateLeft(node.parent);
			} else {
				rotateLeft(parent);
				rotateRight(node.parent);
			}
		}
	}

	private boolean isLeftZigZig(SplayBinaryNode<T> parent, SplayBinaryNode<T> node) {
		return parent.hasLeftChild() && parent.leftChild.equals(node) && parent.equals(parent.parent.leftChild);
	}

	private boolean isRightZigZig(SplayBinaryNode<T> parent, SplayBinaryNode<T> node) {
		return parent.hasRightChild() && parent.rightChild.equals(node) && parent.equals(parent.parent.rightChild);
	}

	private boolean isRightLeftZigZag(SplayBinaryNode<T> parent, SplayBinaryNode<T> node) {
		return parent.hasLeftChild() && parent.leftChild.equals(node) && parent.equals(parent.parent.rightChild);
	}

	@Override
	public void delete(T data) {
		SplayBinaryNode<T> deleted = delete(root, data);
		boolean newRoot = deleted != null && deleted.data.compareTo(root.data) != 0;
		if (newRoot) {
			root = deleted;
		}
	}

	private SplayBinaryNode<T> delete(SplayBinaryNode<T> root, T data) {
		if (root == null) {
			return null;
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

	private void rotateLeft(SplayBinaryNode<T> node) {
		SplayBinaryNode<T> rightNode = node.rightChild;
		node.rightChild = rightNode.leftChild;
		if (node.hasRightChild()) {
			node.rightChild.parent = node;
		}

		SplayBinaryNode<T> parent = node.parent;
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

	private void rotateRight(SplayBinaryNode<T> node) {
		System.out.println("Rotating to the right = " + node);
		SplayBinaryNode<T> leftNode = node.leftChild;
		node.leftChild = leftNode.rightChild;
		if (node.hasLeftChild()) {
			node.leftChild.parent = node;
		}

		SplayBinaryNode<T> parent = node.parent;
		leftNode.parent = parent;
		if (leftNode.isRoot()) {
			root = leftNode;
		} else if (parent.hasLeftChild() && node.equals(parent.leftChild)) {
			parent.leftChild = leftNode;
		} else {
			parent.rightChild = leftNode;
		}

		leftNode.leftChild = node;
		node.parent = leftNode;
	}

	@Override
	public boolean contains(T data) {
		SplayBinaryNode<T> node = search(root, data);
		if (node == null) {
			return false;
		}
		splay(node);
		return true;
	}

	private SplayBinaryNode<T> search(SplayBinaryNode<T> root, T data) {
		int comparison = data.compareTo(root.data);
		SplayBinaryNode<T> found;
		if (comparison == 0) {
			found = root;
		} else if (comparison > 0 && root.hasRightChild()) {
			found = search(root.rightChild, data);
		} else if (root.hasLeftChild()) {
			found = search(root.leftChild, data);
		} else {
			found = null;
		}
		return found;
	}

	@Override
	public T min() {
		return min(root);
	}

	private T min(SplayBinaryNode<T> root) {
		if (root.hasLeftChild()) {
			return min(root.leftChild);
		}
		return root.data;
	}

	@Override
	public T max() {
		return max(root);
	}

	private T max(SplayBinaryNode<T> root) {
		if (root.hasRightChild()) {
			return max(root.rightChild);
		}
		return root.data;
	}

	@Override
	public List<T> items() {
		return root == null ? new ArrayList<>() : items(root, new ArrayList<>());
	}

	private List<T> items(SplayBinaryNode<T> root, List<T> items) {
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

	private void traverse(SplayBinaryNode<T> root, Consumer<T> itemConsumer) {
		if (root.hasLeftChild()) {
			traverse(root.leftChild, itemConsumer);
		}
		itemConsumer.accept(root.data);
		if (root.hasRightChild()) {
			traverse(root.rightChild, itemConsumer);
		}
	}
}
