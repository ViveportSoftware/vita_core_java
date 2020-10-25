package com.htc.vita.core.util;

import java.util.HashSet;
import java.util.Set;

public class Action1<T1> {
    private final Set<Handler<T1>> mHandlerSet = new HashSet<Handler<T1>>();

    public Action1<T1> add(Handler<T1> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.add(handler);
        }
        return this;
    }

    public void invoke(T1 t1) throws Exception {
        Set<Handler<T1>> newHandlerSet;
        synchronized (mHandlerSet) {
            newHandlerSet = new HashSet<Handler<T1>>(mHandlerSet);
        }

        for (Handler<T1> item: newHandlerSet) {
            item.handle(t1);
        }
    }

    public Action1<T1> remove(Handler<T1> handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.remove(handler);
        }
        return this;
    }

    public static abstract class Handler<T1> {
        public void handle(T1 t1) throws Exception {
            onHandle(t1);
        }

        protected abstract void onHandle(T1 t1) throws Exception;
    }
}
