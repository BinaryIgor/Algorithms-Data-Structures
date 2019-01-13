package com.iprogrammerr.algorithms_data_structures;

import java.util.Arrays;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.graph.mf.Edge;
import com.iprogrammerr.algorithms_data_structures.graph.mf.FlowNetwork;
import com.iprogrammerr.algorithms_data_structures.graph.mf.FordFulkerson;
import com.iprogrammerr.algorithms_data_structures.graph.mf.Vertex;

public class App {

	public static void main(String[] args) throws Exception {
		FlowNetwork network = new FlowNetwork();
		Vertex v0 = new Vertex("s");
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("t");

		List<Vertex> vertices = Arrays.asList(v0, v1, v2, v3);

		network.addEdge(new Edge(v0, v1, 4));
		network.addEdge(new Edge(v0, v2, 5));
		network.addEdge(new Edge(v1, v3, 7));
		network.addEdge(new Edge(v2, v1, 4));
		network.addEdge(new Edge(v2, v3, 1));

		FordFulkerson fulkerson = new FordFulkerson(network, v0, v3);
		System.out.println("Maximum flow is = " + fulkerson.value());
		System.out.println("\nVertices in the min cut set: ");
		vertices.forEach(v -> {
			if (fulkerson.isInCut(v)) {
				System.out.println(v);
			}
		});
	}
}
