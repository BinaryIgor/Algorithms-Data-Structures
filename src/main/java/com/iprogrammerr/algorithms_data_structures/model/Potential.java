package com.iprogrammerr.algorithms_data_structures.model;

public final class Potential<T> {

	private T value;

	public Potential(T value) {
		this.value = value;
	}

	public Potential() {
		this(null);
	}

	public void fill(T value) {
		this.value = value;
	}

	public void change(Potential<T> potential) {
		try {
			if (potential.isFilled()) {
				this.value = potential.value;
			} else {
				empty();
			}
		} catch (Exception e) {
			empty();
		}
	}

	public T value() {
		if (this.value == null) {
			throw new RuntimeException("Potential does not have any value");
		}
		return this.value;
	}

	public boolean isFilled() {
		return this.value != null;
	}

	public void empty() {
		this.value = null;
	}

	@Override
	public String toString() {
		return "Potential [value=" + this.value + "]";
	}

	@Override
	public boolean equals(Object object) {
		boolean equal;
		if (object == null || !(object instanceof Potential)) {
			equal = false;
		} else if (object == this) {
			equal = true;
		} else {
			Potential<T> other = (Potential<T>) object;
			equal = (!isFilled() && !other.isFilled())
					|| (isFilled() && this.value.equals(other.value));
		}
		return equal;
	}

}
