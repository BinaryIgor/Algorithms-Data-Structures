package com.iprogrammerr.algorithms_data_structures.tree.fast;

import com.iprogrammerr.algorithms_data_structures.tree.Tree;

public final class RedBlackTree<T extends Comparable<T>> implements Tree<T> {

	private RedBlackNode<T> root;

	private RedBlackTree(RedBlackNode<T> root) {
		this.root = root;
	}

	public RedBlackTree(T data) {
		this(new RedBlackNode<>(data, false));
	}

	@Override
	public void insert(T data) throws Exception {
		RedBlackNode<T> node = new RedBlackNode<>(data);
		this.root = insert(this.root, node);
		if (hasGrandParent(node)) {
			fixViolations(node);
		}
	}

	private RedBlackNode<T> insert(RedBlackNode<T> root, RedBlackNode<T> node) throws Exception {
		if (root == null) {
			return node;
		}
		int comparison = root.data().compareTo(node.data());
		if (comparison == 0) {
			throw new Exception("Duplicated values are not allowed");
		}
		if (comparison > 0) {
			root.setLeft(insert(root.left(), node));
			root.left().setParent(root);
		} else {
			root.setRight(insert(root.right(), node));
			root.right().setParent(root);
		}
		return root;
	}

	private boolean hasGrandParent(RedBlackNode<T> node) throws Exception {
		return node.parent() != null && node.parent().parent() != null;
	}

	private void fixViolations(RedBlackNode<T> node) {
		while (shouldFixViolation(node)) {
			RedBlackNode<T> parent = node.parent();
			RedBlackNode<T> grandParent = parent.parent();
			if (grandParent.left() != null && parent.equals(grandParent.left())) {
				node = fixedLeftViolation(grandParent, parent, node);
			} else {
				node = fixedRightViolation(grandParent, parent, node);
			}
		}
		if (this.root.isRed()) {
			this.root.setRed(false);
		}
	}

	private boolean shouldFixViolation(RedBlackNode<T> node) {
		return !this.root.equals(node) && node.isRed() && node.parent().isRed();
	}

	private RedBlackNode<T> fixedLeftViolation(RedBlackNode<T> grandParent, RedBlackNode<T> parent,
			RedBlackNode<T> node) {
		RedBlackNode<T> uncle = grandParent.right();
		if (uncle != null && uncle.isRed()) {
			recolor(grandParent);
			node = grandParent;
		} else {
			if (parent.right() != null && node.equals(parent.right())) {
				rotateLeft(parent);
				node = parent;
				parent = node.parent();
			}
			rotateRight(grandParent);
			swapColors(grandParent, parent);
			node = parent;
		}
		return node;
	}

	private void recolor(RedBlackNode<T> grandParent) {
		grandParent.setRed(true);
		grandParent.left().setRed(false);
		grandParent.right().setRed(false);
	}

	private void swapColors(RedBlackNode<T> first, RedBlackNode<T> second) {
		boolean red = first.isRed();
		first.setRed(second.isRed());
		second.setRed(red);
	}

	private RedBlackNode<T> fixedRightViolation(RedBlackNode<T> grandParent, RedBlackNode<T> parent,
			RedBlackNode<T> node) {
		RedBlackNode<T> uncle = grandParent.left();
		if (uncle != null && uncle.isRed()) {
			recolor(grandParent);
			node = grandParent;
		} else {
			if (parent.left() != null && node.equals(parent.left())) {
				rotateRight(parent);
				node = parent;
				parent = node.parent();
			}
			rotateLeft(grandParent);
			swapColors(grandParent, parent);
			node = parent;
		}
		return node;
	}

	private void rotateRight(RedBlackNode<T> node) {
		RedBlackNode<T> left = node.left();
		node.setLeft(left.right());
		if (node.left() != null) {
			node.left().setParent(node);
		}
		left.setParent(node.parent());
		if (left.parent() == null) {
			this.root = left;
		} else if (node.equals(node.parent().left())) {
			node.parent().setLeft(left);
		} else {
			node.parent().setRight(left);
		}
		left.setRight(node);
		node.setParent(left);
	}

	private void rotateLeft(RedBlackNode<T> node) {
		RedBlackNode<T> right = node.right();
		node.setRight(right.left());
		if (node.right() != null) {
			node.right().setParent(node);
		}
		right.setParent(node.parent());
		if (right.parent() == null) {
			this.root = right;
		} else if (node.equals(node.parent().left())) {
			node.parent().setLeft(right);
		} else {
			node.parent().setRight(right);
		}
		right.setLeft(node);
		node.setParent(right);
	}

	@Override
	public void delete(T data) {
		this.root = deleted(this.root, data);

	}

