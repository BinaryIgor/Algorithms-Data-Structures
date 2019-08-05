package com.iprogrammerr.algorithms_data_structures.graph.as;

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

    public AstarAlgorithm(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new RuntimeException("Rows and columns have to be a positive numbers");
        }
        searchSpace = new Anode[rows][cols];
        closed = new HashSet<>();
        open = new PriorityQueue<>((f, s) -> {
            if (f.f() > s.f()) {
                return 1;
            } else if (f.f() < s.f()) {
                return -1;
            }
            return 0;
        });
    }

    public AstarAlgorithm() {
        this(10, 15);
    }

    private void initSearchSpace(Anode start, Anode end) {
        Random random = new Random();
        int rows = searchSpace.length;
        int cols = searchSpace[0].length;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                searchSpace[i][j] = new Anode(i, j);
            }
        }
        for (int i = 0; i < rows; ++i) {
            int obstacles = random.nextInt(cols);
            for (int j = 0; j < obstacles; ++j) {
                Anode node = searchSpace[i][j];
                if (canAddObstacle(i, j) && !node.equals(start) && !node.equals(end)) {
                    searchSpace[i][j].setObstacle(true);
                }
            }
        }
    }

    private boolean canAddObstacle(int row, int col) {
        int upperRow = row - 1;
        int leftCol = col - 1;
        int rightCol = col + 1;
        int lowerRow = row + 1;
        return !isObstacle(upperRow, leftCol) && !isObstacle(upperRow, col) && !isObstacle(upperRow, rightCol)
            && !isObstacle(row, leftCol) && !isObstacle(row, rightCol)
            && !isObstacle(lowerRow, leftCol) && !isObstacle(lowerRow, col) && !isObstacle(lowerRow, rightCol);
    }

    private boolean isObstacle(int row, int col) {
        return row < 0 || row >= searchSpace.length ||
            col < 0 || col >= searchSpace[0].length ||
            searchSpace[row][col].isObstacle();
    }

    public List<Anode> path(Anode start, Anode end) {
        validateStart(start);
        validateEnd(end);
        initSearchSpace(start, end);
        System.out.println(String.format("start = %s, end = %s", start, end));

        open.clear();
        closed.clear();
        findSolution(start, end);

        return fromEnd(end);
    }

    private void validateStart(Anode start) {
        validateStartOrEnd(start, true);
    }

    private void validateStartOrEnd(Anode node, boolean start) {
        int maxRow = searchSpace.length;
        int maxCol = searchSpace[0].length;
        if (node.row() >= maxRow || node.col() >= maxCol) {
            throw new RuntimeException(String.format("%s have to be in %d, %d bounds", start ? "Start" : "End",
                maxRow, maxCol));
        }
    }

    private void validateEnd(Anode end) {
        validateStartOrEnd(end, false);
    }

    private void findSolution(Anode start, Anode end) {
        start.setH(manhattanHeuristic(start, end));
        open.add(start);
        while (!open.isEmpty()) {
            Anode current = open.poll();
            if (current.equals(end)) {
                end.setPredecessor(current.predecessor());
                break;
            }
            closed.add(current);
            for (Anode n : neighbors(current, end)) {
                if (closed.contains(n)) {
                    continue;
                }
                open.add(n);
                n.setPredecessor(current);
            }
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

        considerLeftAndLower(row, col, end, neighbors);
        considerLowerAndRight(row, col, end, neighbors);
        considerRightAndUpper(row, col, end, neighbors);
        considerUpperAndLeft(row, col, end, neighbors);

        return neighbors;
    }

    private void considerLeftAndLower(int row, int col, Anode end, List<Anode> neighbors) {
        int leftCol = col - 1;
        int lowerRow = row + 1;
        if (leftCol >= 0 && !searchSpace[row][leftCol].isObstacle()) {
            neighbors.add(configuredNode(searchSpace[row][leftCol], end, false));
            if (lowerRow < searchSpace.length && !searchSpace[lowerRow][leftCol].isObstacle()) {
                neighbors.add(configuredNode(searchSpace[lowerRow][leftCol], end, true));
            }
        }
    }

    private void considerLowerAndRight(int row, int col, Anode end, List<Anode> neighbors) {
        int lowerRow = row + 1;
        int rightCol = col + 1;
        if (lowerRow < searchSpace.length && !searchSpace[lowerRow][col].isObstacle()) {
            neighbors.add(configuredNode(searchSpace[lowerRow][col], end, false));
            if (rightCol < searchSpace[0].length && !searchSpace[lowerRow][rightCol].isObstacle()) {
                neighbors.add(configuredNode(searchSpace[lowerRow][rightCol], end, true));
            }
        }
    }

    private void considerRightAndUpper(int row, int col, Anode end, List<Anode> neighbors) {
        int rightCol = col + 1;
        int upperRow = row - 1;
        if (rightCol < searchSpace[0].length && !searchSpace[row][rightCol].isObstacle()) {
            neighbors.add(configuredNode(searchSpace[row][rightCol], end, false));
            if (upperRow >= 0 && !searchSpace[upperRow][rightCol].isObstacle()) {
                neighbors.add(configuredNode(searchSpace[upperRow][rightCol], end, true));
            }
        }
    }

    private void considerUpperAndLeft(int row, int col, Anode end, List<Anode> neighbors) {
        int upperRow = row - 1;
        int rightCol = col + 1;
        if (upperRow >= 0 && !searchSpace[upperRow][col].isObstacle()) {
            neighbors.add(configuredNode(searchSpace[upperRow][col], end, false));
            if (rightCol < searchSpace[0].length && !searchSpace[upperRow][rightCol].isObstacle()) {
                neighbors.add(configuredNode(searchSpace[upperRow][rightCol], end, true));
            }
        }
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
