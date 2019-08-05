package com.iprogrammerr.algorithms_data_structures.graph;

import com.iprogrammerr.algorithms_data_structures.graph.as.Anode;
import com.iprogrammerr.algorithms_data_structures.graph.as.AstarAlgorithm;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class AstarAlgorithmTest {

    private final Random random = new Random();

    @Test
    public void findsPathInSmallSpace() {
        int size = 5;
        AstarAlgorithm astar = new AstarAlgorithm(size, size);
        findsPath(astar, new Anode(0, 0), new Anode(random.nextInt(size), random.nextInt(size)));
    }

    private void findsPath(AstarAlgorithm astar, Anode start, Anode end) {
        List<Anode> path = astar.path(start, end);

        MatcherAssert.assertThat(start, Matchers.equalTo(path.get(0)));
        MatcherAssert.assertThat(end, Matchers.equalTo(path.get(path.size() - 1)));

        for (int i = 1; i < path.size(); i++) {
            Anode previous = path.get(i - 1);
            Anode current = path.get(i);
            MatcherAssert.assertThat(previous.colDiff(current), Matchers.lessThanOrEqualTo(1));
            MatcherAssert.assertThat(previous.rowDiff(current), Matchers.lessThanOrEqualTo(1));
            MatcherAssert.assertThat(previous.equals(current), Matchers.equalTo(false));
        }
    }

    @Test
    public void findsPathInLargeSpace() {
        int rows = 500;
        int cols = 1000;

        AstarAlgorithm astar = new AstarAlgorithm(rows, cols);
        Anode start = new Anode(random.nextInt(rows / 2), random.nextInt(cols / 2));
        Anode end = new Anode(rows - 1, cols - 1);

        findsPath(astar, start, end);
    }
}