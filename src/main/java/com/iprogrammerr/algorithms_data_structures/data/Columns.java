package com.iprogrammerr.algorithms_data_structures.data;

import java.util.ArrayList;
import java.util.List;

public final class Columns implements TypedMap {

	private final List<StringObject> values;

	private Columns(List<StringObject> values) {
		this.values = values;
	}

	public Columns() {
		this(new ArrayList<>(10));
	}

	@Override
	public TypedMap put(String key, Object value) {
		int index = index(key);
		if (index >= 0) {
			this.values.get(index).revalue(value);
		} else {
			this.values.add(new StringObject(key, value));
		}
		return this;
	}

	private int index(String key) {
		int index = -1;
		for (int i = 0; i < this.values.size(); i++) {
			if (this.values.get(i).key().equals(key)) {
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public boolean booleanValue(String key) throws Exception {
		return value(key, Boolean.class);
	}

	@Override
	public int intValue(String key) throws Exception {
		return value(key, Integer.class);
	}

	@Override
	public long longValue(String key) throws Exception {
		return value(key, Long.class);
	}

	@Override
	public String stringValue(String key) throws Exception {
		return value(key, String.class);
	}

	@Override
	public float floatValue(String key) throws Exception {
		return value(key, Float.class);
	}

	@Override
	public double doubleValue(String key) throws Exception {
		return value(key, Double.class);
	}

	@Override
	public byte[] binaryValue(String key) throws Exception {
		return value(key, byte[].class);
	}

	@Override
	public List<StringObject> keyValues() {
		return this.values;
	}

	private <T> T value(String key, Class<T> clazz) throws Exception {
		for (KeyValue<String, Object> v : this.values) {
			if (v.key().equals(key) && v.value().getClass().isAssignableFrom(clazz)) {
				return clazz.cast(v.value());
			}
		}
		throw new Exception(String.format("Key %s of type %s is not present", key, clazz));
	}

	@Override
	public boolean isEmpty() {
		return this.values.isEmpty();
	}

	@Override
	public <T> boolean has(String key, Class<T> clazz) {
		int index = index(key);
		return index >= 0 && this.values.get(index).getClass().isAssignableFrom(clazz);
	}

}
