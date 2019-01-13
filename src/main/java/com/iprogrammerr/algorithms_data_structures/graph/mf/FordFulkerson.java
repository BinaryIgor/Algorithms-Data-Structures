package com.iprogrammerr.algorithms_data_structures.graph.mf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.iprogrammerr.algorithms_data_structures.initialization.Initialization;
import com.iprogrammerr.algorithms_data_structures.initialization.StickyInitialization;

public final class FordFulkerson implements MaxFlow {

	private Map<Vertex, Boolean> marked;
	private Map<Vertex, Edge> edgeTo;
	private final Initialization<Double> maxFlow;

	public FordFulkerson(FlowNetwork network, Vertex s, Vertex t) {
		this.maxFlow = new StickyInitialization<>(() -> {
			double mf = 0.0;
			while (hasAugmentingPath(network, s, t)) {
				double min = Double.POSITIVE_INFINITY;
				for (Vertex v = t; v != s; v = this.edgeTo.get(v).other(v)) {
					min = Math.min(min, this.edgeTo.get(v).residualFlow(v));
				}
				for (Vertex v = t; v != s; v = this.edgeTo.get(v).other(v)) {
					this.edgeTo.get(v).addResidualFlow(v, min);
				}
				mf += min;
			}
			return mf;
		});
		this.edgeTo = new HashMap<>(network.edges());
		this.marked = new HashMap<>(network.vertices());
	}

	private boolean hasAugmentingPath(FlowNetwork network, Vertex s, Vertex t) {
		this.edgeTo.clear();
		this.marked.clear();
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(s);
		this.marked.put(s, true);
		while (!queue.isEmpty() && !this.marked.getOrDefault(t, false)) {
			Vertex v = queue.poll();
			for (Edge e : network.edges(v)) {
				Vertex w = e.other(v);
				if (e.residualFlow(w) > 0 && !this.marked.getOrDefault(w, false)) {
					this.edgeTo.put(w, e);
					this.marked.put(w, true);
					queue.add(w);
				}
			}
		}
		return this.marked.getOrDefault(t, false);
	}

	@Override
	public boolean isInCut(Vertex vertex) {
		return this.marked.getOrDefault(vertex, false);
	}

	@Override
	public double value() {
		return this.maxFlow.value();
	}
}
