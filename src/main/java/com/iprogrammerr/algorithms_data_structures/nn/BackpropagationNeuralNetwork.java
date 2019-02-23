package com.iprogrammerr.algorithms_data_structures.nn;

import java.util.Random;

public final class BackpropagationNeuralNetwork {

	private static final double LEARNING_RATE = 0.3;
	private static final double MOMENTUM = 0.6;
	private final Layer[] layers;

	public BackpropagationNeuralNetwork(int input, int hidden, int output, Random random) {
		this.layers = new Layer[2];
		this.layers[0] = new Layer(input, hidden, random);
		this.layers[1] = new Layer(hidden, output, random);
	}

	public Layer layer(int index) {
		return this.layers[index];
	}

	public double[] run(double[] input) {
		double[] activations = input;
		for (int i = 0; i < this.layers.length; ++i) {
			activations = this.layers[i].run(activations);
		}
		return activations;
	}

	public void train(double[] input, double[] output) {
		double[] calculatedOutput = run(input);
		double[] error = new double[calculatedOutput.length];
		for (int i = 0; i < error.length; ++i) {
			error[i] = output[i] - calculatedOutput[i];
		}
		for (int i = layers.length - 1; i >= 0; i--) {
			error = this.layers[i].train(error, LEARNING_RATE, MOMENTUM);
		}
	}
}
