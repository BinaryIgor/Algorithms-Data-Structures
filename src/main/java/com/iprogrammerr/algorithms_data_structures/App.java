package com.iprogrammerr.algorithms_data_structures;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.heuristics.meta.GeneticAlgorithm;
import com.iprogrammerr.algorithms_data_structures.heuristics.meta.Population;

public class App {

	public static void main(String[] args) throws Exception {
		Random random = new Random();
		GeneticAlgorithm algorithm = new GeneticAlgorithm(random,
				new Population(random, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 100));
		System.out.println(String.format("Fittest = %s", algorithm.fittest(1000)));
	}
}
