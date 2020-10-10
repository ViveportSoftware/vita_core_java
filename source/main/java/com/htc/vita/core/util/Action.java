package com.htc.vita.core.util;

import java.util.HashSet;
import java.util.Set;

public class Action {
    private final Set<Handler> mHandlerSet = new HashSet<Handler>();

    public Action add(Handler handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.add(handler);
        }
        return this;
    }

    public void invoke() throws Exception {
        Set<Handler> newHandlerSet;
        synchronized (mHandlerSet) {
            newHandlerSet = new HashSet<Handler>(mHandlerSet);
        }

        for (Handler item: newHandlerSet) {
            item.handle();
        }
    }

    public Action remove(Handler handler) {
        if (handler == null) {
            return this;
        }

        synchronized (mHandlerSet) {
            mHandlerSet.remove(handler);
        }
        return this;
    }

    public static abstract class Handler {
        public void handle() throws Exception {
            onHandle();
        }

        protected abstract void onHandle() throws Exception;
    }
}
