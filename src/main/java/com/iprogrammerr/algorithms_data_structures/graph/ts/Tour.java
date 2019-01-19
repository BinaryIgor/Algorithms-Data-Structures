package com.iprogrammerr.algorithms_data_structures.graph.ts;

import java.util.ArrayList;
import java.util.List;

public final class Tour {

	private final List<City> tour;
	private double distance;

	public Tour(List<City> tour) {
		this.tour = new ArrayList<>(tour);
		this.distance = -1;
	}

	public double distance() {
		if (this.distance < 0) {
			double td = 0;
			for (int i = 0; i < this.tour.size(); ++i) {
				City destination;
				if (i + 1 < this.tour.size()) {
					destination = this.tour.get(i + 1);
				} else {
					destination = this.tour.get(0);
				}
				td = td + this.tour.get(i).distanceTo(destination);
			}
			this.distance = td;
		}
		return this.distance;
	}

	public List<City> value() {
		return this.tour;
	}

	public void swap(int first, int second) {
		City tmp = this.tour.get(first);
		this.tour.set(first, this.tour.get(second));
		this.tour.set(second, tmp);
		this.distance = -1;
	}

	@Override
	public String toString() {
		return this.tour.toString();
	}
}
