package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

import java.util.Arrays;
import java.util.Random;

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
        this.maxGene = new StickyInitialization<>(() -> Arrays.stream(this.adapted).max().getAsInt());
    }


    private Population evolved(Population population) {
        Population toEvolve = new Population(random, adapted, population.size());
        for (int i = 0; i < population.size(); ++i) {
            toEvolve.save(i, crossover(random(population), random(population)));
        }
        for (int i = 0; i < toEvolve.size(); ++i) {
            mutate(toEvolve.individual(i));
        }
        return toEvolve;
    }

    private Individual random(Population population) {
        Population newPopulation = new Population(random, adapted, randomPopulation);
        for (int i = 0; i < newPopulation.size(); ++i) {
            newPopulation.save(i, population.individual(random.nextInt(population.size())));
        }
        return newPopulation.fittest();
    }

    public Individual fittest(int generations) {
        Population evolved = base;
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
        System.out.println(String.format("Fittest(%d/%d) found in %d generation",
            fittest.fitness(), adapted.length, i));
        return fittest;
    }

    private Individual crossover(Individual first, Individual second) {
        Individual newIndividual = new Individual(random, adapted);
        for (int i = 0; i < adapted.length; ++i) {
            if (random.nextDouble() <= CROSSOVER_RATE) {
                newIndividual.mutate(i, first.gene(i));
            } else {
                newIndividual.mutate(i, second.gene(i));
            }
        }
        return newIndividual;
    }

    private void mutate(Individual individual) {
        for (int i = 0; i < adapted.length; ++i) {
            if (random.nextDouble() <= MUTATION_RATE) {
                individual.mutate(i, random.nextInt(maxGene.value()));
            }
        }
    }
}
