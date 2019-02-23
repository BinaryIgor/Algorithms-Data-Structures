package com.iprogrammerr.algorithms_data_structures.nn;

import java.util.Arrays;
import java.util.Random;

public final class Layer {

	private final double[] input;
	private final double[] output;
	private final double[] weights;
	private final double[] dWeights;
	private final Random random;

	public Layer(int input, int output, Random random) {
		this.input = new double[input + 1];
		this.output = new double[output];
		this.weights = new double[(input + 1) * output];
		this.dWeights = new double[this.weights.length];
		this.random = random;
		initWeights();
	}

	private void initWeights() {
		for (int i = 0; i < this.weights.length; ++i) {
			this.weights[i] = (this.random.nextDouble() - 0.5) * 4; // (-2, 2)
		}
	}

	public double[] run(double[] input) {
		System.arraycopy(input, 0, this.input, 0, input.length);
		this.input[this.input.length - 1] = 1;
		int offset = 0;
		for (int i = 0; i < this.output.length; ++i) {
			for (int j = 0; j < this.input.length; ++j) {
				this.output[i] += this.weights[offset + j] * this.input[j];
			}
			this.output[i] = ActivationFunctions.sigmoid(this.output[i]);
			offset += this.input.length;
		}
		return Arrays.copyOf(this.output, this.output.length);
	}

	public double[] train(double[] error, double learningRate, double momentum) {
		int offset = 0;
		double[] nextError = new double[this.input.length];
		for (int i = 0; i < this.output.length; ++i) {
			double delta = error[i] * ActivationFunctions.dSigmoidOfSigmoid(this.output[i]);
			for (int j = 0; j < this.input.length; ++j) {
				int weight = offset + j;
				nextError[j] = nextError[j] + this.weights[weight] * delta;
				double dw = this.input[j] * delta * learningRate;
				this.weights[weight] += this.dWeights[weight] * momentum + dw;
				this.dWeights[weight] = dw;
			}
			offset += input.length;
		}
		return nextError;
	}
}
