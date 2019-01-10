package com.iprogrammerr.algorithms_data_structures.binary.huffman;

public final class HuffmanNode implements HuffmanElement {

	private int frequency;

	public HuffmanNode(int frequency) {
		this.frequency = frequency;
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
		return this.leaf();
	}

	@Override
	public char value() {
		return '\0';
	}

	@Override
	public HuffmanElement left() {
		throw new RuntimeException("This is a node, it does not have any elements");
	}

	@Override
	public HuffmanElement right() {
		throw new RuntimeException("This is a node, it does not have any elements");
	}
}
