package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.model.Potential;
import com.iprogrammerr.algorithms_data_structures.tree.node.BinaryNode;
import com.iprogrammerr.algorithms_data_structures.tree.node.BinaryTreeNode;

public final class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private final Potential<BinaryNode<T>> root;

	public BinarySearchTree(Potential<BinaryNode<T>> root) {
		this.root = root;
	}

	public BinarySearchTree(T data) {
		this(new Potential<>(new BinaryTreeNode<>(data)));
	}

	public BinarySearchTree() {
		this(new Potential<>());
	}

	@Override
	public void insert(T data) throws Exception {
		this.root.revalue(insert(this.root, data));
	}

	private BinaryNode<T> insert(Potential<BinaryNode<T>> root, T data) throws Exception {
		if (root.isEmpty()) {
			return new BinaryTreeNode<>(data);
		}
		BinaryNode<T> node = root.value();
		int comparisonValue = data.compareTo(node.data());
		if (comparisonValue == 0) {
			throw new Exception("Duplicated values are not allowed");
		}
		if (comparisonValue < 0) {
			node.changeLeft(insert(node.left(), data));
		} else {
			node.changeRight(insert(node.right(), data));
		}
		return node;
	}

	@Override
	public T search(T data) throws Exception {
		return search(this.root.value(), data).data();
	}

	private BinaryNode<T> search(BinaryNode<T> root, T data) throws Exception {
		int comparisonValue = root.data().compareTo(data);
		BinaryNode<T> foundNode;
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
	public void delete(T data) {
		try {
			Potential<BinaryNode<T>> deleted = delete(this.root, data);
			boolean newRoot = deleted.hasValue() && deleted.value().data().compareTo(this.root.value().data()) != 0;
			if (newRoot) {
				this.root.revalue(deleted.value());
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Potential<BinaryNode<T>> delete(Potential<BinaryNode<T>> root, T data) throws Exception {
		if (root.isEmpty()) {
			return root;
		}
		BinaryNode<T> rootValue = root.value();
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
			rootValue.data(maxFromLeftSubTree);
		}
		return root;
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

	private List<T> items(BinaryNode<T> root, List<T> items) throws Exception {
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
	public T min() throws Exception {
		return min(this.root.value());
	}

	private T min(BinaryNode<T> root) throws Exception {
		if (root.left().hasValue()) {
			return min(root.left().value());
		}
		return root.data();
	}

	@Override
	public T max() throws Exception {
		return max(this.root.value());
	}

	private T max(BinaryNode<T> root) throws Exception {
		if (root.right().hasValue()) {
			return max(root.right().value());
		}
		return root.data();
	}

	@Override
	public void traverse() {
		try {
			if (this.root.hasValue()) {
				traverse(this.root.value());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void traverse(BinaryNode<T> node) throws Exception {
		if (node.left().hasValue()) {
			traverse(node.left().value());
		}
		System.out.println(node.data());
		if (node.right().hasValue()) {
			traverse(node.right().value());
		}
	}
}
