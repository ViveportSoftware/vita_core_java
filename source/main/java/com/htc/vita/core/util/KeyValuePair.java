package com.htc.vita.core.util;

import java.util.AbstractMap;

public class KeyValuePair<K, V> extends AbstractMap.SimpleEntry<K, V> {
    public KeyValuePair(
            K key,
            V value) {
        super(
                key,
                value
        );
    }
}
