package com.iprogrammerr.algorithms_data_structures.graph;

import java.awt.Point;
import java.util.List;

public interface Maze {

	int WAY = 0;
	int WALL = 1;
	int START = 2;
	int END = 3;

	Point start();

	List<List<Integer>> value();
}
