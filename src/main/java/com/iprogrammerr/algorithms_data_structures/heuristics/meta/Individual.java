package com.iprogrammerr.algorithms_data_structures.heuristics.meta;

import java.util.Random;

public class Individual {

    private final int[] genes;
    private final int[] adapted;
    private int fitness;

    public Individual(int[] genes, int[] adapted) {
        if (genes.length != adapted.length) {
            throw new RuntimeException(String.format("Genes %d aren't of the same size as adapted %d",
                genes.length, adapted.length));
        }
        this.genes = genes;
        this.adapted = adapted;
        this.fitness = -1;
    }

    public Individual(Random random, int[] adapted) {
        this(randomGenes(random, adapted), adapted);
    }

    private static int[] randomGenes(Random random, int[] adapted) {
        int[] genes = new int[adapted.length];
        for (int i = 0; i < genes.length; ++i) {
            genes[i] = random.nextInt(genes.length);
        }
        return genes;
    }

    public int fitness() {
        if (fitness < 0) {
            fitness = 0;
            for (int i = 0; i < genes.length; ++i) {
                if (genes[i] == adapted[i]) {
                    ++fitness;
                }
            }
        }
        return fitness;
    }

    public boolean isFittest() {
        return fitness() == genes.length;
    }

    public void mutate(int idx, int value) {
        genes[idx] = value;
        fitness = -1;
    }

    public int gene(int idx) {
        return genes[idx];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < genes.length; ++i) {
            builder.append("|").append(gene(i));
        }
        return builder.append("|").toString();
    }
}
