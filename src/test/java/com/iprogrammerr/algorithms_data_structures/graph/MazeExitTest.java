package com.iprogrammerr.algorithms_data_structures.graph;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class MazeExitTest {

    @Test
    public void findsExit() {
        Maze maze = new MazeFile(getClass().getResource("/maze.txt").getFile());
        MazeExit exit = new MazeExit(maze);
        MatcherAssert.assertThat(exit.find(), Matchers.equalTo(new Maze.Coordinates(5, 4)));
    }
}
