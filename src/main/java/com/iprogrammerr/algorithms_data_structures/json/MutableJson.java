package com.iprogrammerr.algorithms_data_structures.json;

public interface MutableJson {

	void put(String key, Object value);

	void put(String key, Iterable<Object> values);

	void put(String key, Object[] values);

	String value();
}
