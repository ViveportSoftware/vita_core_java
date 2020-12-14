package com.htc.vita.core.util;

import java.util.HashSet;
import java.util.Set;

public class Action3<T1, T2, T3> {
    private final Set<Handler<T1, T2, T3>> mHandlerSet = new HashSet<Handler<T1, T2, T3>>();

    public Action3<T1, T2, T3> add(Handler<T1, T2, T3> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.add(handler);
        }
        return this;
    }

    public void invoke(T1 t1, T2 t2, T3 t3) throws Exception {
        Set<Handler<T1, T2, T3>> newHandlerSet;
        synchronized (mHandlerSet) {
            newHandlerSet = new HashSet<Handler<T1, T2, T3>>(mHandlerSet);
        }

        for (Handler<T1, T2, T3> item: newHandlerSet) {
            item.handle(
                    t1,
                    t2,
                    t3
            );
        }
    }

    public Action3<T1, T2, T3> remove(Handler<T1, T2, T3> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.remove(handler);
        }
        return this;
    }

    public static abstract class Handler<T1, T2, T3> {
        public void handle(
                T1 t1,
                T2 t2,
                T3 t3) throws Exception {
            onHandle(
                    t1,
                    t2,
                    t3
            );
        }

        protected abstract void onHandle(
                T1 t1,
                T2 t2,
                T3 t3
        ) throws Exception;
    }
}
