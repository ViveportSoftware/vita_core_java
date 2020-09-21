package com.htc.vita.core.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<TKey, TValue> {
    private final Map<TKey, TValue> mInnerInstance = new HashMap<TKey, TValue>();

    public MapBuilder<TKey, TValue> clear() {
        mInnerInstance.clear();
        return this;
    }

    public MapBuilder<TKey, TValue> put(
            TKey key,
            TValue value) {
        mInnerInstance.put(
                key,
                value
        );
        return this;
    }

    public MapBuilder<TKey, TValue> putIfNotExists(
            TKey key,
            TValue value) {
        if (mInnerInstance.containsKey(key)) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public MapBuilder<TKey, TValue> putIfNotNull(
            TKey key,
            TValue value) {
        if (value == null) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public int size() {
        return mInnerInstance.size();
    }

    public Map<TKey, TValue> toMap() {
        return mInnerInstance;
    }
}
