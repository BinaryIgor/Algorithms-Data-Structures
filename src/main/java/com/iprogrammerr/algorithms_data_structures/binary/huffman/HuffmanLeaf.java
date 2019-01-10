package com.iprogrammerr.algorithms_data_structures.binary.huffman;

public final class HuffmanLeaf implements HuffmanElement {

	private int frequency;
	private char value;

	public HuffmanLeaf(int frequency, char value) {
		this.frequency = frequency;
		this.value = value;
	}

	@Override
	public int compareTo(HuffmanElement other) {
		return this.frequency - other.value();
	}

	@Override
	public int frequency() {
		return this.frequency;
	}

	@Override
	public boolean leaf() {
		return true;
	}

	@Override
	public char value() {
		return this.value;
	}

	@Override
	public HuffmanElement left() {
		throw new RuntimeException("This is a leaf, it does not have any elements");
	}

	@Override
	public HuffmanElement right() {
		throw new RuntimeException("This is a leaf, it does not have any elements");
	}
}
