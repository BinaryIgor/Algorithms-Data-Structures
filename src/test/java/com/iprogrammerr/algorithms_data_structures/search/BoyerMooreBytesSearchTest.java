package com.iprogrammerr.algorithms_data_structures.search;

import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;

import org.junit.Test;

public final class BoyerMooreBytesSearchTest {

	@Test
	public void canFound() throws Exception {
		byte[] prefix = "withoutPattern".getBytes();
		byte[] pattern = "\r\n".getBytes();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(prefix);
		baos.write(pattern);
		baos.write("other".getBytes());
		assertThat(new BoyerMooreBytesSearch(baos.toByteArray()),
				new BytesSearchThatReturnProperIndices(pattern, prefix.length));
	}

	@Test
	public void shouldNotFoundEmpty() {
		assertThat(new BoyerMooreBytesSearch(new byte[] { 1, 2, 3, 4, 4, 6, 7, 8 }),
				new BytesSearchThatReturnProperIndices(new byte[0], -1));
	}

	@Test
	public void shouldNotFound() {
		assertThat(new BoyerMooreBytesSearch(new byte[] { 1, 2, 3, 4, 4, 6, 7, 8, 99, 35 }),
				new BytesSearchThatReturnProperIndices(new byte[] { 99, 88 }, -1));
	}
}
