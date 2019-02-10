package com.iprogrammerr.algorithms_data_structures;

import java.util.Arrays;
import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.search.StochasticSearch;

public class App {

	public static void main(String[] args) throws Exception {
		StochasticSearch<String> search = new StochasticSearch<>(new Random(), true, 2);
		String min = search.value(Arrays.asList("b", "ded", "a", "gz", "ji"));
		System.out.println(String.format("min = %s", min));
	}
}
