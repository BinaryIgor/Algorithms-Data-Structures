package com.iprogrammerr.algorithms_data_structures.binary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RunLengthDecoding implements Decompression<String> {

	private static final Pattern PATTERN = Pattern.compile("[0-9]+|[a-zA-Z]");
	private final String source;

	public RunLengthDecoding(String source) {
		this.source = source;
	}

	@Override
	public String decompressed() {
		StringBuilder builder = new StringBuilder();
		Matcher matcher = PATTERN.matcher(this.source);
		while (matcher.find()) {
			String group = matcher.group();
			if (Character.isDigit(group.charAt(0))) {
				int runLength = Integer.parseInt(group);
				matcher.find();
				while (runLength > 0) {
					builder.append(matcher.group());
					--runLength;
				}
			} else {
				builder.append(group);
			}
		}
		return builder.toString();
	}
}
