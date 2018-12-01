package com.iprogrammerr.algorithms_data_structures.data;

public class CacheItem<T> {

	private final long timestamp;
	private final T value;

	public CacheItem(long timestamp, T value) {
		this.timestamp = timestamp;
		this.value = value;
	}

	public long timestamp() {
		return this.timestamp;
	}

	public T value() {
		return this.value;
	}
}
