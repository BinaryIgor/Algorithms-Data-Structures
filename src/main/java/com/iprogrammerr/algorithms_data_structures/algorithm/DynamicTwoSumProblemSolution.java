package com.iprogrammerr.algorithms_data_structures.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iprogrammerr.algorithms_data_structures.model.Pair;

public class DynamicTwoSumProblemSolution implements TwoSumProblemSolution {

	private final Set<Integer> numbers;
	private final int goal;

	private DynamicTwoSumProblemSolution(Set<Integer> numbers, int goal) {
		this.numbers = numbers;
		this.goal = goal;
	}

	public DynamicTwoSumProblemSolution(int goal) {
		this(new HashSet<>(), goal);
	}

	@Override
	public List<Pair<Integer>> value(List<Integer> numbers) {
		List<Pair<Integer>> solutions = new ArrayList<>();
		for (int i = 0; i < numbers.size(); ++i) {
			int remainder = this.goal - numbers.get(i);
			if (this.numbers.contains(remainder)) {
				solutions.add(new Pair<>(remainder, numbers.get(i)));
			}
			this.numbers.add(numbers.get(i));
		}
		this.numbers.clear();
		return solutions;
	}
}
