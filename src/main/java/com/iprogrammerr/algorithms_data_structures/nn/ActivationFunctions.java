package com.iprogrammerr.algorithms_data_structures.nn;

public final class ActivationFunctions {

	public static double step(double activation) {
		if (activation >= 1) {
			return 1;
		}
		return 0;
	}

	public static double sigmoid(double activation) {
		return 1 / (1 + Math.exp(-activation));
	}

	public static double dSigmoidOfSigmoid(double activation) {
		return activation * (1 - activation);
	}
}
