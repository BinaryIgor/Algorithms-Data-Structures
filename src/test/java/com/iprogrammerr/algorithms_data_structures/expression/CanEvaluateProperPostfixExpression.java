package com.iprogrammerr.algorithms_data_structures.expression;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.iprogrammerr.algorithms_data_structures.expression.PostfixExpression;

public final class CanEvaluateProperPostfixExpression extends TypeSafeMatcher<PostfixExpression> {

	private static final double DELTA = 10e-6;
	private final double expected;

	public CanEvaluateProperPostfixExpression(double expected) {
		this.expected = expected;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(getClass().getSimpleName());
	}

	@Override
	protected boolean matchesSafely(PostfixExpression item) {
		boolean matched;
		try {
			matched = Math.abs(this.expected - item.value()) < DELTA;
		} catch (Exception e) {
			matched = false;
		}
		return matched;
	}
}
