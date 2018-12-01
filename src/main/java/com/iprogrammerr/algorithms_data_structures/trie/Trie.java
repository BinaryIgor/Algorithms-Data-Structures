package com.iprogrammerr.algorithms_data_structures.trie;

import java.util.List;

public interface Trie {

	void insert(String word);

	boolean has(String word);

	List<String> words(String prefix);

	String longestCommonPrefix();
}
