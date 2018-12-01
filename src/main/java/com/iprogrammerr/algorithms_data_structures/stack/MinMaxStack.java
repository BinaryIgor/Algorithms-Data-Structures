package com.iprogrammerr.algorithms_data_structures.stack;

import com.iprogrammerr.algorithms_data_structures.data.MinMax;

public final class MinMaxStack<T extends Comparable<T>> implements MinMax<T>, Stack<T> {

	private final Stack<T> base;
	private final Stack<T> min;
	private final Stack<T> max;

	private MinMaxStack(Stack<T> base, Stack<T> min, Stack<T> max) {
		this.base = base;
		this.min = min;
		this.max = max;
	}

	public MinMaxStack() {
		this(new LinkedListStack<>(), new LinkedListStack<>(), new LinkedListStack<>());
	}

	@Override
	public void push(T item) {
		this.base.push(item);
		if (this.max.isEmpty()) {
			this.max.push(item);
		} else {
			try {
				if (item.compareTo(this.max.peek()) > 0) {
					this.max.push(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (this.min.isEmpty()) {
			this.min.push(item);
		} else {
			try {
				if (item.compareTo(this.min.peek()) < 0) {
					this.min.push(item);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public T pop() throws Exception {
		T t = this.base.pop();
		if (this.max.peek().compareTo(t) == 0) {
			this.max.pop();
		}
		if (this.min.peek().compareTo(t) == 0) {
			this.min.pop();
		}
		return t;
	}

	@Override
	public T peek() throws Exception {
		return this.base.peek();
	}

	@Override
	public boolean isEmpty() {
		return this.base.isEmpty();
	}

	public T max() throws Exception {
		return this.max.peek();
	}

	@Override
	public T min() throws Exception {
		return this.min.peek();
	}
}
