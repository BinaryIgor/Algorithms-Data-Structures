package com.iprogrammerr.algorithms_data_structures.search;

import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class BruteForceSearchTest {

	@Test
	public void canFound() {
		assertThat(new BruteForceSearch("This is very, very long text search to be tested"),
				new StringSearchThatReturnProperIndices("very", 8));
	}

	@Test
	public void shouldNotFoundEmpty() {
		assertThat(new BruteForceSearch("empty"), new StringSearchThatReturnProperIndices("", -1));
	}

	@Test
	public void shouldNotFound() {
		assertThat(new BruteForceSearch("empty"), new StringSearchThatReturnProperIndices("not", -1));
	}
}
