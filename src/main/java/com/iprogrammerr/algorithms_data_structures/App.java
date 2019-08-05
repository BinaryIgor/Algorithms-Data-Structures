package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.graph.as.Anode;
import com.iprogrammerr.algorithms_data_structures.graph.as.AstarAlgorithm;

public class App {

    public static void main(String[] args) {
        AstarAlgorithm astar = new AstarAlgorithm(10, 5);
        for (Anode n : astar.path(new Anode(0, 0), new Anode(9, 4))) {
            System.out.println(n);
        }
    }
}
