package com.iprogrammerr.algorithms_data_structures.heuristics.meta.game;

public enum CellState {
	COMPUTER("X"), USER("O"), EMPTY("-");

	private final String text;

	CellState(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
