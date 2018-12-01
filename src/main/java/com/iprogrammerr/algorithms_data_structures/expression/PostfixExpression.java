package com.iprogrammerr.algorithms_data_structures.expression;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.stack.LinkedListStack;
import com.iprogrammerr.algorithms_data_structures.stack.Stack;

public final class PostfixExpression implements Expression<Double> {

	private final Stack<Double> values;
	private final Initialization<String> source;

	private PostfixExpression(Stack<Double> values, Initialization<String> source) {
		this.values = values;
		this.source = source;
	}

	public PostfixExpression(Initialization<String> source) {
		this(new LinkedListStack<>(), source);
	}

	public PostfixExpression(String source) {
		this(new LinkedListStack<>(), () -> source);
	}

	@Override
	public Double value() throws Exception {
		String[] se = this.source.value().split(" ");
		for (int i = 0; i < se.length; ++i) {
			if (se[i].isEmpty()) {
				continue;
			}
			if (Character.isDigit(se[i].charAt(0))
					|| (se[i].length() > 1 && se[i].charAt(0) == '-')) {
				this.values.push(Double.parseDouble(se[i].replaceAll(",", ".")));
			} else {
				double first = this.values.pop();
				double second = this.values.pop();
				char op = se[i].charAt(0);
				if (op == '+') {
					this.values.push(first + second);
				} else if (op == '-') {
					this.values.push(second - first);
				} else if (op == '/') {
					this.values.push(second / first);
				} else if (op == '*') {
					this.values.push(first * second);
				} else {
					this.values.push(Math.pow(second, first));
				}
			}
		}
		return this.values.pop();
	}
}
