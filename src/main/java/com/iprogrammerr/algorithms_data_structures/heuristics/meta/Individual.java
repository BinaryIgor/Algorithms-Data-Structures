package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class Individual {

	private final Random random;
	private final Initialization<int[]> genes;
	private final int[] adapted;
	private int fitness;

	public Individual(Random random, int[] adapted) {
		this.random = random;
		this.genes = new StickyInitialization<>(() -> {
			int[] genes = new int[adapted.length];
			for (int i = 0; i < genes.length; ++i) {
				genes[i] = this.random.nextInt(genes.length);
			}
			return genes;
		});
		this.adapted = adapted;
		this.fitness = -1;
	}

	public int fitness() {
		if (this.fitness < 0) {
			this.fitness = 0;
			for (int i = 0; i < this.genes.value().length; ++i) {
				if (this.genes.value()[i] == this.adapted[i]) {
					++this.fitness;
				}
			}
		}
		return this.fitness;
	}

	public boolean isFittest() {
		return fitness() == this.genes.value().length;
	}

	public void mutate(int idx, int value) {
		this.genes.value()[idx] = value;
		this.fitness = -1;
	}

	public int gene(int idx) {
		return this.genes.value()[idx];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.genes.value().length; ++i) {
			builder.append("|").append(gene(i));
		}
		return builder.append("|").toString();
	}
}
