package com.iprogrammerr.algorithms_data_structures.graph.as;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public final class AstarAlgorithm {

    private static final int HORIZONTAL_VERTICAL_COST = 10;
    private static final int DIAGONAL_COST = 15;
    private final Anode[][] searchSpace;
    private final Set<Anode> closed;
    private final Queue<Anode> open;
    private final Initialization<Void> initialization;
    private Anode start;

    public AstarAlgorithm(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new RuntimeException("Rows and columns have to be a positive numbers");
        }
        this.searchSpace = new Anode[rows][cols];
        this.closed = new HashSet<>();
        this.open = new PriorityQueue<>((f, s) -> {
            if (f.f() > s.f()) {
                return 1;
            } else if (f.f() < s.f()) {
                return -1;
            }
            return 0;
        });
        this.initialization = new StickyInitialization<>(() -> initialization(rows, cols));
    }

    public AstarAlgorithm() {
        this(10, 15);
    }

    private Void initialization(int rows, int cols) {
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                this.searchSpace[i][j] = new Anode(i, j);
            }
        }
        Random random = new Random();
        for (int i = 0; i < rows; ++i) {
            int obstacles = 1 + random.nextInt(cols);
            for (int j = 0; j < obstacles; ++j) {
                if (random.nextBoolean()) {
                    this.searchSpace[i][j].setObstacle(true);
                }
            }
        }
        this.start = this.searchSpace[random.nextInt(rows)][random.nextInt(cols)];
        return null;
    }

    public List<Anode> path(Anode end) {
        this.initialization.value();
        validateEnd(end);
        System.out.println(String.format("start = %s, end = %s", this.start, end));

        this.start.setH(manhattanHeuristic(this.start, end));
        this.open.add(this.start);
        while (!this.open.isEmpty()) {
            Anode current = this.open.poll();
            if (current.equals(end)) {
                end.setPredecessor(current.predecessor());
                break;
            }
            this.closed.add(current);
            for (Anode n : neighbors(current, end)) {
                if (this.closed.contains(n)) {
                    continue;
                }
                this.open.add(n);
                n.setPredecessor(current);
            }
        }

        return fromEnd(end);
    }

    private void validateEnd(Anode end) {
        int maxRow = searchSpace.length;
        int maxCol = searchSpace[0].length;
        if (end.row() >= maxRow || end.col() >= maxCol) {
            throw new RuntimeException(String.format("End have to be in %d, %d bounds", maxRow, maxCol));
        }
    }

    private List<Anode> fromEnd(Anode end) {
        List<Anode> path = new ArrayList<>();
        Anode n = end;
        while (n != null) {
            path.add(n);
            n = n.predecessor();
        }
        Collections.reverse(path);
        return path;
    }

    private List<Anode> neighbors(Anode node, Anode end) {
        List<Anode> neighbors = new ArrayList<>();
        int row = node.row();
        int col = node.col();
        if (col - 1 >= 0 && !this.searchSpace[row][col - 1].isObstacle()) {
            neighbors.add(configuredNode(this.searchSpace[row][col - 1], end, false));
            if (row + 1 < this.searchSpace.length && !this.searchSpace[row + 1][col - 1].isObstacle()) {
                neighbors.add(configuredNode(this.searchSpace[row + 1][col - 1], end, true));
            }
        }
        if (row + 1 < this.searchSpace.length && !this.searchSpace[row + 1][col].isObstacle()) {
            neighbors.add(configuredNode(this.searchSpace[row + 1][col], end, false));
            if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row + 1][col + 1].isObstacle()) {
                neighbors.add(configuredNode(this.searchSpace[row + 1][col + 1], end, true));
            }
        }
        if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row][col + 1].isObstacle()) {
            neighbors.add(configuredNode(this.searchSpace[row][col + 1], end, false));
            if (row - 1 >= 0 && !this.searchSpace[row - 1][col + 1].isObstacle()) {
                neighbors.add(configuredNode(this.searchSpace[row - 1][col + 1], end, true));
            }
        }
        if (row - 1 >= 0 && !this.searchSpace[row - 1][col].isObstacle()) {
            neighbors.add(configuredNode(this.searchSpace[row - 1][col], end, false));
            if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row - 1][col + 1].isObstacle()) {
                neighbors.add(configuredNode(this.searchSpace[row - 1][col + 1], end, true));
            }
        }
        return neighbors;
    }

    private Anode configuredNode(Anode node, Anode end, boolean diagonal) {
        node.setG(node.g() + (diagonal ? DIAGONAL_COST : HORIZONTAL_VERTICAL_COST));
        node.setH(manhattanHeuristic(node, end));
        return node;
    }

    private int manhattanHeuristic(Anode first, Anode second) {
        return (Math.abs(first.row() - second.row()) + Math.abs(first.col() - second.col())) * 10;
    }
}
