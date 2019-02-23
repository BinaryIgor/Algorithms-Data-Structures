package com.iprogrammerr.algorithms_data_structures.nn;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class Perceptron {

	private final ActivationFunction function;
	private final double learningRate;
	private final double[][] input;
	private final double[] output;
	private final Initialization<double[]> weights;

	public Perceptron(ActivationFunction function, double learningRate, double[][] input, double[] output) {
		this.function = function;
		this.learningRate = learningRate;
		this.input = input;
		this.output = output;
		this.weights = new StickyInitialization<>(() -> {
			double[] weights = new double[input[0].length];
			for (int i = 0; i < weights.length; ++i) {
				weights[i] = 0;
			}
			return weights;
		});
	}

	public Perceptron(double learningRate, double[][] input, double[] output) {
		this((a) -> {
			if (a >= 1) {
				return 1;
			}
			return 0;
		}, learningRate, input, output);
	}

	public Perceptron(double[][] input, double[] output) {
		this(0.1, input, output);
	}

	public void train() {
		double totalError = 1;
		while (totalError != 0) {
			totalError = 0;
			for (int i = 0; i < this.output.length; ++i) {
				double output = output(this.input[i]);
				double error = Math.abs(this.output[i] - output);
				totalError += error;
				for (int j = 0; j < this.weights.value().length; ++j) {
					this.weights.value()[j] = this.weights.value()[j] + this.learningRate * this.input[i][j] * error;
				}
			}
			System.out.println(String.format("Keep on training the network, error is %.3f", totalError));
		}
		System.out.print("Neural network is trained, it has weights:");
		for (int i = 0; i < this.weights.value().length; ++i) {
			System.out.print(" " + this.weights.value()[i]);
		}
		System.out.println();
	}

	public double output(double... input) {
		double sum = 0;
		for (int i = 0; i < input.length; ++i) {
			sum = sum + this.weights.value()[i] * input[i];
		}
		return this.function.value(sum);
	}
}
