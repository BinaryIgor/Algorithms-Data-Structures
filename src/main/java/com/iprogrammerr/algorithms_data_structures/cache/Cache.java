package com.iprogrammerr.algorithms_data_structures.cache;

import java.io.OutputStream;

public interface Cache<K, V> {

	void put(K key, V value);

	boolean has(K key);

	V cached(K key) throws Exception;

	void print(OutputStream outputStream) throws Exception;
}
