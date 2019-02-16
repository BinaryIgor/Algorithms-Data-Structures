package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import java.util.Arrays;
import java.util.Random;

public final class ParticleSwarmOptimization {

	private static final int DIMENSIONS = 2;
	private static final int MAX_ITERATIONS = 500;
	private static final int PARTICLES = 10;
	private static final double MIN = -2;
	private static final double MAX = 2;
	private static final double w = 0.729;
	private static final double c1 = 1.49;
	private static final double c2 = 1.49;
	private final Random random;
	private double[] globalBests;
	private Particle[] swarm;

	public ParticleSwarmOptimization(Random random) {
		this.random = random;
		this.globalBests = new double[DIMENSIONS];
		this.swarm = new Particle[PARTICLES];
		generateRandomSolution();
	}

	private void generateRandomSolution() {
		for (int i = 0; i < DIMENSIONS; ++i) {
			this.globalBests[i] = random(MIN, MAX);
		}
	}

	public double[] solution() {
		for (int i = 0; i < this.swarm.length; ++i) {
			this.swarm[i] = new Particle(randomLocation(), randomVelocity());
		}
		int epochs = 0;
		while (epochs < MAX_ITERATIONS) {
			for (Particle p : this.swarm) {
				for (int i = 0; i < p.velocity.length; ++i) {
					double rp = this.random.nextDouble();
					double rg = this.random.nextDouble();
					p.velocity[i] = w * p.velocity[i] + c1 * rp * (p.bestPosition()[i] - p.position[i])
							+ c2 * rg * (this.globalBests[i] - p.position[i]);
				}
				for (int i = 0; i < p.position.length; ++i) {
					p.position[i] += p.velocity[i];
					if (p.position[i] < MIN) {
						p.position[i] = MIN;
					} else if (p.position[i] > MAX) {
						p.position[i] = MAX;
					}
				}
				if (f(p.position) > f(p.bestPosition())) {
					p.setBestPosition(p.position);
				}
				if (f(p.bestPosition()) > f(this.globalBests)) {
					this.globalBests = Arrays.copyOf(p.bestPosition(), p.bestPosition().length);
				}
			}
			++epochs;
		}
		return Arrays.copyOf(this.globalBests, this.globalBests.length);
	}

	private double random(double min, double max) {
		return min + (this.random.nextDouble() * (max - min));
	}

	private double[] randomLocation() {
		return new double[] { random(MIN, MAX), random(MIN, MAX) };
	}

	private double[] randomVelocity() {
		return new double[] { random(-MAX - MIN, MAX - MIN), random(-MAX - MIN, MAX - MIN) };
	}

	public double f(double... args) {
		return Math.exp(-args[0] * args[0] - args[1] * args[1]) * Math.sin(args[0]);
	}
}
