package com.iprogrammerr.algorithms_data_structures.cache;

public class CacheNode<K, V> {

	private final K key;
	private V value;
	private CacheNode<K, V> left;
	private CacheNode<K, V> right;

	public CacheNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K key() {
		return this.key;
	}

	public V value() {
		return this.value;
	}

	public void revalue(V value) {
		this.value = value;
	}

	public CacheNode<K, V> left() {
		return this.left;
	}

	public void setLeft(CacheNode<K, V> left) {
		this.left = left;
	}

	public CacheNode<K, V> right() {
		return this.right;
	}

	public void setRight(CacheNode<K, V> right) {
		this.right = right;
	}
}
