package com.iprogrammerr.algorithms_data_structures.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ReservoirSampling<T> implements ParametrizedAlgorithm<Iterable<T>, Integer> {

	private final List<T> base;
	private final Random random;

	public ReservoirSampling(List<T> base, Random random) {
		this.base = base;
		this.random = random;
	}

	public ReservoirSampling(List<T> base) {
		this(base, new Random());
	}

	@Override
	public Iterable<T> solution(Integer param) {
		if (param < 0 || param >= this.base.size()) {
			throw new RuntimeException(String.format(
					"Sample size must be greater than 0 and lesser than %d", this.base.size()));
		}
		List<T> reservoir = new ArrayList<>(param);
		for (int i = 0; i < param; ++i) {
			reservoir.add(this.base.get(i));
		}
		for (int i = param; i < this.base.size(); ++i) {
			int r = this.random.nextInt(i);
			if (r < param) {
				reservoir.set(r, this.base.get(i));
			}
		}
		return reservoir;
	}
}
