package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public interface BinaryNode<T extends Comparable<T>> {

    T data();

    void data(T data);

    Potential<BinaryNode<T>> left();

    void changeLeft(BinaryNode<T> node);

    void changeLeft(Potential<BinaryNode<T>> node);

    Potential<BinaryNode<T>> right();

    void changeRight(BinaryNode<T> node);

    void changeRight(Potential<BinaryNode<T>> node);
}
