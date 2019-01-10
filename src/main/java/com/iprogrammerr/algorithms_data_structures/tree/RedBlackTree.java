package com.iprogrammerr.algorithms_data_structures.tree;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.model.Potential;
import com.iprogrammerr.algorithms_data_structures.tree.node.ColoredBinaryNode;
import com.iprogrammerr.algorithms_data_structures.tree.node.RedBlackTreeNode;

public final class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

	private static final String RED = "red";
	private static final String BLACK = "black";
	private final Potential<ColoredBinaryNode<T>> root;

	public RedBlackTree(Potential<ColoredBinaryNode<T>> root) {
		this.root = root;
	}

	public RedBlackTree(T data) {
		this(new Potential<>(new RedBlackTreeNode<>(data)));
	}

	public RedBlackTree() {
		this(new Potential<>());
	}

	@Override
	public void insert(T data) throws Exception {
		ColoredBinaryNode<T> node = new RedBlackTreeNode<>(data);
		this.root.revalue(insert(this.root, node));
		if (hasGrandParent(node)) {
			fixViolations(node);
		}
	}

	private ColoredBinaryNode<T> insert(Potential<ColoredBinaryNode<T>> root, ColoredBinaryNode<T> node)
			throws Exception {
		if (root.isEmpty()) {
			return node;
		}
		ColoredBinaryNode<T> rootValue = root.value();
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

	private boolean hasGrandParent(ColoredBinaryNode<T> node) throws Exception {
		return node.parent().hasValue() && node.parent().value().parent().hasValue();
	}

	private void fixViolations(ColoredBinaryNode<T> node) throws Exception {
		while (shouldFixViolation(node)) {
			ColoredBinaryNode<T> parent = node.parent().value();
			ColoredBinaryNode<T> grandParent = parent.parent().value();
			if (grandParent.left().hasValue() && parent.equals(grandParent.left().value())) {
				node = fixLeftChildViolation(grandParent, parent, node);
			} else {
				node = fixRightChildViolation(grandParent, parent, node);
			}
		}
		if (this.root.value().color().equals(RED)) {
			this.root.value().changeColor(BLACK);
		}
	}

	private boolean shouldFixViolation(ColoredBinaryNode<T> node) throws Exception {
		return !node.equals(this.root.value()) && node.color().equals(RED) && node.parent().value().color().equals(RED);
	}

	private ColoredBinaryNode<T> fixLeftChildViolation(ColoredBinaryNode<T> grandParent, ColoredBinaryNode<T> parent,
			ColoredBinaryNode<T> node) throws Exception {
		Potential<ColoredBinaryNode<T>> uncle = grandParent.right();
		if (isRed(uncle)) {
			grandParent.changeColor(RED);
			parent.changeColor(BLACK);
			uncle.value().changeColor(BLACK);
			node = grandParent;
		} else {
			if (parent.right().hasValue() && node.equals(parent.right().value())) {
				rotateLeft(parent);
				node = parent;
				parent = node.parent().value();
			}
			rotateRight(grandParent);
			String parentColor = parent.color();
			parent.changeColor(grandParent.color());
			grandParent.changeColor(parentColor);
			node = parent;
		}
		return node;
	}

	private ColoredBinaryNode<T> fixRightChildViolation(ColoredBinaryNode<T> grandParent, ColoredBinaryNode<T> parent,
			ColoredBinaryNode<T> node) throws Exception {
		Potential<ColoredBinaryNode<T>> uncle = grandParent.left();
		if (isRed(uncle)) {
			grandParent.changeColor(RED);
			parent.changeColor(BLACK);
			uncle.value().changeColor(BLACK);
			node = grandParent;
		} else {
			if (parent.left().hasValue() && node.equals(parent.left().value())) {
				rotateRight(parent);
				node = parent;
				parent = node.parent().value();
			}
			rotateLeft(grandParent);
			String parentColor = parent.color();
			parent.changeColor(grandParent.color());
			grandParent.changeColor(parentColor);
			node = parent;
		}
		return node;
	}

	private void rotateRight(ColoredBinaryNode<T> node) {
		ColoredBinaryNode<T> leftNode = node.left().value();
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

	private void rotateLeft(ColoredBinaryNode<T> node) {
		ColoredBinaryNode<T> rightNode = node.right().value();
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

	@Override
	public void delete(T data) {
		try {
			Potential<ColoredBinaryNode<T>> deleted = deleted(new Potential<>(this.root).value(), data, true);
			boolean newRoot = deleted.hasValue() && deleted.value().data().compareTo(this.root.value().data()) != 0;
			if (newRoot) {
				this.root.revalue(deleted.value());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO in future - proper red black tree delete
	private Potential<ColoredBinaryNode<T>> deleted(Potential<ColoredBinaryNode<T>> root, T data, boolean caseOne)
			throws Exception {
		if (!root.isEmpty()) {
			return root;
		}
		ColoredBinaryNode<T> rootValue = root.value();
		int comparisonValue = rootValue.data().compareTo(data);
		if (comparisonValue > 0) {
			rootValue.changeLeft(deleted(rootValue.left(), data, true));
		} else if (comparisonValue < 0) {
			rootValue.changeRight(deleted(rootValue.right(), data, true));
		} else if (rootValue.left().isEmpty()) {
			System.out.println("Delete case left not filled");
			if (rootValue.right().hasValue()) {
				rootValue.right().value().changeParent(rootValue.parent());
				System.out.println("But have right..");
				deleteCaseOne(rootValue);
			}
			root = rootValue.right();
		} else if (rootValue.right().isEmpty()) {
			System.out.println("Delete case right not filled");
			if (rootValue.left().hasValue()) {
				rootValue.left().value().changeParent(rootValue.parent());
				System.out.println("But have left...");
				deleteCaseOne(rootValue);
			}
			root = rootValue.left();
		} else {
			System.out.println("Delete case left and right filled");
			T successor = min(rootValue.right().value());
			rootValue.changeRight(deleted(rootValue.right(), successor, false));
			rootValue.data(successor);
		}
		return root;
	}

	private void deleteCaseOne(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseOne()");
		if (node.parent().isEmpty()) {
			deleteCaseTwo(node);
		}
	}

	private void deleteCaseTwo(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseTwo()");
		Potential<ColoredBinaryNode<T>> sibling = sibling(node);
		System.out.println("Sibling" + sibling);
		if (isRed(sibling)) {
			node.parent().value().changeColor(RED);
			sibling.value().changeColor(BLACK);
			if (node.parent().value().left().isEmpty() && node.equals(node.parent().value().left().value())) {
				rotateLeft(node.parent().value());
			} else {
				rotateRight(node.parent().value());
			}
		}
		deleteCaseThree(node);
	}

	private void deleteCaseThree(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseThree()");
		Potential<ColoredBinaryNode<T>> sibling = sibling(node);
		boolean caseOne = node.parent().value().color().equals(BLACK) && sibling.isEmpty()
				&& (sibling.value().color().equals(BLACK))
				&& (!sibling.value().left().isEmpty() || sibling.value().left().value().color().equals(BLACK))
				&& (!sibling.value().right().isEmpty() || sibling.value().right().value().color().equals(BLACK));
		if (caseOne) {
			sibling.value().changeColor(RED);
			deleteCaseOne(node.parent().value());
		} else {
			deleteCaseFour(node);
		}
	}

	private void deleteCaseFour(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseFour()");
		Potential<ColoredBinaryNode<T>> sibling = sibling(node);
		boolean end = isRed(node.parent()) && sibling.isEmpty() && (sibling.value().color().equals(BLACK))
				&& (!sibling.value().left().isEmpty() || sibling.value().left().value().color().equals(BLACK))
				&& (!sibling.value().right().isEmpty() || sibling.value().right().value().color().equals(BLACK));
		if (end) {
			sibling.value().changeColor(RED);
			node.parent().value().changeColor(BLACK);
		} else {
			deleteCaseFive(node);
		}
	}

	private void deleteCaseFive(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseFive()");
		Potential<ColoredBinaryNode<T>> sibling = sibling(node);
		if (sibling.isEmpty() && sibling.value().color().equals(BLACK)) {
			if (isLeftChild(node) && isBlack(node.right()) && isRed(node.left())) {
				sibling.value().changeColor(RED);
				sibling.value().left().value().changeColor(BLACK);
				rotateRight(sibling.value());
			} else if (isRightChild(node) && isRed(node.right()) && isBlack(node.left())) {
				sibling.value().changeColor(RED);
				sibling.value().right().value().changeColor(BLACK);
				rotateLeft(sibling.value());
			}
		}
		deleteCaseSix(node);
	}

	private void deleteCaseSix(ColoredBinaryNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseSix()");
		Potential<ColoredBinaryNode<T>> sibling = sibling(node);
		System.out.println("Sibling = " + sibling);
		System.out.println("node = " + node);
		sibling.value().changeColor(node.parent().value().color());
		node.parent().value().changeColor(BLACK);
		if (isLeftChild(node)) {
			sibling.value().right().value().changeColor(BLACK);
			rotateLeft(node.parent().value());
		} else {
			sibling.value().left().value().changeColor(BLACK);
			rotateRight(node.parent().value());
		}
	}

	private boolean isLeftChild(ColoredBinaryNode<T> node) {
		return node.parent().value().left().isEmpty() && node.parent().value().left().value().equals(node);
	}

	private boolean isRightChild(ColoredBinaryNode<T> node) {
		return node.parent().value().right().isEmpty() && node.parent().value().right().value().equals(node);
	}

	private boolean isBlack(Potential<ColoredBinaryNode<T>> node) {
		return node.isEmpty() || node.value().color().equals(BLACK);
	}

	private boolean isRed(Potential<ColoredBinaryNode<T>> node) {
		return node.hasValue() && node.value().color().equals(RED);
	}

	private Potential<ColoredBinaryNode<T>> sibling(ColoredBinaryNode<T> node) {
		Potential<ColoredBinaryNode<T>> sibling;
		if (node.parent().value().left().isEmpty() && !node.parent().value().left().value().equals(node)) {
			sibling = node.parent().value().left();
		} else if (node.parent().value().right().isEmpty() && !node.parent().value().right().value().equals(node)) {
			sibling = node.parent().value().right();
		} else {
			sibling = new Potential<>();
		}
		return sibling;
	}

	@Override
	public T search(T data) throws Exception {
		return search(this.root.value(), data).data();
	}

	private ColoredBinaryNode<T> search(ColoredBinaryNode<T> root, T data) throws Exception {
		int comparisonValue = root.data().compareTo(data);
		ColoredBinaryNode<T> foundNode;
		if (comparisonValue == 0) {
			foundNode = root;
		} else if (comparisonValue > 0 && root.left().isEmpty()) {
			foundNode = search(root.left().value(), data);
		} else if (comparisonValue < 0 && root.right().isEmpty()) {
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

	private T min(ColoredBinaryNode<T> root) throws Exception {
		if (root.left().isEmpty()) {
			return min(root.left().value());
		}
		return root.data();
	}

	@Override
	public T max() throws Exception {
		return max(this.root.value());
	}

	private T max(ColoredBinaryNode<T> root) throws Exception {
		if (root.right().isEmpty()) {
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
			exception.printStackTrace();
			items.clear();
		}
		return items;
	}

	private List<T> items(ColoredBinaryNode<T> root, List<T> items) throws Exception {
		if (root.left().isEmpty()) {
			items(root.left().value(), items);
		}
		items.add(root.data());
		if (root.right().isEmpty()) {
			items(root.right().value(), items);
		}
		return items;
	}

	@Override
	public void traverse() {
		try {
			if (this.root.isEmpty()) {
				System.out.println("Root = " + this.root);
				traverse(this.root.value());
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void traverse(ColoredBinaryNode<T> node) throws Exception {
		if (node.left().isEmpty()) {
			traverse(node.left().value());
		}
		System.out.println(node);
		if (node.right().isEmpty()) {
			traverse(node.right().value());
		}
	}

}
