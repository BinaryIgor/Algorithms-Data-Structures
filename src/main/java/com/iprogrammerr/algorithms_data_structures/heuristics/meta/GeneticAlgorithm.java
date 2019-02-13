package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class GeneticAlgorithm {

	private static final double CROSSOVER_RATE = 0.5;
	private static final double MUTATION_RATE = CROSSOVER_RATE / 5;
	private final int randomPopulation;
	private final Random random;
	private final Population base;
	private final int[] adapted;
	private final Initialization<Integer> maxGene;

	public GeneticAlgorithm(Random random, Population base) {
		if (base.size() < 20) {
			this.randomPopulation = base.size() / 2;
		} else {
			this.randomPopulation = base.size() / 10;
		}
		this.random = random;
		this.base = base;
		this.adapted = this.base.adapted();
		this.maxGene = new StickyInitialization<>(() -> {
			int max = this.adapted[0];
			for (int i = 1; i < this.adapted.length; i++) {
				if (this.adapted[i] > max) {
					max = this.adapted[i];
				}
			}
			return max;
		});
	}

	private Population evolved(Population population) {
		Population toEvolve = new Population(this.random, this.adapted, population.size());
		for (int i = 0; i < population.size(); ++i) {
			toEvolve.save(i, crossovered(random(population), random(population)));
		}
		for (int i = 0; i < toEvolve.size(); ++i) {
			mutate(toEvolve.individual(i));
		}
		return toEvolve;
	}

	private Individual random(Population population) {
		Population newPopulation = new Population(this.random, this.adapted, this.randomPopulation);
		for (int i = 0; i < newPopulation.size(); ++i) {
			newPopulation.save(i, population.individual(this.random.nextInt(population.size())));
		}
		return newPopulation.fittest();
	}

	public Individual fittest(int generations) {
		Population evolved = this.base;
		Individual fittest = evolved.fittest();
		int i = 0;
		while (!fittest.isFittest() && i < generations) {
			evolved = evolved(evolved);
			Individual newFittest = evolved.fittest();
			if (newFittest.fitness() > fittest.fitness()) {
				fittest = newFittest;
			}
			++i;
		}
		System.out.println(String.format("Fittest found in %d generation", i));
		return fittest;
	}

	private Individual crossovered(Individual first, Individual second) {
		Individual newIndividual = new Individual(this.random, this.adapted);
		for (int i = 0; i < this.adapted.length; ++i) {
			if (this.random.nextDouble() <= CROSSOVER_RATE) {
				newIndividual.mutate(i, first.gene(i));
			} else {
				newIndividual.mutate(i, second.gene(i));
			}
		}
		return newIndividual;
	}

	private void mutate(Individual individual) {
		for (int i = 0; i < this.adapted.length; ++i) {
			if (this.random.nextDouble() <= MUTATION_RATE) {
				individual.mutate(i, this.random.nextInt(this.maxGene.value()));
			}
		}
	}
}
