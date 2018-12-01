package com.iprogrammerr.algorithms_data_structures.expression;

import com.iprogrammerr.algorithms_data_structures.stack.LinkedListStack;
import com.iprogrammerr.algorithms_data_structures.stack.Stack;

//TODO numbers, not digits, proper brackets handling
public final class InfixToPostfix implements Expression<String> {

	private static final String ADDITION = "+";
	private static final String SUBTRACTION = "-";
	private static final String MULTIPLICATION = "*";
	private static final String DIVISION = "/";
	private static final String EXPONENTIATION = "^";
	private static final String OPENING_BRACKET = "(";
	private static final String CLOSING_BRACKET = ")";
	private final String infix;
	private final Stack<String> stack;

	private InfixToPostfix(String infix, Stack<String> stack) {
		this.infix = infix;
		this.stack = stack;
	}

	public InfixToPostfix(String infix) {
		this(infix, new LinkedListStack<>());
	}

	@Override
	public String value() throws Exception {
		String result = "";
		String[] expressions = this.infix.split(" ");
		for (String e : expressions) {
			if (isValue(e)) {
				result += e + " ";
			} else if (e.equals(OPENING_BRACKET)) {
				this.stack.push(e);
			} else if (e.equals(CLOSING_BRACKET)) {
				while (!this.stack.isEmpty() && this.stack.peek() != OPENING_BRACKET) {
					result += this.stack.pop();
				}
				if (!this.stack.isEmpty() && this.stack.peek() != OPENING_BRACKET) {
					throw new Exception("Invalid expression");
				}
			} else {
				while (!this.stack.isEmpty() && precedence(e) <= precedence(this.stack.peek())) {
					result += this.stack.pop();
				}
				this.stack.push(e + " ");
			}
		}
		while (!this.stack.isEmpty()) {
			result += this.stack.pop();
		}
		return result;
	}

	private boolean isValue(String expression) {
		return (!expression.isEmpty() && Character.isLetterOrDigit(expression.charAt(0))
				|| (expression.length() > 1 && (expression.charAt(0) == '-' || expression.charAt(0) == '+')
						&& Character.isLetterOrDigit(expression.charAt(1))));
	}

	private int precedence(String c) {
		int p;
		if (c.equals(ADDITION) || c.equals(SUBTRACTION)) {
			p = 1;
		} else if (c.equals(MULTIPLICATION) || c.equals(DIVISION)) {
			p = 2;
		} else if (c.equals(EXPONENTIATION)) {
			p = 3;
		} else {
			p = -1;
		}
		return p;
	}
}
