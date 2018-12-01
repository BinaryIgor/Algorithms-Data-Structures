package com.iprogrammerr.algorithms_data_structures.tree.node;

import com.iprogrammerr.algorithms_data_structures.model.Potential;

public interface ColoredBinaryNode<T extends Comparable<T>> {

    T data();

    void data(T data);

    String color();

    void changeColor(String color);

    Potential<ColoredBinaryNode<T>> parent();

    void changeParent(ColoredBinaryNode<T> node);

    void changeParent(Potential<ColoredBinaryNode<T>> node);

    Potential<ColoredBinaryNode<T>> left();

    void changeLeft(Potential<ColoredBinaryNode<T>> node);

    void changeLeft(ColoredBinaryNode<T> node);

    Potential<ColoredBinaryNode<T>> right();

    void changeRight(Potential<ColoredBinaryNode<T>> node);

    void changeRight(ColoredBinaryNode<T> node);
}
