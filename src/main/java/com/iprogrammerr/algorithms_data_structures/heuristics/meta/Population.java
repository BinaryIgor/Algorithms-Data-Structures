package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class Population {

	private final Initialization<Individual[]> individuals;
	private final int[] adapted;

	public Population(Random random, int[] adapted, int size) {
		this.individuals = new StickyInitialization<>(() -> {
			Individual[] individuals = new Individual[size];
			for (int i = 0; i < individuals.length; ++i) {
				individuals[i] = new Individual(random, adapted);
			}
			return individuals;
		});
		this.adapted = adapted;
	}

	public Population(Random random, int[] adapted) {
		this(random, adapted, 100);
	}

	public Individual individual(int idx) {
		return this.individuals.value()[idx];
	}

	public void save(int idx, Individual individual) {
		this.individuals.value()[idx] = individual;
	}

	public Individual fittest() {
		Individual fittest = this.individuals.value()[0];
		for (int i = 1; i < this.individuals.value().length; ++i) {
			if (this.individuals.value()[i].fitness() >= fittest.fitness()) {
				fittest = this.individuals.value()[i];
			}
		}
		return fittest;
	}

	public int size() {
		return this.individuals.value().length;
	}

	public int[] adapted() {
		return this.adapted;
	}
}
