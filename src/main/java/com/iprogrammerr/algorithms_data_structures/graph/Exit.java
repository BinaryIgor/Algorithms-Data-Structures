package com.iprogrammerr.algorithms_data_structures.graph;

import java.awt.Point;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public final class Exit {

	private final Maze maze;
	private final Potential<Point> value;

	public Exit(Maze maze) {
		this.maze = maze;
		this.value = new Potential<>();
	}

	public Point value() {
		dfs(this.maze.start().x, this.maze.start().y,
				new boolean[this.maze.value().size()][this.maze.value().get(0).size()]);
		if (this.value.hasValue()) {
			return this.value.value();
		}
		throw new RuntimeException("There is no exit in a given maze");
	}

	private void dfs(int row, int col, boolean[][] visited) {
		if (this.maze.value().get(row).get(col) == Maze.END) {
			this.value.revalue(new Point(row, col));
		} else if (!skip(row, col, visited)) {
			visited[row][col] = true;
			dfs(row + 1, col, visited);
			dfs(row, col + 1, visited);
			dfs(row, col - 1, visited);
			dfs(row - 1, col, visited);
		}
	}

	private boolean skip(int row, int col, boolean[][] visited) {
		int end = this.maze.value().size() - 1;
		return visited[row][col] || (row < 0 || row > end) || (col < 0 || col > end)
				|| this.maze.value().get(row).get(col) == Maze.WALL;
	}
}
