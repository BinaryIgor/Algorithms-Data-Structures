package com.iprogrammerr.algorithms_data_structures.search;

import java.util.HashMap;
import java.util.Map;

public final class BoyerMooreBytesSearch implements BytesSearch {

	private final byte[] source;

	public BoyerMooreBytesSearch(byte[] source) {
		this.source = source;
	}

	@Override
	public int index(byte[] pattern) {
		int index = -1;
		if (pattern.length > 0) {
			Map<Byte, Integer> bytesShifts = bytesShifts(pattern);
			int shift;
			for (int i = 0; i <= this.source.length - pattern.length; i += shift) {
				shift = 0;
				int j;
				for (j = pattern.length - 1; j >= 0; --j) {
					if (this.source[i + j] != pattern[j]) {
						shift = bytesShifts.containsKey(pattern[j]) ? bytesShifts.get(pattern[j]) : pattern.length;
						break;

					}
				}
				if (j == -1) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	private Map<Byte, Integer> bytesShifts(byte[] pattern) {
		Map<Byte, Integer> shifts = new HashMap<>();
		for (int i = 0; i < pattern.length; ++i) {
			int shift = Math.max(1, pattern.length - i - 1);
			shifts.put(pattern[i], shift);
		}
		return shifts;
	}
}
