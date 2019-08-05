package com.iprogrammerr.algorithms_data_structures.initialization;

import java.util.concurrent.Callable;

public class SolidInitialization<T> implements Initialization<T> {

    private final Callable<T> source;
    private T value;

    public SolidInitialization(Callable<T> source) {
        this.source = source;
    }

    @Override
    public T value() {
        synchronized (this) {
            if (value == null) {
                try {
                    value = source.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return value;
        }
    }
}
