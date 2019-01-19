package com.iprogrammerr.algorithms_data_structures.initialization;

public final class StickyInitialization<T> implements StickableInitialization<T> {

	private final Initialization<T> base;
	private T value;
	private boolean stick;

	public StickyInitialization(Initialization<T> base) {
		this.base = base;
		this.stick = false;
	}

	@Override
	public T value() {
		if (this.value == null || !this.stick) {
			this.value = this.base.value();
			this.stick = true;
		}
		return this.value;
	}

	@Override
	public void unstick() {
		this.stick = false;
	}
}
