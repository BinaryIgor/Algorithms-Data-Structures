package com.iprogrammerr.algorithms_data_structures.heuristics.meta.game;

public final class Cell {

	public final int x;
	public final int y;
	private int miniMax;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setMiniMax(int miniMax) {
		this.miniMax = miniMax;
	}

	public int miniMax() {
		return this.miniMax;
	}

	@Override
	public String toString() {
		return new StringBuilder("(").append(this.x).append(",").append(this.y).append(")").toString();
	}
}
