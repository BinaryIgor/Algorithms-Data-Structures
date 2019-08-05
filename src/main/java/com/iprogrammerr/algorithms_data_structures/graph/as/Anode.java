package com.iprogrammerr.algorithms_data_structures.graph.as;

import java.util.Objects;

public final class Anode {

    private final int row;
    private final int col;
    private Anode predecessor;
    private boolean obstacle;
    private int g;
    private int h;

    public Anode(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isObstacle() {
        return this.obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public int row() {
        return this.row;
    }

    public int col() {
        return this.col;
    }

    public Anode predecessor() {
        return this.predecessor;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int f() {
        return this.g + this.h;
    }

    public void setPredecessor(Anode predecessor) {
        this.predecessor = predecessor;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Anode) {
            Anode node = (Anode) other;
            return this.row == node.row && this.col == node.col;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return new StringBuilder("Anode, row:").append(this.row).append(", col:").append(this.col)
            .append(" Predecessor: ").append(predecessor == null ? "none" : "has").toString();
    }
}
