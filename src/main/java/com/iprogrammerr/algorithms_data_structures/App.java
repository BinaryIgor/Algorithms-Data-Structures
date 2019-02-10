package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.graph.as.Anode;
import com.iprogrammerr.algorithms_data_structures.graph.as.AstarAlgorithm;

public class App {

	public static void main(String[] args) throws Exception {
		AstarAlgorithm algorithm = new AstarAlgorithm();
		for (Anode node : algorithm.path()) {
			System.out.println(node);
		}
	}
}
