package com.iprogrammerr.algorithms_data_structures.search;

import java.util.HashMap;
import java.util.Map;

public final class BoyerMooreSearch implements StringSearch {

	private final String source;

	public BoyerMooreSearch(String source) {
		this.source = source;
	}

	@Override
	public int index(String pattern) {
		int index = -1;
		if (pattern.length() > 0) {
			Map<Character, Integer> charsShifts = charsShifts(pattern);
			int shift;
			for (int i = 0; i <= this.source.length() - pattern.length(); i += shift) {
				shift = 0;
				int j;
				for (j = pattern.length() - 1; j >= 0; --j) {
					if (this.source.charAt(i + j) != pattern.charAt(j)) {
						shift = charsShifts.containsKey(pattern.charAt(j)) ? charsShifts.get(pattern.charAt(j))
								: pattern.length();
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

	private Map<Character, Integer> charsShifts(String pattern) {
		Map<Character, Integer> shifts = new HashMap<>();
		for (int i = 0; i < pattern.length(); ++i) {
			int shift = Math.max(1, pattern.length() - i - 1);
			shifts.put(pattern.charAt(i), shift);
		}
		return shifts;
	}
}
