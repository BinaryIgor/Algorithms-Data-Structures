package com.iprogrammerr.algorithms_data_structures.search;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public final class BytesSearchThatReturnProperIndices extends TypeSafeMatcher<BytesSearch> {

	private final byte[] pattern;
	private final int expected;
	private int actual;

	public BytesSearchThatReturnProperIndices(byte[] pattern, int expected) {
		this.pattern = pattern;
		this.expected = expected;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(getClass().getSimpleName());
	}

	@Override
	protected void describeMismatchSafely(BytesSearch item, Description description) {
		description.appendText(String.format("Expected index %d, but was %d", this.expected, this.actual));
	}

	@Override
	protected boolean matchesSafely(BytesSearch item) {
		this.actual = item.index(this.pattern);
		return this.actual == this.expected;
	}
}
