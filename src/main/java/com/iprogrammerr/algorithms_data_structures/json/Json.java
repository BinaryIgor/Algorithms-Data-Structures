package com.iprogrammerr.algorithms_data_structures.json;

public interface Json {

	int intValue(String key) throws Exception;

	long longValue(String key) throws Exception;

	float floatValue(String key) throws Exception;

	double doubleValue(String key) throws Exception;

	boolean booleanValue(String key) throws Exception;

	String stringValue(String key) throws Exception;

	Json jsonValue(String key) throws Exception;

	JsonArray jsonArrayValue(String key) throws Exception;
}
