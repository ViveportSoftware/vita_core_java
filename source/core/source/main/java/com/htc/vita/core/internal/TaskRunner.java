package com.htc.vita.core.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class TaskRunner {
    public static void execute(Runnable command) {
        TaskRunnerImpl.getInstance().execute(command);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return TaskRunnerImpl.getInstance().submit(task);
    }
}
