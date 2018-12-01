package com.iprogrammerr.algorithms_data_structures.cache;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public final class LruCache<K, V> implements Cache<K, V> {

	private final int capacity;
	private int size;
	private final Map<K, CacheNode<K, V>> map;
	private CacheNode<K, V> head;
	private CacheNode<K, V> tail;

	private LruCache(int capacity, Map<K, CacheNode<K, V>> map) {
		this.capacity = capacity;
		this.map = map;
	}

	public LruCache(int capacity) {
		this(capacity, new HashMap<>());
	}

	public LruCache() {
		this(5);
	}

	@Override
	public void put(K key, V value) {
		if (this.map.containsKey(key)) {
			CacheNode<K, V> node = this.map.get(key);
			node.revalue(value);
			update(node);
		} else {
			if (this.size >= this.capacity) {
				removeTail();
			} else {
				++this.size;
			}
			add(new CacheNode<>(key, value));
		}

	}

	private void update(CacheNode<K, V> node) {
		CacheNode<K, V> left = node.left();
		CacheNode<K, V> right = node.right();
		if (left != null) {
			left.setRight(right);
		}
		setHead(node);
	}

	private void setHead(CacheNode<K, V> node) {
		node.setRight(this.head);
		this.head.setLeft(node);
		this.head = node;
	}

	private void add(CacheNode<K, V> node) {
		if (this.head == null) {
			this.head = this.tail = node;
		} else {
			setHead(node);
		}
		this.map.put(node.key(), node);
	}

	private void removeTail() {
		this.map.remove(this.tail.key());
		this.tail = this.tail.left();
		this.tail.setRight(null);
	}

	@Override
	public boolean has(K key) {
		return this.map.containsKey(key);
	}

	@Override
	public V cached(K key) throws Exception {
		CacheNode<K, V> node = this.map.get(key);
		if (node == null) {
			throw new Exception(
					String.format("There is no value associateed with %s key in the cache", key));
		}
		return node.value();
	}

	@Override
	public void print(OutputStream outputStream) throws Exception {
		StringBuilder builder = new StringBuilder();
		CacheNode<K, V> node = this.head;
		if (node != null) {
			builder.append(node.value().toString());
			node = node.right();
		}
		while (node != null) {
			builder.append(", ").append(node.value().toString());
			node = node.right();
		}
		outputStream.write(builder.toString().getBytes());
	}

}
