package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.graph.iddfs.Iddfs;
import com.iprogrammerr.algorithms_data_structures.graph.iddfs.IddfsNode;

public class App {

	public static void main(String[] args) throws Exception {
		IddfsNode v0 = new IddfsNode("A");
		IddfsNode v1 = new IddfsNode("B");
		IddfsNode v2 = new IddfsNode("C");
		IddfsNode v3 = new IddfsNode("D");
		IddfsNode v4 = new IddfsNode("E");

		v0.addNeighbors(v1, v2);
		v1.addNeighbors(v3);
		v3.addNeighbors(v4);
		Iddfs iddfs = new Iddfs(v0);
		iddfs.run(v4);
	}
}
