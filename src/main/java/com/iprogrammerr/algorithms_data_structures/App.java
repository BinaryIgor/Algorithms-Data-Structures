package com.iprogrammerr.algorithms_data_structures;

import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.graph.ts.City;
import com.iprogrammerr.algorithms_data_structures.graph.ts.SimulatedAnnealing;
import com.iprogrammerr.algorithms_data_structures.graph.ts.Tour;

public class App {

	public static void main(String[] args) throws Exception {
		int size = 10_000;
		List<City> cities = new ArrayList<>(size);
		for (int i = 0; i < size; ++i) {
			cities.add(new City());
		}
		Tour solution = new SimulatedAnnealing(cities).solution();
		System.out.println(String.format("Found solution: %.3f", solution.distance()));
	}
}
