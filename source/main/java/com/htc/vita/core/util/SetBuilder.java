package com.htc.vita.core.util;

import java.util.HashSet;
import java.util.Set;

public class SetBuilder<T> {
    private final Set<T> mInnerInstance = new HashSet<T>();

    public SetBuilder<T> add(T item) {
        mInnerInstance.add(item);
        return this;
    }

    public SetBuilder<T> addIfNotNull(T item) {
        if (item == null) {
            return this;
        }
        return add(item);
    }

    public SetBuilder<T> clear() {
        mInnerInstance.clear();
        return this;
    }

    public int size() {
        return mInnerInstance.size();
    }

    public Set<T> toSet() {
        return new HashSet<T>(mInnerInstance);
    }
}
