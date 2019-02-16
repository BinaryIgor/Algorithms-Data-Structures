package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

public class Particle {

	public final double[] position;
	public final double[] velocity;
	private final double[] bestPosition;

	public Particle(double[] position, double[] velocity) {
		this.position = position;
		this.velocity = velocity;
		this.bestPosition = new double[position.length];
	}

	public double[] bestPosition() {
		return this.bestPosition;
	}

	public void setBestPosition(double[] bestPosition) {
		for (int i = 0; i < this.bestPosition.length; ++i) {
			this.bestPosition[i] = bestPosition[i];
		}
	}

	@Override
	public String toString() {
		return String.format("Best position so far %.3f, %.3f", this.bestPosition[0], this.bestPosition[1]);
	}
}
