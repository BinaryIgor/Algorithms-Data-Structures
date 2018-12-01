package com.iprogrammerr.algorithms_data_structures.search;

import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class BoyerMooreSearchTest {

	@Test
	public void canFound() {
		String pattern = System.currentTimeMillis() + "ttt";
		assertThat(new BoyerMooreSearch(String.format("Text%sText", pattern)),
				new StringSearchThatReturnProperIndices(pattern, 4));
	}

	@Test
	public void shouldNotFoundEmpty() {
		assertThat(new BoyerMooreSearch(String.valueOf(System.currentTimeMillis())),
				new StringSearchThatReturnProperIndices("", -1));
	}

	@Test
	public void shouldNotFound() {
		assertThat(new BoyerMooreSearch(String.valueOf(System.currentTimeMillis())),
				new StringSearchThatReturnProperIndices("present", -1));
	}
}
