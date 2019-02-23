package com.iprogrammerr.algorithms_data_structures.nn;

public interface Training {

	double[][] input();

	double[] output();

	class Or implements Training {

		private static final double[][] INPUT = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		private static final double[] OUTPUT = { 0, 1, 1, 1 };

		@Override
		public double[][] input() {
			return INPUT;
		}

		@Override
		public double[] output() {
			return OUTPUT;
		}
	}

	class And implements Training {

		private static final double[][] INPUT = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		private static final double[] OUTPUT = { 0, 0, 0, 1 };

		@Override
		public double[][] input() {
			return INPUT;
		}

		@Override
		public double[] output() {
			return OUTPUT;
		}
	}

	class Xor implements Training {

		private static final double[][] INPUT = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
		private static final double[] OUTPUT = { 0, 1, 1, 0 };

		@Override
		public double[][] input() {
			return INPUT;
		}

		@Override
		public double[] output() {
			return OUTPUT;
		}
	}
}
