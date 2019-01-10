package com.iprogrammerr.algorithms_data_structures.binary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class LzwDecompression implements Decompression<String> {

	private final List<Integer> compressed;
	private final Initialization<String> decompressed;

	public LzwDecompression(List<Integer> compressed) {
		this.compressed = compressed;
		this.decompressed = new StickyInitialization<>(() -> {
			int size = 256;
			Map<Integer, String> dct = new HashMap<>(size);
			for (int i = 0; i < size; ++i) {
				dct.put(i, String.valueOf((char) i));
			}
			StringBuilder sb = new StringBuilder();
			String w = "";
			for (int code : this.compressed) {
				String e;
				if (dct.containsKey(code)) {
					e = dct.get(code);
				} else if (code == size) {
					e = w + w.charAt(0);
				} else {
					throw new RuntimeException("Bad compression format");
				}
				sb.append(e);
				dct.put(size, w + e.charAt(0));
				++size;
				w = e;
			}
			return sb.toString();
		});
	}

	@Override
	public String decompressed() {
		return this.decompressed.value();
	}
}
