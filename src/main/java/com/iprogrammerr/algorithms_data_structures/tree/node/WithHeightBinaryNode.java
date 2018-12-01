package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public interface WithHeightBinaryNode<T extends Comparable<T>> {

    T data();

    void changeData(T data);

    int height();

    void changeHeight(int height);

    Potential<WithHeightBinaryNode<T>> left();

    void changeLeft(Potential<WithHeightBinaryNode<T>> node);

    void changeLeft(WithHeightBinaryNode<T> node);

    Potential<WithHeightBinaryNode<T>> right();

    void changeRight(Potential<WithHeightBinaryNode<T>> node);

    void changeRight(WithHeightBinaryNode<T> node);
}
