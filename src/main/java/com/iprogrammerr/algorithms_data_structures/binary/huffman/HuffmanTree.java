package com.iprogrammerr.algorithms_data_structures.binary.huffman;

public final class HuffmanTree implements HuffmanElement {

	private final HuffmanElement left;
	private final HuffmanElement right;

	public HuffmanTree(HuffmanElement left, HuffmanElement right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int compareTo(HuffmanElement other) {
		return value() - other.value();
	}

	@Override
	public int frequency() {
		return this.left.frequency() + this.right.frequency();
	}

	@Override
	public boolean leaf() {
		return false;
	}

	@Override
	public char value() {
		return '\0';
	}

	@Override
	public HuffmanElement left() {
		return this.left;
	}

	@Override
	public HuffmanElement right() {
		return this.right;
	}
}
