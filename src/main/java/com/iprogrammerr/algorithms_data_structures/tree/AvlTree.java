package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.model.Potential;
import com.iprogrammerr.algorithms_data_structures.tree.node.AvlTreeNode;
import com.iprogrammerr.algorithms_data_structures.tree.node.WithHeightBinaryNode;

public final class AvlTree<T extends Comparable<T>> implements Tree<T> {

	private final Potential<WithHeightBinaryNode<T>> root;

	public AvlTree(Potential<WithHeightBinaryNode<T>> root) {
		this.root = root;
	}

	public AvlTree(T data) {
		this(new Potential<>(new AvlTreeNode<>(data)));
	}

	public AvlTree(int balanceThreshold) {
		this(new Potential<>());
	}

	@Override
	public void insert(T data) throws Exception {
		this.root.revalue(insert(this.root, data));
	}

	private WithHeightBinaryNode<T> insert(Potential<WithHeightBinaryNode<T>> root, T data) throws Exception {
		if (root.isEmpty()) {
			return new AvlTreeNode<>(data);
		}
		WithHeightBinaryNode<T> node = root.value();
		int comparisonValue = data.compareTo(node.data());
		if (comparisonValue == 0) {
			throw new Exception("Duplicated values are not allowed");
		}
		if (comparisonValue < 0) {
			node.changeLeft(insert(node.left(), data));
		} else {
			node.changeRight(insert(node.right(), data));
		}
		node.changeHeight(height(node));
		return settleInsertion(root, data);
	}

	private WithHeightBinaryNode<T> settleInsertion(Potential<WithHeightBinaryNode<T>> node, T data) throws Exception {
		int balance = balance(node);
		if (balance >= -1 && balance <= 1) {
			return node.value();
		}
		WithHeightBinaryNode<T> nodeValue = node.value();
		WithHeightBinaryNode<T> settled;
		int comparisonValue;
		if (balance > 1) {
			comparisonValue = data.compareTo(nodeValue.left().value().data());
			if (comparisonValue > 0) {
				nodeValue.changeLeft(leftRotation(nodeValue.left().value()));
			}
			settled = rightRotation(nodeValue);
		} else {
			comparisonValue = data.compareTo(nodeValue.right().value().data());
			if (comparisonValue < 0) {
				nodeValue.changeRight(rightRotation(nodeValue.right().value()));
			}
			settled = leftRotation(nodeValue);
		}
		return settled;
	}

	private int balance(Potential<WithHeightBinaryNode<T>> node) throws Exception {
		if (node.isEmpty()) {
			return 0;
		}
		return height(node.value().left()) - height(node.value().right());
	}

	private int height(WithHeightBinaryNode<T> node) {
		int leftHeight = height(node.left());
		int rightHeight = height(node.right());
		return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
	}

	private int height(Potential<WithHeightBinaryNode<T>> node) {
		if (node.isEmpty()) {
			return -1;
		}
		try {
			return node.value().height();
		} catch (Exception exception) {
			return -1;
		}
	}

	private WithHeightBinaryNode<T> rightRotation(WithHeightBinaryNode<T> node) throws Exception {
		WithHeightBinaryNode<T> leftNode = node.left().value();
		Potential<WithHeightBinaryNode<T>> leftRightNode = leftNode.right();
		node.changeLeft(leftRightNode);
		leftNode.changeRight(node);
		node.changeHeight(height(node));
		leftNode.changeHeight(height(leftNode));
		return leftNode;
	}

	private WithHeightBinaryNode<T> leftRotation(WithHeightBinaryNode<T> node) throws Exception {
		WithHeightBinaryNode<T> rightNode = node.right().value();
		Potential<WithHeightBinaryNode<T>> rightLeftNode = rightNode.left();
		node.changeRight(rightLeftNode);
		rightNode.changeLeft(node);
		node.changeHeight(height(node));
		rightNode.changeHeight(height(rightNode));
		return rightNode;
	}

