package com.htc.vita.core.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DefaultTaskRunnerImpl extends TaskRunnerImpl {
    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    @Override
    protected void onExecute(Runnable command) {
        THREAD_POOL.execute(command);
    }

    @Override
    protected <T> Future<T> onSubmit(Callable<T> task) {
        return THREAD_POOL.submit(task);
    }
}
