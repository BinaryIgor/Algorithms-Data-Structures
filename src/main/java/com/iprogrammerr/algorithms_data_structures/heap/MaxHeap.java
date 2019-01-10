package com.iprogrammerr.algorithms_data_structures.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

import com.iprogrammerr.algorithms_data_structures.search.ReversedBinarySearch;
import com.iprogrammerr.algorithms_data_structures.search.Search;

public final class MaxHeap<T extends Comparable<T>> implements Heap<T> {

	private T[] heap;
	private int size;
	private final Search<T> search;

	public MaxHeap(int capacity, Class<T> clazz, Search<T> search) {
		this.heap = (T[]) Array.newInstance(clazz, capacity);
		this.search = search;
	}

	public MaxHeap(Class<T> clazz) {
		this(25, clazz, new ReversedBinarySearch<>());
	}

	@Override
	public void insert(T value) throws Exception {
		if (isFull()) {
			throw new Exception("Heap is full");
		}
		heap[size] = value;
		fixHeapAbove(size);
		++size;
	}

	private void fixHeapAbove(int index) {
		T newValue = heap[index];
		while (index > 0 && newValue != null && newValue.compareTo(heap[parent(index)]) > 0) {
			heap[index] = heap[parent(index)];
			index = parent(index);
		}
		heap[index] = newValue;
	}

	private int leftChild(int parent) {
		return 2 * parent + 1;
	}

	private int rightChild(int parent) {
		return 2 * parent + 2;
	}

	@Override
	public boolean isFull() {
		return size == heap.length;
	}

	private int parent(int child) {
		return (child - 1) / 2;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void delete(T value) throws Exception {
		int index = search.index(heap, size, value);
		if (index < 0) {
			throw new Exception(String.format("%s is not present in a heap", value));
		}
		delete(index);
	}

	private void delete(int index) throws Exception {
		if (isEmpty()) {
			throw new Exception("Can not delete from an empty heap");
		}
		int parent = parent(index);
		heap[index] = heap[size - 1];
		if (index == 0 || heap[index].compareTo(heap[parent]) < 0) {
			fixHeapBelow(index, size - 1);
		} else {
			fixHeapAbove(index);
		}
		--size;
	}

	private void fixHeapBelow(int index, int size) {
		while (index <= size) {
			int largest = index;
			int leftChild = leftChild(index);
			int rightChild = rightChild(index);
			if (leftChild < size && heap[largest].compareTo(heap[leftChild]) < 0) {
				largest = leftChild;
			}
			if (rightChild < size && heap[largest].compareTo(heap[rightChild]) < 0) {
				largest = rightChild;
			}
			if (largest == index) {
				break;
			}
			T tmp = heap[index];
			heap[index] = heap[largest];
			heap[largest] = tmp;
			index = largest;
		}
	}

	@Override
	public void print() {
		for (int i = 0; i < size; ++i) {
			System.out.println(heap[i]);
		}
	}

	@Override
	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("Heap is empty");
		}
		return heap[0];
	}

	@Override
	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("Heap is empty");
		}
		T head = heap[0];
		delete(0);
		return head;
	}

	@Override
	public T[] sorted() {
		int lastHeapIndex = size - 1;
		T[] sorted = this.heap;
		T[] origin = Arrays.copyOf(this.heap, this.heap.length);
		for (int i = 0; i < lastHeapIndex; ++i) {
			T tmp = heap[0];
			heap[0] = heap[lastHeapIndex - i];
			heap[lastHeapIndex - i] = tmp;
			fixHeapBelow(0, lastHeapIndex - i);
		}

		this.heap = origin;
		return Arrays.copyOf(sorted, size);
	}

	public void reverse() {
		for (int i = (size - 2) / 2; i >= 0; i--) {
			fixBellow(i);
		}
	}

	private void fixBellow(int index) {
		int smallest = index;
		int left = 2 * smallest + 1;
		int right = 2 * smallest + 2;
		if (left < this.size && this.heap[smallest].compareTo(this.heap[left]) > 0) {
			smallest = left;
		}
		if (right < this.size && this.heap[smallest].compareTo(this.heap[right]) > 0) {
			smallest = right;
		}
		if (smallest != index) {
			T tmp = this.heap[index];
			this.heap[index] = this.heap[smallest];
			this.heap[smallest] = tmp;
			fixBellow(smallest);
		}

	}
}
