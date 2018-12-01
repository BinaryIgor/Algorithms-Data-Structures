package com.iprogrammerr.algorithms_data_structures.algorithm;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public final class ArrayDuplicates<T> implements Algorithm<T[]> {

	private static final int UNIQUE_PLACEHOLDER = 1;
	private final Class<T> clazz;
	private final T[] source;
	private final Map<T, Integer> uniques;
	private final Map<T, Integer> duplicates;

	private ArrayDuplicates(Class<T> clazz, T[] source, Map<T, Integer> uniques,
			Map<T, Integer> duplicates) {
		this.clazz = clazz;
		this.source = source;
		this.uniques = uniques;
		this.duplicates = duplicates;
	}

	public ArrayDuplicates(Class<T> clazz, T[] source) {
		this(clazz, source, new HashMap<>(source.length), new HashMap<>(source.length));
	}

	@Override
	public T[] solution() {
		int size = 0;
		for (int i = 0; i < this.source.length; ++i) {
			if (!this.uniques.containsKey(this.source[i])) {
				this.uniques.put(this.source[i], UNIQUE_PLACEHOLDER);
			} else if (!this.duplicates.containsKey(this.source[i])) {
				this.duplicates.put(this.source[i], size++);
			}
		}
		T[] duplicates = (T[]) Array.newInstance(this.clazz, size);
		for (Map.Entry<T, Integer> entry : this.duplicates.entrySet()) {
			duplicates[entry.getValue()] = entry.getKey();
		}
		return duplicates;
	}
}
