package com.iprogrammerr.algorithms_data_structures.trie;

public class TrieNode {

	private final char character;
	private final TrieNode[] children;
	private boolean leaf;
	private boolean visited;

	public TrieNode(char character, TrieNode[] children) {
		this.character = character;
		this.children = children;
	}

	public TrieNode(char character, int children) {
		this(character, new TrieNode[children]);
	}

	public TrieNode(char character) {
		this(character, 26);
	}

	public char character() {
		return this.character;
	}

	public TrieNode[] children() {
		return this.children;
	}

	public boolean leaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean visited() {
		return this.visited;
	}

	public int singleChildIndex() {
		int idx = -1;
		for (int i = 0; i < this.children.length; ++i) {
			if (this.children[i] != null) {
				if (idx != -1) {
					idx = -1;
					break;
				}
				idx = i;
			}
		}
		return idx;
	}

}
