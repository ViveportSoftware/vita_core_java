package com.htc.vita.core.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InternalTaskRunner {
    private static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool();

    public static void execute(Runnable command) {
        THREAD_POOL.execute(command);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return THREAD_POOL.submit(task);
    }
}
