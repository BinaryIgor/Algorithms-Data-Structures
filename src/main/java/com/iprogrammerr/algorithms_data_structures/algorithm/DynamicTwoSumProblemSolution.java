package com.iprogrammerr.algorithms_data_structures.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iprogrammerr.algorithms_data_structures.model.Pair;

public class DynamicTwoSumProblemSolution implements TwoSumProblemSolution {

	private final Map<Integer, Integer> numbers;
	private final int goal;

	private DynamicTwoSumProblemSolution(Map<Integer, Integer> numbers, int goal) {
		this.numbers = numbers;
		this.goal = goal;
	}

	public DynamicTwoSumProblemSolution(int goal) {
		this(new HashMap<>(), goal);
	}

	@Override
	public List<Pair<Integer>> value(List<Integer> numbers) {
		List<Pair<Integer>> solutions = new ArrayList<>();
		for (int i = 0; i < numbers.size(); ++i) {
			int remainder = this.goal - numbers.get(i);
			if (this.numbers.containsKey(remainder)) {
				solutions.add(new Pair<>(remainder, numbers.get(i)));
			}
			this.numbers.put(numbers.get(i), i);
		}
		this.numbers.clear();
		return solutions;
	}
}
