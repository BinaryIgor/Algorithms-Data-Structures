package com.iprogrammerr.algorithms_data_structures.json;

import java.util.Map;

import com.iprogrammerr.algorithms_data_structures.initialization.UnreliableStickyInitialization;

public final class ParsedJson implements Json {

	private final UnreliableStickyInitialization<Map<String, Object>> values;

	private ParsedJson(UnreliableStickyInitialization<Map<String, Object>> values) {
		this.values = values;
	}

	public ParsedJson(String source) {
		this(new UnreliableStickyInitialization<>(new MapFromJson(source)));
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
	public float floatValue(String key) throws Exception {
		return value(key, Float.class);
	}

	@Override
	public double doubleValue(String key) throws Exception {
		return value(key, Double.class);
	}

	@Override
	public boolean booleanValue(String key) throws Exception {
		return value(key, Boolean.class);
	}

	@Override
	public String stringValue(String key) throws Exception {
		return value(key, String.class);
	}

	@Override
	public Json jsonValue(String key) throws Exception {
		return value(key, Json.class);
	}

	@Override
	public JsonArray jsonArrayValue(String key) throws Exception {
		return value(key, JsonArray.class);
	}

	private <T> T value(String key, Class<T> clazz) throws Exception {
		Object value = this.values.value().get(key);
		if (value == null || !clazz.isAssignableFrom(value.getClass())) {
			throw new Exception(String.format(
					"Json does not have value associated with %s key and of %s type", key, clazz));
		}
		return clazz.cast(value);
	}
}
