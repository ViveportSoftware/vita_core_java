package com.htc.vita.core.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletedFuture<T> implements Future<T> {
    private final T mData;

    public CompletedFuture(final T data) {
        mData = data;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public T get()
            throws InterruptedException, ExecutionException {
        return mData;
    }

    @Override
    public T get(
            long l,
            TimeUnit timeUnit)
                    throws InterruptedException, ExecutionException, TimeoutException {
        return get();
    }
}
