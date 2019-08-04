package com.iprogrammerr.algorithms_data_structures.trie;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class AlphabeticalTrieTest {

	@Test
	public void hasWords() {
		AlphabeticalTrie trie = new AlphabeticalTrie();

		List<String> expected = Arrays.asList("aa", "bb");
		expected.forEach(trie::insert);
		trie.insert("d");
		trie.insert("dz");

		for (String e : expected) {
			MatcherAssert.assertThat(trie.has(e), Matchers.equalTo(true));
		}
	}

	@Test
	public void findsWords() {
		AlphabeticalTrie trie = new AlphabeticalTrie();

		List<String> expected = Arrays.asList("a", "ab", "ac", "abd");
		expected.forEach(trie::insert);

		MatcherAssert.assertThat(trie.words("a"), Matchers.contains(expected.stream().sorted().toArray()));
	}

	@Test
	public void findsLongestCommonPrefix() {
		AlphabeticalTrie trie = new AlphabeticalTrie();

		trie.insert("abc");
		trie.insert("abcx");
		trie.insert("abcZ");

		MatcherAssert.assertThat(trie.longestCommonPrefix(), Matchers.equalTo("abc"));
	}
}
