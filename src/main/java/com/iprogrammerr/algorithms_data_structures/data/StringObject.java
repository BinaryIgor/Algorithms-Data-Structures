package com.iprogrammerr.algorithms_data_structures.data;

public final class StringObject implements KeyValue<String, Object> {

    private final String key;
    private Object value;

    public StringObject(String key, Object value) {
	this.key = key;
	this.value = value;
    }

    @Override
    public String key() {
	return this.key;
    }

    @Override
    public Object value() {
	return this.value;
    }

    public void revalue(Object value) {
	this.value = value;
    }

}
