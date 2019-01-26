package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.graph.CycleDetection;
import com.iprogrammerr.algorithms_data_structures.graph.VisitableVertex;

public class App {

	public static void main(String[] args) throws Exception {
		VisitableVertex<Integer> v1 = new VisitableVertex<>(1);
		VisitableVertex<Integer> v2 = new VisitableVertex<>(2);
		VisitableVertex<Integer> v3 = new VisitableVertex<>(3);
		VisitableVertex<Integer> v4 = new VisitableVertex<>(4);
		VisitableVertex<Integer> v5 = new VisitableVertex<>(5);
		VisitableVertex<Integer> v6 = new VisitableVertex<>(6);
		v1.addNeighbors(v2);
		v2.addNeighbors(v3, v4);
		v3.addNeighbors(v4, v5);
		v4.addNeighbors(v3, v6);
		new CycleDetection<>(v1).detect();
	}
}
