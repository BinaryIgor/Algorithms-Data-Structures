package com.iprogrammerr.algorithms_data_structures.hashtable;

public interface HashTable<K, V> {

	HashTable<K, V> save(K key, V value);

	V value(K key);

	void remove(K key);

	int size();

	boolean isEmpty();

	Iterable<HashTableEntry<K, V>> entries();

	Iterable<K> keys();

	Iterable<V> values();
}
