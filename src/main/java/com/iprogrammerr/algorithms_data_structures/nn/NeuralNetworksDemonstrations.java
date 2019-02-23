package com.iprogrammerr.algorithms_data_structures.nn;

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
}
