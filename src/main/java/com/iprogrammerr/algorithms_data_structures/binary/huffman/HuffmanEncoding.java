package com.iprogrammerr.algorithms_data_structures.binary.huffman;

import java.util.PriorityQueue;

public final class HuffmanEncoding {

	private final String source;

	public HuffmanEncoding(String source) {
		this.source = source;
	}

	private HuffmanElement tree() {
		int[] frequencies = frequencies();
		PriorityQueue<HuffmanElement> queue = new PriorityQueue<>();
		for (int i = 0; i < frequencies.length; ++i) {
			if (frequencies[i] > 0) {
				queue.add(new HuffmanLeaf(frequencies[i], (char) i));
			}
		}
		while (queue.size() > 1) {
			HuffmanElement first = queue.poll();
			HuffmanElement second = queue.poll();
			queue.add(new HuffmanTree(first, second));
		}
		return queue.poll();
	}

	private int[] frequencies() {
		int[] frequencies = new int[256];
		for (char c : this.source.toCharArray()) {
			++frequencies[c];
		}
		return frequencies;
	}

	public void printCodes() {
		printCodes(tree(), new StringBuilder(""));
	}

	private void printCodes(HuffmanElement tree, StringBuilder prefix) {
		if (tree.leaf()) {
			System.out.println(tree.value() + "\t" + tree.frequency() + "\t" + prefix);
		} else {
			prefix.append("0");
			printCodes(tree.left(), prefix);
			prefix.deleteCharAt(prefix.length() - 1);
			prefix.append("1");
			printCodes(tree.right(), prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
}
