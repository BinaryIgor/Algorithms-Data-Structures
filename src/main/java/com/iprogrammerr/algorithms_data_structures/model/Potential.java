package com.iprogrammerr.algorithms_data_structures.model;

public final class Potential<T> {

	private T value;

	public Potential(T value) {
		this.value = value;
	}

	public Potential() {
		this(null);
	}

	public void revalue(T value) {
		this.value = value;
	}

	public void revalue(Potential<T> potential) {
		if (potential.hasValue()) {
			this.value = potential.value;
		} else {
			empty();
		}
	}

	public T value() {
		if (this.value == null) {
			throw new RuntimeException("Potential does not have any value");
		}
		return this.value;
	}

	public boolean isEmpty() {
		return this.value == null;
	}

	public boolean hasValue() {
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
			equal = (!isEmpty() && !other.isEmpty()) || (isEmpty() && this.value.equals(other.value));
		}
		return equal;
	}
}
