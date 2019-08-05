package com.iprogrammerr.algorithms_data_structures.graph.scc;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

import java.util.Stack;
import java.util.concurrent.Callable;

public final class DepthFirstOrder {

    private Initialization<Stack<KosarajuVertex>> stack;

    public DepthFirstOrder(KosarajuGraph graph) {
        this.stack = new StickyInitialization<>(new Callable<Stack<KosarajuVertex>>() {

            private final Stack<KosarajuVertex> stack = new Stack<>();

            @Override
            public Stack<KosarajuVertex> call() {
                if (this.stack.isEmpty()) {
                    for (KosarajuVertex v : graph.vertices()) {
                        if (!v.isVisited()) {
                            dfs(v);
                        }
                    }
                }
                return stack;
            }

            private void dfs(KosarajuVertex vertex) {
                vertex.setVisited(true);
                for (KosarajuVertex v : vertex.neighbors()) {
                    if (!v.isVisited()) {
                        dfs(v);
                    }
                }
                this.stack.push(vertex);
            }
        });
    }

    public Stack<KosarajuVertex> stack() {
        return this.stack.value();
    }
}
