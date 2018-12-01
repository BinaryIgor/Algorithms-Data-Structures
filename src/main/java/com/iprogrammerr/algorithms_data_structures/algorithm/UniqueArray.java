package com.iprogrammerr.algorithms_data_structures.algorithm;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public final class UniqueArray<T> implements Algorithm<T[]> {

	private final Class<T> clazz;
	private final T[] source;
	private final Map<T, Integer> uniques;

	private UniqueArray(Class<T> clazz, T[] source, Map<T, Integer> uniques) {
		this.clazz = clazz;
		this.source = source;
		this.uniques = uniques;
	}

	public UniqueArray(Class<T> clazz, T[] source) {
		this(clazz, source, new HashMap<>(source.length));
	}

	@Override
	public T[] solution() {
		int size = 0;
		for (int i = 0; i < source.length; ++i) {
			if (!this.uniques.containsKey(this.source[i])) {
				this.uniques.put(this.source[i], size++);
			}
		}
		T[] uniques = (T[]) Array.newInstance(this.clazz, size);
		for (Map.Entry<T, Integer> entry : this.uniques.entrySet()) {
			uniques[entry.getValue()] = entry.getKey();
		}
		return uniques;
	}
}
