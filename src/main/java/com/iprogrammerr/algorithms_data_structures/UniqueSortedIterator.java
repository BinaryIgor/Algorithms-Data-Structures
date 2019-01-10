package com.iprogrammerr.algorithms_data_structures;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public final class UniqueSortedIterator implements Iterator<Integer> {

	private final Iterator<Integer> base;

	public UniqueSortedIterator(int[][] arrays) {
		Set<Integer> set = new TreeSet<>();
		for (int[] array : arrays) {
			for (int item : array) {
				set.add(item);
			}
		}
		this.base = set.iterator();
	}

	@Override
	public boolean hasNext() {
		return this.base.hasNext();
	}

	@Override
	public Integer next() {
		return this.base.next();
	}
}
