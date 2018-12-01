package com.iprogrammerr.algorithms_data_structures.mock;

public final class MockedBinary {

	private final byte[] needed;
	private final int index;

	public MockedBinary(byte[] needed, int index) {
		this.needed = needed;
		this.index = index;
	}

	public byte[] content() throws Exception {
		byte[] bytes = new byte[1000 + (int) (Math.random() * (100_000))];
		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = (byte) (Byte.MIN_VALUE + (Math.random() * 2 * Byte.MAX_VALUE));
		}
		for (int i = this.index; i < this.needed.length; ++i) {
			bytes[i] = this.needed[i];
		}
		return bytes;
	}
}
