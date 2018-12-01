package com.iprogrammerr.algorithms_data_structures.json;

import java.util.HashMap;
import java.util.Map;

import com.iprogrammerr.algorithms_data_structures.initialization.UnreliableInitialization;

public final class MapFromJson implements UnreliableInitialization<Map<String, Object>> {

	private final String source;

	public MapFromJson(String source) {
		this.source = source;
	}

	// TODO complex processing....
	@Override
	public Map<String, Object> value() throws Exception {
		Map<String, Object> values = new HashMap<>();

		return values;
	}

}
