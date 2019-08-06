package com.iprogrammerr.algorithms_data_structures.heuristics.meta.game;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public final class Board {

    public final List<Cell> rootValues;
    public final Scanner scanner;
    public final int size;
    private final Initialization<CellState[][]> board;

    public Board(Scanner scanner, int size) {
        this.rootValues = new ArrayList<>();
        this.scanner = scanner;
        this.board = new StickyInitialization<>(() -> {
            CellState[][] board = new CellState[size][size];
            for (int i = 0; i < board.length; ++i) {
                for (int j = 0; j < board[i].length; ++j) {
                    board[i][j] = CellState.EMPTY;
                }
            }
            return board;
        });
        this.size = size;
    }

    public Board(int boardSize) {
        this(new Scanner(System.in), boardSize);

    }

    public Board() {
        this(3);
    }

    public boolean isRunning() {
        return (!isWinning(CellState.COMPUTER) && !isWinning(CellState.USER)) && hasEmptyCell();
    }

    public Cell bestMove() {
        int max = this.rootValues.get(0).miniMax();
        int best = 0;
        for (int i = 1; i < this.rootValues.size(); ++i) {
            if (max < this.rootValues.get(i).miniMax()) {
                max = this.rootValues.get(i).miniMax();
                best = i;
            }
        }
        return this.rootValues.get(best);
    }

    public boolean isWinning(CellState state) {
        return isWinningDiagonally(state, true) || isWinningDiagonally(state, false) || isWinningHorizontally(state)
            || isWinningVertically(state);
    }

    private boolean isWinningDiagonally(CellState state, boolean leftRight) {
        boolean winning = true;
        if (leftRight) {
            for (int i = 0; i < this.size; ++i) {
                winning &= this.board.value()[i][i] == state;
                if (!winning) {
                    break;
                }
            }
        } else {
            for (int i = 0, j = this.size - 1; i < this.size; ++i, --j) {
                winning &= this.board.value()[i][j] == state;
                if (!winning) {
                    break;
                }
            }
        }
        return winning;
    }

    private boolean isWinningHorizontally(CellState state) {
        for (int i = 0; i < this.size; ++i) {
            boolean winning = true;
            for (int j = 0; j < this.size; ++j) {
                winning &= this.board.value()[i][j] == state;
                if (!winning) {
                    break;
                }
            }
            if (winning) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinningVertically(CellState state) {
        for (int i = 0; i < this.size; ++i) {
            boolean winning = true;
            for (int j = 0; j < this.size; ++j) {
                winning &= this.board.value()[j][i] == state;
                if (!winning) {
                    break;
                }
            }
            if (winning) {
                return true;
            }
        }
        return false;
    }

    private List<Cell> empty() {
        List<Cell> empty = new ArrayList<>();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.board.value()[i][j] == CellState.EMPTY) {
                    empty.add(new Cell(i, j));
                }
            }
        }
        return empty;
    }

    private boolean hasEmptyCell() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.board.value()[i][j] == CellState.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    private int min(List<Integer> ints) {
        return ints.stream().min(Comparator.naturalOrder()).get();
    }

    private int max(List<Integer> ints) {
        return ints.stream().max(Comparator.naturalOrder()).get();
    }

    public void move(Cell cell, CellState state) {
        this.board.value()[cell.x][cell.y] = state;
    }

    public void display() {
        StringBuilder builder = new StringBuilder(System.lineSeparator());
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                builder.append(this.board.value()[i][j]).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        System.out.println(builder.toString());
    }

    public void callMinimax(CellState state) {
        this.rootValues.clear();
        minimax(true, state);
    }

    private int minimax(boolean root, CellState state) {
        if (isWinning(CellState.COMPUTER)) {
            return 1;
        }
        if (isWinning(CellState.USER)) {
            return -1;
        }
        List<Cell> available = empty();
        if (available.isEmpty()) {
            return 0;
        }
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < available.size(); ++i) {
            Cell cell = available.get(i);
            if (CellState.COMPUTER == state) {
                move(cell, CellState.COMPUTER);
                int score = minimax(false, CellState.USER);
                scores.add(score);
                if (root) {
                    cell.setMiniMax(score);
                    this.rootValues.add(cell);
                }
            } else if (state == CellState.USER) {
                move(cell, CellState.USER);
                scores.add(minimax(false, CellState.COMPUTER));
            }
            this.board.value()[cell.x][cell.y] = CellState.EMPTY;
        }
        if (state == CellState.COMPUTER) {
            return max(scores);
        }
        return min(scores);
    }
}
