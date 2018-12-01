package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public interface WithParentBinaryNode<T extends Comparable<T>> {

    T data();

    void data(T data);

    Potential<WithParentBinaryNode<T>> parent();

    void changeParent(WithParentBinaryNode<T> node);

    void changeParent(Potential<WithParentBinaryNode<T>> node);

    Potential<WithParentBinaryNode<T>> left();

    void changeLeft(WithParentBinaryNode<T> node);

    void changeLeft(Potential<WithParentBinaryNode<T>> node);

    Potential<WithParentBinaryNode<T>> right();

    void changeRight(WithParentBinaryNode<T> node);

    void changeRight(Potential<WithParentBinaryNode<T>> node);

}
