package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.heuristics.meta.game.Board;
import com.iprogrammerr.algorithms_data_structures.heuristics.meta.game.TicTacToeGame;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(new Board(), new Random());
        game.play();
    }
}
