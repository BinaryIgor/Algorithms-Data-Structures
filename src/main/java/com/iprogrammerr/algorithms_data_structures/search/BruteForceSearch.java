package com.iprogrammerr.algorithms_data_structures.search;

public final class BruteForceSearch implements StringSearch {

	private final String source;

	public BruteForceSearch(String source) {
		this.source = source;
	}

	@Override
	public int index(String pattern) {
		int index = -1;
		if (pattern.length() > 0) {
			for (int i = 0; i <= this.source.length() - pattern.length(); ++i) {
				int j;
				for (j = 0; j < pattern.length(); ++j) {
					if (this.source.charAt(i + j) != pattern.charAt(j)) {
						break;
					}
				}
				if (j == pattern.length()) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
}
