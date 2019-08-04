package com.iprogrammerr.algorithms_data_structures.hashtable;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.NextPrimeNumber;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class ChainedArrayHashTable<K, V> implements HashTable<K, V> {

	private static final float LOAD_FACTOR = 0.75f;
	private Initialization<List<HashTableEntry<K, V>>[]> entries;
	private int size;
	private int entriesSize;

	public ChainedArrayHashTable(int initialCapacity) {
		this.entries = new StickyInitialization<>(() -> {
			int min = (int) (initialCapacity / LOAD_FACTOR);
			return new ArrayList[new NextPrimeNumber(min).value()];
		});
	}

	public ChainedArrayHashTable() {
		this(50);
	}

	@Override
	public HashTable<K, V> save(K key, V value) {
		int hashedKey = hashedKey(key);
		List<HashTableEntry<K, V>> keyEntries = this.entries.value()[hashedKey(key)];
		if (keyEntries == null) {
			keyEntries = new ArrayList<>();
			keyEntries.add(new HashTableEntry<K, V>(key, value));
			this.entries.value()[hashedKey] = keyEntries;
			++this.size;
			++this.entriesSize;
			if (this.entriesSize > (this.entries.value().length * LOAD_FACTOR)) {
				resize(this.entriesSize * 2);
			}
		} else {
			int previous = indexOf(key, keyEntries);
			if (previous >= 0) {
				keyEntries.set(previous, new HashTableEntry<K, V>(key, value));
			} else {
				keyEntries.add(new HashTableEntry<K, V>(key, value));
				++this.size;
			}
		}
		return this;
	}

	private int indexOf(K key, List<HashTableEntry<K, V>> keyEntries) {
		for (int i = 0; i < keyEntries.size(); i++) {
			if (keyEntries.get(i).key().equals(key)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public V value(K key) {
		List<HashTableEntry<K, V>> keyEntries = this.entries.value()[hashedKey(key)];
		if (keyEntries == null) {
			throw new RuntimeException(String.format("There is no value associated with %s key", key));
		}
		for (HashTableEntry<K, V> entry : keyEntries) {
			if (entry.key().equals(key)) {
				return entry.value();
			}
		}
		throw new RuntimeException(String.format("There is no value associated with %s key", key));
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
		return Math.abs(key.hashCode()) % this.entries.value().length;
	}

	@Override
	public void remove(K key) {
		int hashedKey = hashedKey(key);
		List<HashTableEntry<K, V>> keyEntries = this.entries.value()[hashedKey];
		if (keyEntries != null) {
			int index = indexOf(key, keyEntries);
			if (index >= 0) {
				keyEntries.remove(index);
			}
			if (keyEntries.isEmpty()) {
				this.entries.value()[hashedKey] = null;
				--this.entriesSize;
				if (this.entriesSize < (this.entries.value().length / 4)) {
					resize(this.entries.value().length / 2);
				}
			}
		}
	}

	@Override
	public Iterable<HashTableEntry<K, V>> entries() {
		return () -> valuedEntries().iterator();
	}

	@Override
	public Iterable<K> keys() {
		return () -> valuedEntries().stream().map(entry -> entry.key()).iterator();
	}

	@Override
	public Iterable<V> values() {
		return () -> valuedEntries().stream().map(entry -> entry.value()).iterator();
	}

	private List<HashTableEntry<K, V>> valuedEntries() {
		List<HashTableEntry<K, V>> valuedEntries = new ArrayList<>();
		for (List<HashTableEntry<K, V>> keyEntries : this.entries.value()) {
			if (keyEntries != null) {
				valuedEntries.addAll(keyEntries);
			}
		}
		return valuedEntries;
	}

	private void resize(int size) {
		List<HashTableEntry<K, V>>[] previous = this.entries.value();
		this.entries = new StickyInitialization<>(() -> {
			return new ArrayList[new NextPrimeNumber(size).value()];
		});
		this.entriesSize = this.size = 0;
		for (List<HashTableEntry<K, V>> keyEntries : previous) {
			if (keyEntries == null) {
				continue;
			}
			for (HashTableEntry<K, V> entry : keyEntries) {
				save(entry.key(), entry.value());
			}
		}
	}
}
