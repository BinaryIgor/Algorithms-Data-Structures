package com.iprogrammerr.algorithms_data_structures.initialization;

import java.util.concurrent.Callable;

public class StickyInitialization<T> implements StickableInitialization<T> {

    private final Callable<T> source;
    private T value;
    private boolean stick;

    public StickyInitialization(Callable<T> source) {
        this.source = source;
        this.stick = false;
    }

    @Override
    public T value() {
        if (value == null || !stick) {
            try {
                value = source.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stick = true;
        }
        return value;
    }

    @Override
    public void unstick() {
        stick = false;
    }
}
