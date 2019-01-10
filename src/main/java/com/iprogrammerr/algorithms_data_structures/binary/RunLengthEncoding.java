package com.iprogrammerr.algorithms_data_structures.binary;

public final class RunLengthEncoding implements Compression<String> {

	private final String source;

	public RunLengthEncoding(String source) {
		this.source = source;
	}

	@Override
	public String compressed() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.source.length(); ++i) {
			int runLength = 1;
			while (i + 1 < this.source.length() && this.source.charAt(i) == this.source.charAt(i + 1)) {
				++runLength;
				++i;
			}
			if (runLength > 1) {
				sb.append(runLength);
			}
			sb.append(this.source.charAt(i));
		}
		return sb.toString();
	}
}