	@Override
	public void delete(T data) {
		try {
			Potential<WithHeightBinaryNode<T>> deleted = delete(new Potential<>(this.root).value(), data);
			boolean newRoot = deleted.hasValue() && deleted.value().data().compareTo(this.root.value().data()) != 0;
			if (newRoot) {
				this.root.revalue(deleted.value());
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Potential<WithHeightBinaryNode<T>> delete(Potential<WithHeightBinaryNode<T>> root, T data)
			throws Exception {
		if (root.isEmpty()) {
			return root;
		}
		WithHeightBinaryNode<T> rootValue = root.value();
		int comparisonValue = rootValue.data().compareTo(data);
		if (comparisonValue > 0) {
			rootValue.changeLeft(delete(rootValue.left(), data));
		} else if (comparisonValue < 0) {
			rootValue.changeRight(delete(rootValue.right(), data));
		} else if (rootValue.left().isEmpty()) {
			root = rootValue.right();
		} else if (rootValue.right().isEmpty()) {
			root = rootValue.left();
		} else {
			T maxFromLeftSubTree = max(rootValue.left().value());
			rootValue.changeLeft(delete(rootValue.left(), maxFromLeftSubTree));
			rootValue.changeData(maxFromLeftSubTree);
		}
		rootValue.changeHeight(height(rootValue));
		return root.hasValue() ? settleDeletion(root) : root;
	}

	private Potential<WithHeightBinaryNode<T>> settleDeletion(Potential<WithHeightBinaryNode<T>> node)
			throws Exception {
		int balance = balance(node);
		if (balance >= -1 && balance <= 1) {
			return node;
		}
		WithHeightBinaryNode<T> nodeValue = node.value();
		if (balance > 1) {
			if (balance(nodeValue.left()) < 0) {
				nodeValue.changeLeft(leftRotation(nodeValue.left().value()));
			}
			nodeValue = rightRotation(nodeValue);
		} else if (balance < -1) {
			if (balance(nodeValue.right()) > 0) {
				nodeValue.changeRight(rightRotation(nodeValue.right().value()));
			}
			nodeValue = leftRotation(nodeValue);
		}
		node.revalue(nodeValue);
		return node;
	}

	@Override
	public T search(T data) throws Exception {
		return search(this.root.value(), data).data();
	}

	private WithHeightBinaryNode<T> search(WithHeightBinaryNode<T> root, T data) throws Exception {
		int comparisonValue = root.data().compareTo(data);
		WithHeightBinaryNode<T> foundNode;
		if (comparisonValue == 0) {
			foundNode = root;
		} else if (comparisonValue > 0 && root.left().hasValue()) {
			foundNode = search(root.left().value(), data);
		} else if (comparisonValue < 0 && root.right().hasValue()) {
			foundNode = search(root.right().value(), data);
		} else {
			throw new Exception("Needed data is not present");
		}
		return foundNode;
	}

	@Override
	public T min() throws Exception {
		return min(this.root.value());
	}

	private T min(WithHeightBinaryNode<T> root) throws Exception {
		if (root.left().hasValue()) {
			return min(root.left().value());
		}
		return root.data();
	}

	@Override
	public T max() throws Exception {
		return max(this.root.value());
	}

	private T max(WithHeightBinaryNode<T> root) throws Exception {
		if (root.right().hasValue()) {
			return max(root.right().value());
		}
		return root.data();
	}

	@Override
	public Iterable<T> items() {
		List<T> items = new ArrayList<>();
		try {
			items = items(this.root.value(), items);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return items;
	}

	private List<T> items(WithHeightBinaryNode<T> root, List<T> items) throws Exception {
		if (root.left().hasValue()) {
			items(root.left().value(), items);
		}
		items.add(root.data());
		if (root.right().hasValue()) {
			items(root.right().value(), items);
		}
		return items;
	}

	@Override
	public void traverse() {
		try {
			if (this.root.hasValue()) {
				System.out.println("Root = " + this.root);
				traverse(this.root.value());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void traverse(WithHeightBinaryNode<T> node) throws Exception {
		if (node.left().hasValue()) {
			traverse(node.left().value());
		}
		System.out.println(node.data());
		if (node.right().hasValue()) {
			traverse(node.right().value());
		}
	}

}
