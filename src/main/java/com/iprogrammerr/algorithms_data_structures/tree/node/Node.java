package com.iprogrammerr.algorithms_data_structures.tree.node;

public interface Node<T> {

    Node<T> parent();

    boolean hasParent();

    T data();
}
