package com.iprogrammerr.algorithms_data_structures.initialization;

public interface UnreliableInitialization<T> {
    T value() throws Exception;
}
