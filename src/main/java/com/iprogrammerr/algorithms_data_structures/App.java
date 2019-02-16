package com.iprogrammerr.algorithms_data_structures;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.heuristics.meta.ParticleSwarmOptimization;

public class App {

	public static void main(String[] args) throws Exception {
		ParticleSwarmOptimization optimization = new ParticleSwarmOptimization(new Random());
		double[] solution = optimization.solution();
		System.out.println("Solution for PSO problem");
		System.out.println(String.format("Best solution x: %f, y: %f", solution[0], solution[1]));
		System.out.println(String.format("Value f(x, y) = %f", optimization.f(solution)));
	}
}
