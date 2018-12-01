package com.iprogrammerr.algorithms_data_structures.sort;

import com.iprogrammerr.algorithms_data_structures.PerformanceTest;

public final class SortingAlgorithmTest<T extends Comparable<T>> implements PerformanceTest<SortingAlgorithm<T>> {

	private final int attempts;

	public SortingAlgorithmTest(int attempts) {
		this.attempts = attempts;
	}

	@Override
	public String result(SortingAlgorithm<T> tested) {
		long start = System.nanoTime();
		for (int i = 0; i < this.attempts; ++i) {
			tested.sorted();
		}
		long end = System.nanoTime();
		return verboseResult(tested.getClass().getSimpleName(), (end - start) / this.attempts);
	}

	private String verboseResult(String algorithm, long time) {
		String format = "average of %d sorts using %s took %.2f %s";
		String verbose;
		if (time > 1_000_000_000) {
			verbose = String.format(format, this.attempts, algorithm, (time / 1_000_000_000d), "s");
		} else if (time > 1_000_000) {
			verbose = String.format(format, this.attempts, algorithm, (time / 1_000_000d), "ms");
		} else if (time > 1_000) {
			verbose = String.format(format, this.attempts, algorithm, (time / 1_000d), "us");
		} else {
			verbose = String.format(format, this.attempts, algorithm, time, "ns");
		}
		return verbose;
	}
}