	private RedBlackNode<T> deleted(RedBlackNode<T> root, T data) {
		if (root == null) {
			return root;
		}
		int cmp = root.data().compareTo(data);
		if (cmp > 0) {
			root.setLeft(deleted(root.left(), data));
		} else if (cmp < 0) {
			root.setRight(deleted(root.right(), data));
		} else if (root.left() == null) {
			if (root.right() != null) {
				root.right().setParent(root.parent());
				deleteCaseOne(root);
			}
			root = root.right();
		} else if (root.right() == null) {
			if (root.left() != null) {
				root.left().setParent(root.parent());
				deleteCaseOne(root);
			}
			deleteCaseOne(root);
			root = root.left();
		} else {
			T successor = min(root.right());
			root.setRight(deleted(root.right(), successor));
			root.setData(successor);
		}
		return root;
	}

	private void deleteCaseOne(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseOne()");
		System.out.println("left = " + node.left());
		System.out.println("right = " + node.right());
		if (node.parent() != null) {
			if (!node.isRed() && (isRed(node.left()) || isRed(node.right()))) {
				if (isRed(node.left())) {
					node.left().setRed(false);
				} else {
					node.right().setRed(true);
				}
			} else {
				deleteCaseTwo(node);
			}
		}
	}

	private void deleteCaseTwo(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseTwo()");
		RedBlackNode<T> sibling = sibling(node);
		if (sibling != null && sibling.isRed()) {
			node.parent().setRed(true);
			sibling.setRed(false);
			if (node.equals(node.parent().left())) {
				rotateLeft(node.parent());
			} else {
				rotateRight(node.parent());
			}
		}
		deleteCaseThree(node);
	}

	private void deleteCaseThree(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseThree()");
		RedBlackNode<T> sibling = sibling(node);
		boolean caseOne = !node.parent().isRed() && isNotNullBlack(sibling)
				&& isNullOrBlack(sibling.left()) && isNullOrBlack(sibling.right());
		if (caseOne) {
			sibling.setRed(true);
			deleteCaseOne(node.parent());
		} else {
			deleteCaseFour(node);
		}
	}

	private void deleteCaseFour(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseFour()");
		RedBlackNode<T> sibling = sibling(node);
		boolean end = isRed(node.parent()) && isNotNullBlack(sibling)
				&& isNullOrBlack(sibling.left()) && isNullOrBlack(sibling.right());
		if (end) {
			sibling.setRed(true);
			node.parent().setRed(false);
		} else {
			deleteCaseFive(node);
		}
	}

	private void deleteCaseFive(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseFive()");
		RedBlackNode<T> sibling = sibling(node);
		if (isNotNullBlack(sibling)) {
			if (isLeftChild(node) && isNullOrBlack(node.right()) && isRed(node.left())) {
				sibling.setRed(true);
				sibling.left().setRed(false);
				rotateRight(sibling);
			} else if (isRightChild(node) && isNullOrBlack(node.left()) && isRed(node.right())) {
				sibling.setRed(true);
				sibling.right().setRed(false);
				rotateLeft(sibling);
			}
		}
		deleteCaseSix(node);
	}

	private void deleteCaseSix(RedBlackNode<T> node) {
		System.out.println("RedBlackTree.deleteCaseSix()");
		System.out.println("node " + node);
		System.out.println("nodeleft = " + node.left());
		System.out.println("nodeRight = " + node.right());
		RedBlackNode<T> sibling = sibling(node);
		sibling.setRed(node.parent().isRed());
		node.parent().setRed(false);
		if (isLeftChild(node)) {
			sibling.right().setRed(false);
			rotateLeft(node.parent());
		} else {
			sibling.left().setRed(false);
			rotateRight(node.parent());
		}
	}

	private boolean isLeftChild(RedBlackNode<T> node) {
		return node.equals(node.parent().left());
	}

	private boolean isRightChild(RedBlackNode<T> node) {
		return node.equals(node.parent().right());
	}

	private boolean isRed(RedBlackNode<T> node) {
		return node != null && node.isRed();
	}

	private boolean isNotNullBlack(RedBlackNode<T> node) {
		return node != null && !node.isRed();
	}

	private boolean isNullOrBlack(RedBlackNode<T> node) {
		return node == null || !node.isRed();
	}

	private RedBlackNode<T> sibling(RedBlackNode<T> node) {
		RedBlackNode<T> sibling;
		if (node.equals(node.parent().left())) {
			sibling = node.parent().right();
		} else {
			sibling = node.parent().left();
		}
		return sibling;
	}

	@Override
	public T search(T data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T min() throws Exception {
		return min(this.root);
	}

	private T min(RedBlackNode<T> root) {
		if (root.left() != null) {
			return min(root.left());
		}
		return root.data();
	}

	@Override
	public T max() throws Exception {
		return max(this.root);
	}

	private T max(RedBlackNode<T> root) {
		if (root.right() != null) {
			return min(root.right());
		}
		return root.data();
	}

	@Override
	public Iterable<T> items() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void traverse() {
		System.out.println("Root = " + this.root);
		traverse(this.root);
	}

	private void traverse(RedBlackNode<T> node) {
		if (node.left() != null) {
			traverse(node.left());
		}
		System.out.println(node);
		if (node.right() != null) {
			traverse(node.right());
		}
	}

}
