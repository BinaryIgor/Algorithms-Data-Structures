package com.iprogrammerr.algorithms_data_structures.heuristics.meta.game;

import java.util.Random;

public final class TicTacToeGame {

	private final Board board;
	private final Random random;

	public TicTacToeGame(Board board, Random random) {
		this.board = board;
		this.random = random;
	}

	public void play() {
		this.board.display();
		makeFirstMove();
		playGame();
		checkStatus();
	}

	private void makeFirstMove() {
		System.out.println("Who starts? 1 - COMPUTER, 2 - USER");
		int choice = this.board.scanner.nextInt();
		if (choice == 1) {
			Cell cell = new Cell(this.random.nextInt(this.board.size), this.random.nextInt(this.board.size));
			this.board.move(cell, CellState.COMPUTER);
			this.board.display();
		}
	}

	private void playGame() {
		while (this.board.isRunning()) {
			System.out.println("User move:");
			Cell cell = new Cell(this.board.scanner.nextInt(), this.board.scanner.nextInt());
			this.board.move(cell, CellState.USER);
			this.board.display();
			if (!this.board.isRunning()) {
				break;
			}
			this.board.callMinimax(CellState.COMPUTER);
			for (Cell c : this.board.rootValues) {
				System.out.println(String.format("Cell values: %s, minimax: %d", c, c.miniMax()));
			}
			this.board.move(this.board.bestMove(), CellState.COMPUTER);
			this.board.display();
		}
	}

	private void checkStatus() {
		if (this.board.isWinning(CellState.COMPUTER)) {
			System.out.println("Computer has won...");
		} else if (this.board.isWinning(CellState.USER)) {
			System.out.println("The user has won...");
		} else {
			System.out.println("Nobody has won, hence it is a draw");
		}
	}
}
