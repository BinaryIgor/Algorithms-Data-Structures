package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.model.Potential;
import com.iprogrammerr.algorithms_data_structures.tree.node.SplayTreeNode;
import com.iprogrammerr.algorithms_data_structures.tree.node.WithParentBinaryNode;

public final class SplayTree<T extends Comparable<T>> implements Tree<T> {

	private final Potential<WithParentBinaryNode<T>> root;

	public SplayTree(WithParentBinaryNode<T> root) {
		this.root = new Potential<WithParentBinaryNode<T>>(root);
	}

	public SplayTree(T data) {
		this(new SplayTreeNode<>(data));
	}

	public SplayTree() {
		this.root = new Potential<>();
	}

	@Override
	public void insert(T data) throws Exception {
		WithParentBinaryNode<T> node = new SplayTreeNode<>(data);
		this.root.revalue(insert(this.root, node));
		splay(node);
	}

	private WithParentBinaryNode<T> insert(Potential<WithParentBinaryNode<T>> root, WithParentBinaryNode<T> node)
			throws Exception {
		if (root.isEmpty()) {
			return node;
		}
		WithParentBinaryNode<T> rootValue = root.value();
		int comparisonValue = node.data().compareTo(rootValue.data());
		if (comparisonValue == 0) {
			throw new Exception("Duplicated values are not allowed");
		}
		if (comparisonValue < 0) {
			rootValue.changeLeft(insert(rootValue.left(), node));
			rootValue.left().value().changeParent(rootValue);
		} else {
			rootValue.changeRight(insert(rootValue.right(), node));
			rootValue.right().value().changeParent(rootValue);
		}
		return rootValue;
	}

	private void splay(WithParentBinaryNode<T> node) throws Exception {
		while (node.parent().hasValue()) {
			WithParentBinaryNode<T> parent = node.parent().value();
			if (parent.parent().isEmpty()) {
				if (parent.left().hasValue() && parent.left().value().equals(node)) {
					rotateRight(parent);
				} else {
					rotateLeft(parent);
				}
			} else if (isLeftZigZig(parent, node)) {
				rotateRight(parent.parent().value());
				rotateRight(parent);
			} else if (isRightZigZig(parent, node)) {
				rotateLeft(parent.parent().value());
				rotateLeft(parent);
			} else if (isRightLeftZigZag(parent, node)) {
				rotateRight(parent);
				rotateLeft(node.parent().value());
			} else {
				rotateLeft(parent);
				rotateRight(node.parent().value());
			}
		}
	}

	private boolean isLeftZigZig(WithParentBinaryNode<T> parent, WithParentBinaryNode<T> node) throws Exception {
		return parent.left().hasValue() && parent.left().value().equals(node)
				&& parent.parent().value().left().value().equals(parent);
	}

	private boolean isRightZigZig(WithParentBinaryNode<T> parent, WithParentBinaryNode<T> node) throws Exception {
		return parent.right().hasValue() && parent.right().value().equals(node)
				&& parent.parent().value().right().value().equals(parent);
	}

	private boolean isRightLeftZigZag(WithParentBinaryNode<T> parent, WithParentBinaryNode<T> node) throws Exception {
		return parent.left().hasValue() && parent.left().value().equals(node)
				&& parent.parent().value().right().hasValue() && parent.parent().value().right().value().equals(parent);
	}

