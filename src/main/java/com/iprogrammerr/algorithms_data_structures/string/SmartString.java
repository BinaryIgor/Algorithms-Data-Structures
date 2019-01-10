package com.iprogrammerr.algorithms_data_structures.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SmartString {

	private final String origin;

	public SmartString(String origin) {
		this.origin = origin;
	}

	public String reversed() {
		StringBuilder builder = new StringBuilder();
		for (int i = this.origin.length() - 1; i >= 0; --i) {
			builder.append(this.origin.charAt(i));
		}
		return builder.toString();
	}

	public String longestCommonPrefix(String string) {
		return longestCommonPrefix(this.origin, string);
	}

	private String longestCommonPrefix(String first, String second) {
		int length = Math.min(first.length(), second.length());
		int i;
		for (i = 0; i < length; ++i) {
			if (first.charAt(i) != second.charAt(i)) {
				break;
			}
		}
		return first.substring(0, i);
	}

	public String longestRepeatedSubstring() {
		List<String> suffixes = suffixes();
		Collections.sort(suffixes);
		String lrs = "";
		for (int i = 1; i < this.origin.length(); ++i) {
			String tmp = longestCommonPrefix(suffixes.get(i - 1), suffixes.get(i));
			if (tmp.length() > lrs.length()) {
				lrs = tmp;
			}
		}
		return lrs;
	}

	public List<String> suffixes() {
		List<String> suffixes = new ArrayList<>();
		for (int i = 0; i < this.origin.length(); ++i) {
			suffixes.add(this.origin.substring(i, this.origin.length()));
		}
		return suffixes;
	}
}
