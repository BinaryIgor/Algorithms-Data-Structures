package com.iprogrammerr.algorithms_data_structures.binary.huffman;

public interface HuffmanElement extends Comparable<HuffmanElement> {

	int frequency();

	boolean leaf();

	char value();

	HuffmanElement left();

	HuffmanElement right();
}
