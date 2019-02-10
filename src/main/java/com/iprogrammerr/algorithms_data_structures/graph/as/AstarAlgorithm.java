package com.iprogrammerr.algorithms_data_structures.graph.as;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class AstarAlgorithm {

	private static final int HORIZONAL_VERTICAL_COST = 10;
	private static final int DIAGONAL_COST = 15;
	private final Anode[][] searchSpace;
	private Anode start;
	private Anode end;
	private final List<Anode> closed;
	private final Queue<Anode> open;
	private final Initialization<Boolean> initialization;

	public AstarAlgorithm(int rows, int cols) {
		this.searchSpace = new Anode[rows][cols];
		this.closed = new ArrayList<>();
		this.open = new PriorityQueue<>((f, s) -> {
			if (f.f() > s.f()) {
				return 1;
			} else if (f.f() < s.f()) {
				return -1;
			}
			return 0;
		});
		this.initialization = new StickyInitialization<>(() -> {
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					this.searchSpace[i][j] = new Anode(i, j);
				}
			}
			Random random = new Random();
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < 1 + random.nextInt(cols); ++j) {
					if (random.nextBoolean()) {
						this.searchSpace[i][j].setObstacle(true);
					}
				}
			}
			this.start = this.searchSpace[random.nextInt(rows)][random.nextInt(cols)];
			this.end = this.searchSpace[random.nextInt(rows)][random.nextInt(cols)];
			System.out.println(String.format("start = %s, end = %s", this.start, this.end));
			return true;
		});
	}

	public AstarAlgorithm() {
		this(10, 15);
	}

	public List<Anode> path() {
		this.initialization.value();
		this.start.setH(manhattanHeuristic(this.start, this.end));
		this.open.add(this.start);
		while (!this.open.isEmpty()) {
			Anode current = this.open.poll();
			if (current.equals(this.end)) {
				break;
			}
			this.open.remove(current);
			this.closed.add(current);
			for (Anode n : neighbors(current)) {
				if (this.closed.contains(n)) {
					continue;
				}
				if (!this.open.contains(n)) {
					this.open.add(n);
				}
				n.setPredecessor(current);
			}
		}
		return fromEnd();
	}

	private List<Anode> fromEnd() {
		List<Anode> path = new ArrayList<>();
		Anode n = this.end;
		while (n != null) {
			path.add(n);
			n = n.predecessor();
		}
		Collections.reverse(path);
		return path;
	}

	private List<Anode> neighbors(Anode node) {
		List<Anode> neighbors = new ArrayList<>();
		int row = node.row();
		int col = node.col();
		if (col - 1 >= 0 && !this.searchSpace[row][col - 1].isObstacle()) {
			neighbors.add(configuredNode(this.searchSpace[row][col - 1], false));
			if (row + 1 < this.searchSpace.length && !this.searchSpace[row + 1][col - 1].isObstacle()) {
				neighbors.add(configuredNode(this.searchSpace[row + 1][col - 1], true));
			}
		}
		if (row + 1 < this.searchSpace.length && !this.searchSpace[row + 1][col].isObstacle()) {
			neighbors.add(configuredNode(this.searchSpace[row + 1][col], false));
			if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row + 1][col + 1].isObstacle()) {
				neighbors.add(configuredNode(this.searchSpace[row + 1][col + 1], true));
			}
		}
		if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row][col + 1].isObstacle()) {
			neighbors.add(configuredNode(this.searchSpace[row][col + 1], false));
			if (row - 1 >= 0 && !this.searchSpace[row - 1][col + 1].isObstacle()) {
				neighbors.add(configuredNode(this.searchSpace[row - 1][col + 1], true));
			}
		}
		if (row - 1 >= 0 && !this.searchSpace[row - 1][col].isObstacle()) {
			neighbors.add(configuredNode(this.searchSpace[row - 1][col], false));
			if (col + 1 < this.searchSpace[0].length && !this.searchSpace[row - 1][col + 1].isObstacle()) {
				neighbors.add(configuredNode(this.searchSpace[row - 1][col + 1], true));
			}
		}
		return neighbors;
	}

	private Anode configuredNode(Anode node, boolean diagonal) {
		node.setG(node.g() + (diagonal ? DIAGONAL_COST : HORIZONAL_VERTICAL_COST));
		node.setH(manhattanHeuristic(node, this.end));
		return node;
	}

	private int manhattanHeuristic(Anode first, Anode second) {
		return (Math.abs(first.row() - second.row()) + Math.abs(first.col() - second.col())) * 10;
	}
}
