package com.iprogrammerr.algorithms_data_structures.graph;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public class MazeFile implements Maze {

	private final Initialization<Maze.Coordinates> start;
	private final Initialization<List<List<Integer>>> map;

	public MazeFile(String path) {
		this.map = new StickyInitialization<>(() -> {
			List<List<Integer>> map = new ArrayList<>();
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
				int rows = -1;
				String line = reader.readLine();
				while (line != null) {
					String[] ints = line.split(" ");
					if (rows == -1) {
						rows = ints.length;
					} else if (rows != ints.length) {
						throw new RuntimeException(String.format("Unexpected number of rows, expected: %d, actual: %d",
								rows, ints.length));
					}
					List<Integer> cols = new ArrayList<>();
					for (String i : ints) {
						cols.add(Integer.parseInt(i.trim()));
					}
					map.add(cols);
					line = reader.readLine();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return map;
		});
		this.start = new StickyInitialization<>(() -> {
			for (int i = 0; i < this.map.value().size(); ++i) {
				for (int j = 0; j < this.map.value().get(i).size(); ++j) {
					if (this.map.value().get(i).get(j) == Maze.START) {
						return new Maze.Coordinates(i, j);
					}
				}
			}
			return new Maze.Coordinates(-1, -1);
		});
	}

	@Override
	public List<List<Integer>> value() {
		return this.map.value();
	}

	@Override
	public Maze.Coordinates start() {
		return this.start.value();
	}
}
