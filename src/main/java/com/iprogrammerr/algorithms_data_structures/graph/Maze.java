package com.iprogrammerr.algorithms_data_structures.graph;

import java.util.List;
import java.util.Objects;

public interface Maze {

    int WAY = 0;
    int WALL = 1;
    int START = 2;
    int END = 3;

    Coordinates start();

    List<List<Integer>> value();

    class Coordinates {

        public final int row;
        public final int column;

        public Coordinates(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return row == that.row &&
                column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
