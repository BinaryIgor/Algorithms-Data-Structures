package com.iprogrammerr.algorithms_data_structures.expression;

import static org.junit.Assert.assertThat;

import java.util.Random;

import org.junit.Test;

import com.iprogrammerr.algorithms_data_structures.expression.PostfixExpression;

public final class PostfixExpressionTest {

	@Test
	public void canEvaluteAddition() {
		double expected = 1 + Math.random() * 100;
		double a = expected / 2;
		assertThat(new PostfixExpression(String.format("%f %f +", a, a)),
				new CanEvaluateProperPostfixExpression(expected));
	}

	@Test
	public void canEvaluteSubtraction() {
		double random = 1 + Math.random() * 100;
		double a = random / 2;
		double expected = random - a;
		assertThat(new PostfixExpression(String.format("%f %f -", random, a)),
				new CanEvaluateProperPostfixExpression(expected));
	}

	@Test
	public void canEvaluteMultiplication() {
		double expected = 1 + Math.random() * 100;
		double a = expected / 2;
		assertThat(new PostfixExpression(String.format("%f %d *", a, 2)),
				new CanEvaluateProperPostfixExpression(expected));
	}

	@Test
	public void canEvaluteDivision() {
		double random = 1 + Math.random() * 100;
		double expected = random / 3;
		assertThat(new PostfixExpression(String.format("%f %d /", random, 3)),
				new CanEvaluateProperPostfixExpression(expected));
	}

	@Test
	public void canEvaluteExponentiation() {
		Random random = new Random();
		double base = -10 + random.nextInt(20);
		int pow = 1 + new Random().nextInt(5);
		double expected = Math.pow(base, pow);
		assertThat(new PostfixExpression(String.format("%.1f %d ^", base, pow)),
				new CanEvaluateProperPostfixExpression(expected));
	}

	@Test
	public void canEvaluateComplexExpression() {
		double expected = 1 + Math.random() * 100;
		double a = expected / 8;
		double b = 2;
		double c = a * 2;
		double d = 2 * c;
		double e = c;
		String expression = String.format("%f %f * %f %f %f %f - + + +", a, b, c, c, d, e);
		assertThat(new PostfixExpression(expression),
				new CanEvaluateProperPostfixExpression(expected));
	}
}
