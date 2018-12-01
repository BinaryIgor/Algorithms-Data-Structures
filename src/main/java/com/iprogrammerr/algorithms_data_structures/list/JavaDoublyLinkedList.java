package com.iprogrammerr.algorithms_data_structures.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.iprogrammerr.algorithms_data_structures.exception.NullValueNotAllowedException;
import com.iprogrammerr.algorithms_data_structures.exception.WrongArgumentExeption;

public class JavaDoublyLinkedList<T> implements List<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public JavaDoublyLinkedList() {
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object object) {
		if (object == null) {
			throw new NullValueNotAllowedException();
		}
		if (head == null) {
			return false;
		}
		Iterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			T item = iterator.next();
			if (object.equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<>(head);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		Iterator<T> iterator = iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			array[i] = iterator.next();
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] array) {
		int size = size();
		if (array.length != size) {
			array = (T[]) new Object[size];
		}
		Iterator<T> iterator = (Iterator<T>) iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			array[i] = iterator.next();
		}
		return array;
	}

	@Override
	public boolean add(T element) {
		if (element == null) {
			throw new NullValueNotAllowedException();
		}
		++size;
		if (head == null) {
			head = new Node<>(element);
			tail = head;
			return true;
		}
		Node<T> newTail = new Node<>(element);
		newTail.setPrevious(tail);
		tail.setNext(newTail);
		tail = newTail;
		return true;
	}

	@Override
	public boolean remove(Object object) {
		if (object == null) {
			throw new NullValueNotAllowedException();
		}
		if (isEmpty()) {
			return false;
		}
		--size;
		if (object.equals(head.getItem())) {
			if (head.equals(tail)) {
				head = tail = null;
				return true;
			}
			Node<T> next = head.getNext();
			head = next;
			head.setPrevious(null);
			return true;
		}
		if (object.equals(tail.getItem())) {
			Node<T> previous = tail.getPrevious();
			previous.setNext(null);
			tail = previous;
			if (head.equals(tail)) {
				head.setNext(null);
			}
			return true;
		}
		Node<T> node = head.getNext();
		while (node.getNext() != null && !node.equals(tail)) {
			if (node.getItem().equals(object)) {
				Node<T> previous = node.getPrevious();
				Node<T> next = node.getNext();
				previous.setNext(next);
				next.setPrevious(previous);
				return true;
			}
		}
		++size;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		for (Object element : collection) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		return addAll(0, collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		if (collection.size() < 1) {
			return false;
		}
		T[] items = (T[]) collection.toArray();
		if (index < 0) {
			throw new WrongArgumentExeption("Index must be greater than 0");
		}
		int currentSize = size();
		if (index >= currentSize && index > 0) {
			throw new WrongArgumentExeption("Index must be smaller that current size");
		}
		size += collection.size();
		Node<T> nodesToAdd = getConnectedNodes(items);
		System.out.println("Nodes to add:");
		for (Node<T> node = nodesToAdd; node != null; node = node.getNext()) {
			System.out.println(node.getItem());
		}
		Node<T> nodesToAddTail = getTail(nodesToAdd);
		if (index == 0) {
			Node<T> oldHead = head;
			head = nodesToAdd;
			if (oldHead != null) {
				nodesToAddTail.setNext(oldHead);
			}
			if (currentSize == 0) {
				tail = nodesToAddTail;
			}
			return true;
		}
		if (index == (currentSize - 1)) {
			tail.setNext(nodesToAdd);
			tail = nodesToAddTail;
			return true;
		}
		Node<T> nodeToAddConnectedNodes = head;
		for (int i = 1; i < index; i++) {
			nodeToAddConnectedNodes = nodeToAddConnectedNodes.getNext();
		}
		System.out.println("Node to addConnectedNodes = " + nodeToAddConnectedNodes.getItem());
		Node<T> next = nodeToAddConnectedNodes.getNext();
		nodeToAddConnectedNodes.setNext(nodesToAdd);
		nodesToAdd.setPrevious(nodeToAddConnectedNodes);
		next.setPrevious(nodesToAddTail);
		nodesToAddTail.setNext(next);
		return true;
	}

	private Node<T> getTail(Node<T> node) {
		Node<T> tail = node.getNext();
		while (tail.getNext() != null) {
			tail = tail.getNext();
		}
		return tail;
	}

	private Node<T> getConnectedNodes(T[] items) {
		if (items.length == 0) {
			return null;
		}
		if (items[0] == null) {
			throw new NullValueNotAllowedException();
		}
		if (items.length == 1) {
			return new Node<>(items[0]);
		}
		if (items[1] == null) {
			throw new NullValueNotAllowedException();
		}
		if (items.length == 2) {
			Node<T> node = new Node<>(items[0]);
			Node<T> nextNode = new Node<>(items[1]);
			node.setNext(nextNode);
			nextNode.setPrevious(node);
			return node;
		}
		if (items[2] == null) {
			throw new NullValueNotAllowedException();
		}
		Node<T> node = new Node<>(items[1]);
		Node<T> nextNode = new Node<>(items[2]);
		Node<T> firstNode = new Node<>(items[0]);
		node.setPrevious(firstNode);
		node.setNext(nextNode);
		firstNode.setNext(node);
		nextNode.setPrevious(node);
		for (int i = 3; i < items.length; i++) {
			if (items[i] == null) {
				throw new NullValueNotAllowedException();
			}
			node = node.getNext();
			nextNode = new Node<>(items[i]);
			nextNode.setPrevious(node);
			node.setNext(nextNode);
		}
		return firstNode;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		boolean changed = false;
		for (Object element : collection) {
			if (element != null) {
				changed = remove(element);
			}
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			clear();
			return true;
		}
		if (isEmpty()) {
			return false;
		}
		boolean changed = false;
		Iterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			T item = iterator.next();
			if (!collection.contains(item)) {
				remove(item);
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		if (size == 0) {
			return;
		}
		Node<T> next = head.getNext();
		head.setNext(null);
		while (next != null) {
			Node<T> current = next;
			next = current.getNext();
			current.setPrevious(null);
			current.setNext(null);
		}
		size = 0;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size()) {
			throw new WrongArgumentExeption();
		}
		Node<T> node = head;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
		return node.getItem();
	}

	@Override
	public T set(int index, T element) {
		if (element == null) {
			throw new NullValueNotAllowedException();
		}
		getNode(index).setItem(element);
		return element;
	}

	@Override
	public void add(int index, T element) {
		if (element == null) {
			throw new NullValueNotAllowedException();
		}
		if (index >= (size() - 1)) {
			add(element);
			return;
		}
		++size;
		Node<T> node = getNode(index);
		Node<T> newNode = new Node<>(element);
		newNode.setNext(node);
		newNode.setPrevious(node.getPrevious());
		node.getPrevious().setNext(newNode);
		node.setPrevious(newNode);
	}

	private Node<T> getNode(int index) {
		if (index < 0 || index >= size()) {
			throw new WrongArgumentExeption();
		}
		Node<T> node = head;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
		return node;
	}

	@Override
	public T remove(int index) {
		Node<T> node = getNode(index);
		--size;
		if (index == 0) {
			node = head.getNext();
			node.setPrevious(null);
			head = node;
			if (size() <= 1) {
				tail = head;
			}
			return head != null ? head.getItem() : null;
		}
		if (index == (size() - 1)) {
			node = tail.getPrevious();
			node.setNext(null);
			tail = node;
			if (size() <= 1) {
				head = tail;
			}
			return tail != null ? tail.getItem() : null;
		}
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		return node.getItem();
	}

	@Override
	public int indexOf(Object object) {
		return indexOf(object, false);
	}

	@Override
	public int lastIndexOf(Object object) {
		return indexOf(object, true);
	}

	private int indexOf(Object object, boolean last) {
		if (object == null) {
			return -1;
		}
		Iterator<T> iterator = iterator();
		int indexOfFound = -1;
		for (int i = 0; iterator.hasNext(); i++) {
			boolean found = iterator.next().equals(object);
			if (found && last) {
				indexOfFound = i;
			} else if (found) {
				return i;
			}
		}
		return indexOfFound;
	}

	@Override
	public ListIterator<T> listIterator() {
		return new DoublyLinkedListIterator<>(0, head);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		Node<T> node = getNode(index);
		return new DoublyLinkedListIterator<>(index, node);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		boolean wrongArguments = fromIndex < 0 || fromIndex > toIndex || toIndex > size();
		if (wrongArguments) {
			throw new WrongArgumentExeption();
		}
		List<T> subList = new JavaDoublyLinkedList<>();
		if (fromIndex == toIndex) {
			return subList;
		}
		Node<T> node = getNode(fromIndex);
		while (node != null) {
			subList.add(node.getItem());
			node = node.getNext();
		}
		return subList;
	}

}
