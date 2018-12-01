package com.iprogrammerr.algorithms_data_structures.hashtable;

public class HashTableEntry<K, V> implements KeyValue<K, V> {

    private final K key;
    private final V value;

    public HashTableEntry(K key, V value) {
	this.key = key;
	this.value = value;
    }

    @Override
    public K key() {
	return key;
    }

    @Override
    public V value() {
	return value;
    }

}
