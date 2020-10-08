package com.htc.vita.core.util;

import java.util.HashSet;
import java.util.Set;

public class Action2<T1, T2> {
    private final Set<Handler<T1, T2>> mHandlerSet = new HashSet<Handler<T1, T2>>();

    public Action2<T1, T2> add(Handler<T1, T2> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.add(handler);
        }
        return this;
    }

    public void invoke(T1 t1, T2 t2) throws Exception {
        Set<Handler<T1, T2>> newHandlerSet;
        synchronized (mHandlerSet) {
            newHandlerSet = new HashSet<Handler<T1, T2>>(mHandlerSet);
        }

        for (Handler<T1, T2> item: newHandlerSet) {
            item.handle(
                    t1,
                    t2
            );
        }
    }

    public Action2<T1, T2> remove(Handler<T1, T2> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.remove(handler);
        }
        return this;
    }

    public static abstract class Handler<T1, T2> {
        public void handle(
                T1 t1,
                T2 t2) throws Exception {
            onHandle(
                    t1,
                    t2
            );
        }

        protected abstract void onHandle(
                T1 t1,
                T2 t2
        ) throws Exception;
    }
}
