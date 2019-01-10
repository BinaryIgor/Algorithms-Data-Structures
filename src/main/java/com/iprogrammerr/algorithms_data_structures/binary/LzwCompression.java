package com.iprogrammerr.algorithms_data_structures.binary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class LzwCompression implements Compression<List<Integer>> {

	private final String source;
	private final Initialization<List<Integer>> compressed;

	public LzwCompression(String source) {
		this.source = source;
		this.compressed = new StickyInitialization<>(() -> {
			int size = 256;
			Map<String, Integer> dictionary = new HashMap<>();
			for (int i = 0; i < size; ++i) {
				dictionary.put(String.valueOf((char) i), i);
			}
			String base = "";
			List<Integer> compressed = new ArrayList<>();
			for (char c : this.source.toCharArray()) {
				String tmp = base + c;
				if (dictionary.containsKey(tmp)) {
					base = tmp;
				} else {
					compressed.add(dictionary.get(base));
					++size;
					dictionary.put(tmp, size);
					base = String.valueOf(c);
				}
			}
			if (!base.equals("")) {
				compressed.add(dictionary.get(base));
			}
			return compressed;
		});
	}

	@Override
	public List<Integer> compressed() {
		return this.compressed.value();
	}
}