	@Override
	public void delete(T data) {
		try {
			Potential<WithParentBinaryNode<T>> deleted = deleted(new Potential<>(this.root).value(), data);
			boolean newRoot = deleted.hasValue() && deleted.value().data().compareTo(this.root.value().data()) != 0;
			if (newRoot) {
				this.root.revalue(deleted.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Potential<WithParentBinaryNode<T>> deleted(Potential<WithParentBinaryNode<T>> root, T data)
			throws Exception {
		if (root.isEmpty()) {
			return root;
		}
		WithParentBinaryNode<T> rootValue = root.value();
		int comparisonValue = rootValue.data().compareTo(data);
		if (comparisonValue > 0) {
			rootValue.changeLeft(deleted(rootValue.left(), data));
		} else if (comparisonValue < 0) {
			rootValue.changeRight(deleted(rootValue.right(), data));
		} else if (rootValue.left().isEmpty()) {
			if (rootValue.right().hasValue()) {
				rootValue.right().value().changeParent(rootValue.parent());
			}
			root = rootValue.right();
		} else if (rootValue.right().isEmpty()) {
			if (rootValue.left().hasValue()) {
				rootValue.left().value().changeParent(rootValue.parent());
			}
			root = rootValue.left();
		} else {
			T successor = min(rootValue.right().value());
			rootValue.changeRight(deleted(rootValue.right(), successor));
			rootValue.data(successor);
		}
		return root;
	}

	private void rotateLeft(WithParentBinaryNode<T> node) throws Exception {
		WithParentBinaryNode<T> rightNode = node.right().value();
		node.changeRight(rightNode.left());
		if (node.right().hasValue()) {
			node.right().value().changeParent(node);
		}
		rightNode.changeParent(node.parent());
		if (rightNode.parent().isEmpty()) {
			this.root.revalue(rightNode);
		} else if (node.parent().value().left().hasValue() && node.equals(node.parent().value().left().value())) {
			node.parent().value().changeLeft(rightNode);
		} else {
			node.parent().value().changeRight(rightNode);
		}
		rightNode.changeLeft(node);
		node.changeParent(rightNode);
	}

	private void rotateRight(WithParentBinaryNode<T> node) throws Exception {
		System.out.println("Rotating to the right = " + node);
		WithParentBinaryNode<T> leftNode = node.left().value();
		node.changeLeft(leftNode.right());
		if (node.left().hasValue()) {
			node.left().value().changeParent(node);
		}
		leftNode.changeParent(node.parent());
		if (leftNode.parent().isEmpty()) {
			this.root.revalue(leftNode);
		} else if (node.parent().value().left().hasValue() && node.equals(node.parent().value().left().value())) {
			node.parent().value().changeLeft(leftNode);
		} else {
			node.parent().value().changeRight(leftNode);
		}
		leftNode.changeRight(node);
		node.changeParent(leftNode);
	}

	@Override
	public T search(T data) throws Exception {
		WithParentBinaryNode<T> node = search(this.root.value(), data);
		splay(node);
		return node.data();
	}

	private WithParentBinaryNode<T> search(WithParentBinaryNode<T> root, T data) throws Exception {
		System.out.println("SplayTree.search()");
		int comparison = data.compareTo(root.data());
		WithParentBinaryNode<T> found;
		if (comparison == 0) {
			found = root;
		} else if (comparison > 0) {
			found = search(root.right().value(), data);
		} else {
			found = search(root.left().value(), data);
		}
		return found;
	}

	@Override
	public T min() throws Exception {
		return min(this.root.value());
	}

	private T min(WithParentBinaryNode<T> root) throws Exception {
		if (root.left().hasValue()) {
			return min(root.left().value());
		}
		return root.data();
	}

	@Override
	public T max() throws Exception {
		return max(this.root.value());
	}

	private T max(WithParentBinaryNode<T> root) throws Exception {
		if (root.right().hasValue()) {
			return max(root.right().value());
		}
		return root.data();
	}

	@Override
	public Iterable<T> items() {
		List<T> items;
		try {
			items = items(this.root.value(), new ArrayList<>());
		} catch (Exception e) {
			items = new ArrayList<>();
		}
		return items;
	}

	private List<T> items(WithParentBinaryNode<T> root, List<T> items) throws Exception {
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
			System.out.println("Root = " + this.root.value());
			traverse(this.root.value());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void traverse(WithParentBinaryNode<T> root) throws Exception {
		if (root.left().hasValue()) {
			traverse(root.left().value());
		}
		System.out.println(root);
		if (root.right().hasValue()) {
			traverse(root.right().value());
		}
	}

}
