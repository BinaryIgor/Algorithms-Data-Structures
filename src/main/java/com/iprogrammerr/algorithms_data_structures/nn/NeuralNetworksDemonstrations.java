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

	public static void irisClassification(int iterations) {
		double[][] training = new double[][] { new double[] { 5.1f, 3.5f, 1.4f, 0.2f },
				new double[] { 4.9f, 3.0f, 1.4f, 0.2f }, new double[] { 4.7f, 3.2f, 1.3f, 0.2f },
				new double[] { 4.6f, 3.1f, 1.5f, 0.2f }, new double[] { 5.0f, 3.6f, 1.4f, 0.2f },
				new double[] { 5.4f, 3.9f, 1.7f, 0.4f }, new double[] { 4.6f, 3.4f, 1.4f, 0.3f },
				new double[] { 5.0f, 3.4f, 1.5f, 0.2f }, new double[] { 4.4f, 2.9f, 1.4f, 0.2f },
				new double[] { 4.9f, 3.1f, 1.5f, 0.1f }, new double[] { 5.4f, 3.7f, 1.5f, 0.2f },
				new double[] { 4.8f, 3.4f, 1.6f, 0.2f }, new double[] { 4.8f, 3.0f, 1.4f, 0.1f },
				new double[] { 4.3f, 3.0f, 1.1f, 0.1f }, new double[] { 5.8f, 4.0f, 1.2f, 0.2f },
				new double[] { 5.7f, 4.4f, 1.5f, 0.4f }, new double[] { 5.4f, 3.9f, 1.3f, 0.4f },
				new double[] { 5.1f, 3.5f, 1.4f, 0.3f }, new double[] { 5.7f, 3.8f, 1.7f, 0.3f },
				new double[] { 5.1f, 3.8f, 1.5f, 0.3f }, new double[] { 5.4f, 3.4f, 1.7f, 0.2f },
				new double[] { 5.1f, 3.7f, 1.5f, 0.4f }, new double[] { 4.6f, 3.6f, 1.0f, 0.2f },
				new double[] { 5.1f, 3.3f, 1.7f, 0.5f }, new double[] { 4.8f, 3.4f, 1.9f, 0.2f },
				new double[] { 5.0f, 3.0f, 1.6f, 0.2f }, new double[] { 5.0f, 3.4f, 1.6f, 0.4f },
				new double[] { 5.2f, 3.5f, 1.5f, 0.2f }, new double[] { 5.2f, 3.4f, 1.4f, 0.2f },
				new double[] { 4.7f, 3.2f, 1.6f, 0.2f }, new double[] { 4.8f, 3.1f, 1.6f, 0.2f },
				new double[] { 5.4f, 3.4f, 1.5f, 0.4f }, new double[] { 5.2f, 4.1f, 1.5f, 0.1f },
				new double[] { 5.5f, 4.2f, 1.4f, 0.2f }, new double[] { 4.9f, 3.1f, 1.5f, 0.1f },
				new double[] { 5.0f, 3.2f, 1.2f, 0.2f }, new double[] { 5.5f, 3.5f, 1.3f, 0.2f },
				new double[] { 4.9f, 3.1f, 1.5f, 0.1f }, new double[] { 4.4f, 3.0f, 1.3f, 0.2f },
				new double[] { 5.1f, 3.4f, 1.5f, 0.2f }, new double[] { 5.0f, 3.5f, 1.3f, 0.3f },
				new double[] { 4.5f, 2.3f, 1.3f, 0.3f }, new double[] { 4.4f, 3.2f, 1.3f, 0.2f },
				new double[] { 5.0f, 3.5f, 1.6f, 0.6f }, new double[] { 5.1f, 3.8f, 1.9f, 0.4f },
				new double[] { 4.8f, 3.0f, 1.4f, 0.3f }, new double[] { 5.1f, 3.8f, 1.6f, 0.2f },
				new double[] { 4.6f, 3.2f, 1.4f, 0.2f }, new double[] { 5.3f, 3.7f, 1.5f, 0.2f },
				new double[] { 5.0f, 3.3f, 1.4f, 0.2f }, new double[] { 7.0f, 3.2f, 4.7f, 1.4f },
				new double[] { 6.4f, 3.2f, 4.5f, 1.5f }, new double[] { 6.9f, 3.1f, 4.9f, 1.5f },
				new double[] { 5.5f, 2.3f, 4.0f, 1.3f }, new double[] { 6.5f, 2.8f, 4.6f, 1.5f },
				new double[] { 5.7f, 2.8f, 4.5f, 1.3f }, new double[] { 6.3f, 3.3f, 4.7f, 1.6f },
				new double[] { 4.9f, 2.4f, 3.3f, 1.0f }, new double[] { 6.6f, 2.9f, 4.6f, 1.3f },
				new double[] { 5.2f, 2.7f, 3.9f, 1.4f }, new double[] { 5.0f, 2.0f, 3.5f, 1.0f },
				new double[] { 5.9f, 3.0f, 4.2f, 1.5f }, new double[] { 6.0f, 2.2f, 4.0f, 1.0f },
				new double[] { 6.1f, 2.9f, 4.7f, 1.4f }, new double[] { 5.6f, 2.9f, 3.6f, 1.3f },
				new double[] { 6.7f, 3.1f, 4.4f, 1.4f }, new double[] { 5.6f, 3.0f, 4.5f, 1.5f },
				new double[] { 5.8f, 2.7f, 4.1f, 1.0f }, new double[] { 6.2f, 2.2f, 4.5f, 1.5f },
				new double[] { 5.6f, 2.5f, 3.9f, 1.1f }, new double[] { 5.9f, 3.2f, 4.8f, 1.8f },
				new double[] { 6.1f, 2.8f, 4.0f, 1.3f }, new double[] { 6.3f, 2.5f, 4.9f, 1.5f },
				new double[] { 6.1f, 2.8f, 4.7f, 1.2f }, new double[] { 6.4f, 2.9f, 4.3f, 1.3f },
				new double[] { 6.6f, 3.0f, 4.4f, 1.4f }, new double[] { 6.8f, 2.8f, 4.8f, 1.4f },
				new double[] { 6.7f, 3.0f, 5.0f, 1.7f }, new double[] { 6.0f, 2.9f, 4.5f, 1.5f },
				new double[] { 5.7f, 2.6f, 3.5f, 1.0f }, new double[] { 5.5f, 2.4f, 3.8f, 1.1f },
				new double[] { 5.5f, 2.4f, 3.7f, 1.0f }, new double[] { 5.8f, 2.7f, 3.9f, 1.2f },
				new double[] { 6.0f, 2.7f, 5.1f, 1.6f }, new double[] { 5.4f, 3.0f, 4.5f, 1.5f },
				new double[] { 6.0f, 3.4f, 4.5f, 1.6f }, new double[] { 6.7f, 3.1f, 4.7f, 1.5f },
				new double[] { 6.3f, 2.3f, 4.4f, 1.3f }, new double[] { 5.6f, 3.0f, 4.1f, 1.3f },
				new double[] { 5.5f, 2.5f, 4.0f, 1.3f }, new double[] { 5.5f, 2.6f, 4.4f, 1.2f },
				new double[] { 6.1f, 3.0f, 4.6f, 1.4f }, new double[] { 5.8f, 2.6f, 4.0f, 1.2f },
				new double[] { 5.0f, 2.3f, 3.3f, 1.0f }, new double[] { 5.6f, 2.7f, 4.2f, 1.3f },
				new double[] { 5.7f, 3.0f, 4.2f, 1.2f }, new double[] { 5.7f, 2.9f, 4.2f, 1.3f },
				new double[] { 6.2f, 2.9f, 4.3f, 1.3f }, new double[] { 5.1f, 2.5f, 3.0f, 1.1f },
				new double[] { 5.7f, 2.8f, 4.1f, 1.3f }, new double[] { 6.3f, 3.3f, 6.0f, 2.5f },
				new double[] { 5.8f, 2.7f, 5.1f, 1.9f }, new double[] { 7.1f, 3.0f, 5.9f, 2.1f },
				new double[] { 6.3f, 2.9f, 5.6f, 1.8f }, new double[] { 6.5f, 3.0f, 5.8f, 2.2f },
				new double[] { 7.6f, 3.0f, 6.6f, 2.1f }, new double[] { 4.9f, 2.5f, 4.5f, 1.7f },
				new double[] { 7.3f, 2.9f, 6.3f, 1.8f }, new double[] { 6.7f, 2.5f, 5.8f, 1.8f },
				new double[] { 7.2f, 3.6f, 6.1f, 2.5f }, new double[] { 6.5f, 3.2f, 5.1f, 2.0f },
				new double[] { 6.4f, 2.7f, 5.3f, 1.9f }, new double[] { 6.8f, 3.0f, 5.5f, 2.1f },
				new double[] { 5.7f, 2.5f, 5.0f, 2.0f }, new double[] { 5.8f, 2.8f, 5.1f, 2.4f },
				new double[] { 6.4f, 3.2f, 5.3f, 2.3f }, new double[] { 6.5f, 3.0f, 5.5f, 1.8f },
				new double[] { 7.7f, 3.8f, 6.7f, 2.2f }, new double[] { 7.7f, 2.6f, 6.9f, 2.3f },
				new double[] { 6.0f, 2.2f, 5.0f, 1.5f }, new double[] { 6.9f, 3.2f, 5.7f, 2.3f },
				new double[] { 5.6f, 2.8f, 4.9f, 2.0f }, new double[] { 7.7f, 2.8f, 6.7f, 2.0f },
				new double[] { 6.3f, 2.7f, 4.9f, 1.8f }, new double[] { 6.7f, 3.3f, 5.7f, 2.1f },
				new double[] { 7.2f, 3.2f, 6.0f, 1.8f }, new double[] { 6.2f, 2.8f, 4.8f, 1.8f },
				new double[] { 6.1f, 3.0f, 4.9f, 1.8f }, new double[] { 6.4f, 2.8f, 5.6f, 2.1f },
				new double[] { 7.2f, 3.0f, 5.8f, 1.6f }, new double[] { 7.4f, 2.8f, 6.1f, 1.9f },
				new double[] { 7.9f, 3.8f, 6.4f, 2.0f }, new double[] { 6.4f, 2.8f, 5.6f, 2.2f },
				new double[] { 6.3f, 2.8f, 5.1f, 1.5f }, new double[] { 6.1f, 2.6f, 5.6f, 1.4f },
				new double[] { 7.7f, 3.0f, 6.1f, 2.3f }, new double[] { 6.3f, 3.4f, 5.6f, 2.4f },
				new double[] { 6.4f, 3.1f, 5.5f, 1.8f }, new double[] { 6.0f, 3.0f, 4.8f, 1.8f },
				new double[] { 6.9f, 3.1f, 5.4f, 2.1f }, new double[] { 6.7f, 3.1f, 5.6f, 2.4f },
				new double[] { 6.9f, 3.1f, 5.1f, 2.3f }, new double[] { 5.8f, 2.7f, 5.1f, 1.9f },
				new double[] { 6.8f, 3.2f, 5.9f, 2.3f }, new double[] { 6.7f, 3.3f, 5.7f, 2.5f },
				new double[] { 6.7f, 3.0f, 5.2f, 2.3f }, new double[] { 6.3f, 2.5f, 5.0f, 1.9f },
				new double[] { 6.5f, 3.0f, 5.2f, 2.0f }, new double[] { 6.2f, 3.4f, 5.4f, 2.3f },
				new double[] { 5.9f, 3.0f, 5.1f, 1.8f } };

		double[][] results = new double[][] { new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f }, new double[] { 1f, 0f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f },
				new double[] { 0f, 1f, 0f }, new double[] { 0f, 1f, 0f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f }, new double[] { 0f, 0f, 1f },
				new double[] { 0f, 0f, 1f } };
		BackpropagationNeuralNetwork network = new BackpropagationNeuralNetwork(4, 6, 3, new Random());
		int step = printStep(iterations);
		for (int i = 0; i < iterations; i++) {
			for (int j = 0; j < results.length; j++) {
				network.train(training[j], results[j]);
			}
			if ((i + 1) % step == 0) {
				System.out.println("Iteration #" + iterations + "\n");
				for (int j = 0; j < results.length; j++) {
					double[] data = training[j];
					double[] calculatedOutput = network.run(data);
					System.out.println(String.format("%.3f, %.3f, %.3f -->  %d, %d, %d", data[0], data[1], data[2],
							Math.round(calculatedOutput[0]), Math.round(calculatedOutput[1]),
							Math.round(calculatedOutput[2])));
				}
			}
		}
	}

	public static void ocr(int iterations) {
		double[][] training = new Numbers().value();
		double[][] results = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 } };
		BackpropagationNeuralNetwork network = new BackpropagationNeuralNetwork(training[0].length, 15, results.length,
				new Random());
		int step = printStep(iterations);
		for (int i = 0; i < iterations; i++) {
			for (int j = 0; j < results.length; j++) {
				network.train(training[j], results[j]);
			}
			if ((i + 1) % step == 0) {
				System.out.println(String.format("%sIteration #%d", System.lineSeparator(), (i + 1)));
				for (int j = 0; j < results.length; j++) {
					double[] result = network.run(training[j]);
					for (int k = 0; k < result.length; k++) {
						System.out.print(Math.round(result[k]));
					}
					System.out.println();
				}
			}
		}
	}

	private static int printStep(int iterations) {
		if (iterations <= 10) {
			return 1;
		}
		if (iterations <= 100) {
			return 10;
		}
		if (iterations <= 1000) {
			return 100;
		}
		return 1000;
	}
}
