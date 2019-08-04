package com.iprogrammerr.algorithms_data_structures.graph;

public final class MazeExit {

    private final Maze maze;
    private Maze.Coordinates value;

    public MazeExit(Maze maze) {
        this.maze = maze;
    }

    public Maze.Coordinates find() {
        dfs(this.maze.start().row, this.maze.start().column,
            new boolean[this.maze.value().size()][this.maze.value().get(0).size()]);
        if (this.value != null) {
            return this.value;
        }
        throw new RuntimeException("There is no exit in a given maze");
    }

    private void dfs(int row, int col, boolean[][] visited) {
        if (this.maze.value().get(row).get(col) == Maze.END) {
            this.value = new Maze.Coordinates(row, col);
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
        return (row < 0 || row > end) ||
            (col < 0 || col > end) ||
            visited[row][col] ||
            this.maze.value().get(row).get(col) == Maze.WALL;
    }
}
