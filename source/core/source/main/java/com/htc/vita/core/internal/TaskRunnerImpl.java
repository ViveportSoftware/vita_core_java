package com.htc.vita.core.internal;

import com.htc.vita.core.concurrent.CompletedFuture;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class TaskRunnerImpl {
    static {
        TypeRegistry.registerDefault(
                TaskRunnerImpl.class,
                DefaultTaskRunnerImpl.class
        );
    }

    public static <T extends TaskRunnerImpl> void register(Class<T> clazz) {
        TypeRegistry.register(
                TaskRunnerImpl.class,
                clazz
        );
    }

    public static TaskRunnerImpl getInstance() {
        return TypeRegistry.getInstance(TaskRunnerImpl.class);
    }

    public static <T extends TaskRunnerImpl> TaskRunnerImpl getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                TaskRunnerImpl.class,
                clazz
        );
    }

    public void execute(Runnable command) {
        try {
            onExecute(command);
        } catch (Exception e) {
            Logger.getInstance(TaskRunnerImpl.class.getSimpleName()).error(e.toString());
        }
    }

    public <T> Future<T> submit(Callable<T> task) {
        Future<T> result = null;
        try {
            result = onSubmit(task);
        } catch (Exception e) {
            Logger.getInstance(TaskRunnerImpl.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new CompletedFuture<T>(null);
        }
        return result;
    }

    protected abstract void onExecute(Runnable command) throws Exception;
    protected abstract <T> Future<T> onSubmit(Callable<T> task) throws Exception;
}
