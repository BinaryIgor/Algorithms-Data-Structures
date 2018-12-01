package com.iprogrammerr.algorithms_data_structures.expression;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.stack.LinkedListStack;
import com.iprogrammerr.algorithms_data_structures.stack.Stack;

public final class InfixExpression implements Expression<Double> {

	private static final String ADDITION = "+";
	private static final String MULTIPLICATION = "*";
	private static final String OPENING_BRACKET = "(";
	private static final String CLOSING_BRACKET = ")";
	private final Stack<String> operations;
	private final Stack<Double> values;
	private final Initialization<String> source;

	public InfixExpression(Stack<String> operations, Stack<Double> values,
			Initialization<String> source) {
		this.operations = operations;
		this.values = values;
		this.source = source;
	}

	public InfixExpression(Initialization<String> source) {
		this(new LinkedListStack<>(), new LinkedListStack<>(), source);
	}

	public InfixExpression(String source) {
		this(new LinkedListStack<>(), new LinkedListStack<>(), () -> source);
	}

	@Override
	public Double value() throws Exception {
		String[] expressions = this.source.value().split(" ");
		for (String e : expressions) {
			if (e.equals(ADDITION) || e.equals(MULTIPLICATION)) {
				this.operations.push(e);
			} else if (e.equals(CLOSING_BRACKET)) {
				executeOperation();
			} else if (!e.equals(OPENING_BRACKET)) {
				this.values.push(Double.parseDouble(e));
			}
		}
		while (!this.operations.isEmpty()) {
			executeOperation();
		}
		return this.values.pop();
	}

	private void executeOperation() throws Exception {
		String op = this.operations.pop();
		if (op.equals(ADDITION)) {
			this.values.push(this.values.pop() + this.values.pop());
		} else if (op.equals(MULTIPLICATION)) {
			this.values.push(this.values.pop() * this.values.pop());
		}
	}
}
