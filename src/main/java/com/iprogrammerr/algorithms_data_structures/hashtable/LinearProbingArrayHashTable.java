package com.iprogrammerr.algorithms_data_structures.hashtable;

import java.lang.reflect.Array;

import com.iprogrammerr.algorithms_data_structures.model.ArrayIterator;

public final class LinearProbingArrayHashTable<K, V> implements HashTable<K, V> {

	private static final float LOAD_FACTOR = 0.75f;
	private static final float SHRINK_THRESHOLD = 0.33f;
	private HashTableEntry<K, V>[] entries;
	private int size;

	public LinearProbingArrayHashTable(int initialCapacity) {
		this.entries = (HashTableEntry<K, V>[]) Array.newInstance(HashTableEntry.class,
			(int) (initialCapacity / LOAD_FACTOR));
	}

	public LinearProbingArrayHashTable() {
		this(50);
	}

	@Override
	public HashTable<K, V> save(K key, V value) {
		int hashedKey = hashedKey(key);
		HashTableEntry<K, V> previous = this.entries[hashedKey];
		if (previous == null || previous.key().equals(key)) {
			this.entries[hashedKey] = new HashTableEntry<>(key, value);
		} else {
			this.entries[linearProbedHashedKey(hashedKey, key)] = new HashTableEntry<>(key, value);
		}
		if (previous == null || !previous.key().equals(key)) {
			++this.size;
		}
		if (this.size > (this.entries.length * LOAD_FACTOR)) {
			resize();
		}
		return this;
	}

	private int linearProbedHashedKey(int hashedKey, K key) {
		int previousHashedKey = hashedKey;
		do {
			hashedKey = nextLinearProbedHashedKey(hashedKey);
		} while (this.entries[hashedKey] != null && hashedKey != previousHashedKey);
		return hashedKey;
	}

	private int nextLinearProbedHashedKey(int hashedKey) {
		if (hashedKey == (this.entries.length - 1)) {
			hashedKey = 0;
		} else {
			hashedKey = (hashedKey + 1) % this.entries.length;
		}
		return hashedKey;
	}

	@Override
	public V value(K key) {
		int hashedKey = hashedKey(key);
		HashTableEntry<K, V> entry = this.entries[hashedKey];
		if (entry == null) {
			throw new RuntimeException(String.format("There is no value associated with a %s key", key));
		}
		V value;
		if (entry.key().equals(key)) {
			value = entry.value();
		} else {
			do {
				hashedKey = nextLinearProbedHashedKey(hashedKey);
				entry = this.entries[hashedKey];
			} while (entry == null || !entry.key().equals(key));
			value = entry.value();
		}
		return value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	private int hashedKey(K key) {
		return Math.abs(key.hashCode()) % this.entries.length;
	}

	@Override
	public void remove(K key) {
		int hashedKey = hashedKey(key);
		HashTableEntry<K, V> entry = this.entries[hashedKey];
		if (entry == null) {
			return;
		}
		if (!entry.key().equals(key)) {
			do {
				hashedKey = nextLinearProbedHashedKey(hashedKey);
				entry = this.entries[hashedKey];
			} while (entry == null || !entry.key().equals(key));
		}
		this.entries[hashedKey] = null;
		HashTableEntry<K, V>[] previous = this.entries;
		if ((this.entries.length * SHRINK_THRESHOLD) > this.size) {
			this.entries = (HashTableEntry<K, V>[]) Array.newInstance(HashTableEntry.class, this.entries.length / 2);
		} else {
			this.entries = (HashTableEntry<K, V>[]) Array.newInstance(HashTableEntry.class, this.entries.length);
		}
		this.size = 0;
		copyFrom(this.entries);
	}

	private void resize() {
		HashTableEntry<K, V>[] previous = this.entries;
		this.entries = (HashTableEntry<K, V>[]) Array.newInstance(HashTableEntry.class, 2 * previous.length);
		this.size = 0;
		copyFrom(previous);
	}

	private void copyFrom(HashTableEntry<K, V>[] source) {
		for (int i = 0; i < source.length; i++) {
			HashTableEntry<K, V> entry = source[i];
			if (entry != null) {
				save(entry.key(), entry.value());
			}
		}
	}

	@Override
	public Iterable<HashTableEntry<K, V>> entries() {
		return () -> new ArrayIterator<>(valuedEntries());
	}

	private HashTableEntry<K, V>[] valuedEntries() {
		HashTableEntry<K, V>[] valuedEntries = (HashTableEntry<K, V>[]) Array.newInstance(HashTableEntry.class,
			this.size);
		for (int i = 0, j = 0; i < this.entries.length && j < valuedEntries.length; i++) {
			HashTableEntry<K, V> entry = this.entries[i];
			if (entry != null) {
				valuedEntries[j++] = entry;
			}
		}
		return valuedEntries;
	}

	@Override
	public Iterable<K> keys() {
		HashTableEntry<K, V>[] valuedEntries = valuedEntries();
		K[] keys = (K[]) new Object[valuedEntries.length];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = valuedEntries[i].key();
		}
		return () -> new ArrayIterator<>(keys);
	}

	@Override
	public Iterable<V> values() {
		HashTableEntry<K, V>[] valuedEntries = valuedEntries();
		V[] values = (V[]) new Object[valuedEntries.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = valuedEntries[i].value();
		}
		return () -> new ArrayIterator<>(values);
	}

}
