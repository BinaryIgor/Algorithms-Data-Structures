package com.iprogrammerr.algorithms_data_structures.graph.ts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class SimulatedAnnealing {

	private final List<City> source;
	private final Random random;
	private final double temperature;
	private final double cooling;

	public SimulatedAnnealing(List<City> source, Random random, double temperature, double cooling) {
		this.source = source;
		this.random = random;
		this.temperature = temperature;
		this.cooling = cooling;
	}

	public SimulatedAnnealing(List<City> source, Random random) {
		this(source, random, 10_000, 0.003);
	}

	public SimulatedAnnealing(List<City> source) {
		this(source, new Random());
	}

	public Tour solution() {
		Tour best = random();
		System.out.println(String.format("Initial solution -> %.3f", best.distance()));
		Tour current = new Tour(best.value());
		double temp = this.temperature;
		while (temp > 1) {
			Tour solution = new Tour(current.value());
			int size = solution.value().size();
			solution.swap(this.random.nextInt(size), this.random.nextInt(size));
			if (acceptancePropability(current.distance(), solution.distance(), temp) > this.random.nextDouble()) {
				current = solution;
			}
			if (current.distance() < best.distance()) {
				best = new Tour(current.value());
			}
			temp = temp * (1 - this.cooling);
		}
		return best;
	}

	private Tour random() {
		List<City> cities = new ArrayList<>(this.source);
		Collections.shuffle(cities);
		return new Tour(cities);
	}

	private double acceptancePropability(double currentEnergy, double newEnergy, double temperature) {
		double prop;
		if (currentEnergy > newEnergy) {
			prop = 1;
		} else {
			prop = Math.exp((currentEnergy - newEnergy) / temperature);
		}
		return prop;
	}

}
