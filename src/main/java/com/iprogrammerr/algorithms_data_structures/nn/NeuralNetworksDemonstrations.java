package com.iprogrammerr.algorithms_data_structures.nn;

import java.util.Random;

public final class NeuralNetworksDemonstrations {

	public static void and() {
		logicalProblem(true);
	}

	private static void logicalProblem(boolean and) {
		double[][] input = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		double[] output = and ? new double[] { 0, 0, 0, 1 } : new double[] { 0, 1, 1, 1 };
		Perceptron perceptron = new Perceptron(0.3, input, output);
		perceptron.train();
		System.out.println("Predictions: ");
		System.out.println(String.format("For 0, 0 = %.1f", perceptron.output(0, 0)));
		System.out.println(String.format("For 0, 1 = %.1f", perceptron.output(0, 1)));
		System.out.println(String.format("For 1, 0 = %.1f", perceptron.output(1, 0)));
		System.out.println(String.format("For 1, 1 = %.1f", perceptron.output(1, 1)));
	}

	public static void or() {
		logicalProblem(false);
	}

	public static void and(int iterations) {
		multiLayerLogical(iterations, new double[][] { { 0 }, { 0 }, { 0 }, { 1 } });
	}

	private static void multiLayerLogical(int iterations, double[][] results) {
		double[][] training = new double[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		BackpropagationNeuralNetwork network = new BackpropagationNeuralNetwork(2, 3, 1, new Random());
		for (int i = 0; i < iterations; ++i) {
			for (int j = 0; j < results.length; ++j) {
				network.train(training[j], results[j]);
			}
			System.out.println();
			for (int j = 0; j < results.length; ++j) {
				double[] t = training[j];
				System.out.println(String.format("Num of iterations: %d", i + 1));
				System.out.println(String.format("%.1f, %.1f --> %.3f", t[0], t[1], network.run(t)[0]));
			}
		}
	}

	public static void or(int iterations) {
		multiLayerLogical(iterations, new double[][] { { 0 }, { 1 }, { 1 }, { 1 } });
	}

	public static void xor(int iterations) {
		multiLayerLogical(iterations, new double[][] { { 0 }, { 1 }, { 1 }, { 0 } });
	}
}
