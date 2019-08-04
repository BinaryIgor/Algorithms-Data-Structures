package com.iprogrammerr.algorithms_data_structures;

import com.iprogrammerr.algorithms_data_structures.tree.RedBlackTree;
import com.iprogrammerr.algorithms_data_structures.tree.Tree;

public class App {

    public static void main(String[] args) throws Exception {
        Tree<Integer> tree = new RedBlackTree<>(2);
        tree.put(1);
        tree.put(4);
        tree.put(6);
        tree.delete(4);
        tree.contains(1);
        tree.traverse(t -> System.out.println(t));
    }
}
