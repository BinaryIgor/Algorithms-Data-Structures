package com.iprogrammerr.algorithms_data_structures.list;

import java.util.ArrayList;
import java.util.List;

public class UniqueArrayList<T> implements UniqueList<T> {

    private final List<T> list;

    public UniqueArrayList(int initialCapacity) {
	list = new ArrayList<>(initialCapacity);
    }

    public UniqueArrayList() {
	list = new ArrayList<>();
    }

    @Override
    public void add(T item) {
	int previous = list.indexOf(item);
	if (previous < 0) {
	    list.add(item);
	} else {
	    list.set(previous, item);
	}
    }

    @Override
    public T get(int index) {
	return list.get(index);
    }

    @Override
    public int size() {
	return list.size();
    }

    @Override
    public boolean empty() {
	return list.size() == 0;
    }

}
