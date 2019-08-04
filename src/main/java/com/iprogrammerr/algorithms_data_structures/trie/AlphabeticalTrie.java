package com.iprogrammerr.algorithms_data_structures.trie;

import java.util.ArrayList;
import java.util.List;

public class AlphabeticalTrie implements Trie {

	private final TrieNode root;

	public AlphabeticalTrie(char root) {
		this.root = new TrieNode(root);
	}

	public AlphabeticalTrie() {
		this('\0');
	}

	@Override
	public void insert(String word) {
		TrieNode tmp = this.root;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); ++i) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (tmp.children()[index] == null) {
				tmp.children()[index] = new TrieNode(c);
			}
			tmp = tmp.children()[index];
		}
		tmp.setLeaf(true);
	}

	@Override
	public boolean has(String word) {
		boolean has;
		try {
			has = node(word).leaf();
		} catch (Exception e) {
			has = false;
		}
		return has;
	}

	private TrieNode node(String word) throws Exception {
		TrieNode tmp = this.root;
		word = word.toLowerCase();
		for (int i = 0; i < word.length(); ++i) {
			char c = word.charAt(i);
			int index = c - 'a';
			if (tmp.children()[index] == null) {
				throw new Exception(String.format("There is no any node associated with %s word", word));
			} else {
				tmp = tmp.children()[index];
			}
		}
		return tmp;
	}

	@Override
	public List<String> words(String prefix) {
		List<String> words = new ArrayList<>();
		try {
			collect(node(prefix), prefix, words);
		} catch (Exception e) {

		}
		return words;
	}

	private void collect(TrieNode node, String prefix, List<String> words) {
		if (node.leaf()) {
			words.add(prefix);
		}
		for (TrieNode n : node.children()) {
			if (n != null) {
				collect(n, prefix + n.character(), words);
			}
		}
	}

	@Override
	public String longestCommonPrefix() {
		String lcp = "";
		TrieNode tn = this.root;
		int idx = tn.singleChildIndex();
		while (idx > -1 && !tn.leaf()) {
			tn = tn.children()[idx];
			lcp = lcp + (char) (idx + 'a');
			idx = tn.singleChildIndex();
		}
		return lcp;
	}
}
