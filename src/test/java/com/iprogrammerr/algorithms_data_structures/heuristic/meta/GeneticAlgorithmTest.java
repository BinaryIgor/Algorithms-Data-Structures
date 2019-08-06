package com.iprogrammerr.algorithms_data_structures.heuristic.meta;

import com.iprogrammerr.algorithms_data_structures.heuristics.meta.GeneticAlgorithm;
import com.iprogrammerr.algorithms_data_structures.heuristics.meta.Individual;
import com.iprogrammerr.algorithms_data_structures.heuristics.meta.Population;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Random;

public class GeneticAlgorithmTest {

    private static final int POPULATION_SIZE = 50;
    private static final int[] FITTEST = {0, 2, 1, 5, 4, 3, 6, 7, 8, 9, 12, 55, 100};
    private static final int[] LEAST_ADAPTED = {9, 8, 8, 7, 7, 0, 0, 5, 5, 4, 4, 0, 1};
    private static final int FIRST_GENERATIONS = 10;

    @Test
    public void findsFitter() {
        Individual leastAdapted = new Individual(LEAST_ADAPTED, FITTEST);
        MatcherAssert.assertThat(leastAdapted.fitness(), Matchers.equalTo(0));

        Random random = new Random();
        Population population = new Population(random, FITTEST, POPULATION_SIZE);
        GeneticAlgorithm algorithm = new GeneticAlgorithm(random, population);

        Individual solution = algorithm.fittest(FIRST_GENERATIONS);
        MatcherAssert.assertThat(solution.fitness(), Matchers.greaterThan(leastAdapted.fitness()));

        if (!solution.isFittest()) {
            Individual newSolution = algorithm.fittest(FIRST_GENERATIONS * FIRST_GENERATIONS);
            MatcherAssert.assertThat(newSolution.fitness(), Matchers.greaterThan(solution.fitness()));
        }
    }
}
