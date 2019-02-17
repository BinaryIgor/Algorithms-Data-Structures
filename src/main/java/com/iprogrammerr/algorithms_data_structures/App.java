package com.iprogrammerr.algorithms_data_structures;

import java.util.Random;

import com.iprogrammerr.algorithms_data_structures.heuristics.meta.game.Board;
import com.iprogrammerr.algorithms_data_structures.heuristics.meta.game.TicTacToeGame;

public class App {

	public static void main(String[] args) throws Exception {
		new TicTacToeGame(new Board(), new Random()).play();
	}
}
