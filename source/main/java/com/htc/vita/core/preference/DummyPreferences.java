package com.htc.vita.core.preference;

import java.util.HashSet;
import java.util.Set;

public class DummyPreferences extends Preferences {
    protected DummyPreferences(
            String category,
            String label) {
        super(
                category,
                label
        );
    }

    @Override
    protected Set<String> onAllKeys() {
        return new HashSet<String>();
    }

    @Override
    protected Preferences onClear() {
        return this;
    }

    @Override
    protected boolean onHasKey(String key) {
        return false;
    }

    @Override
    protected Preferences onInitialize() {
        return this;
    }

    @Override
    protected boolean onParseBoolean(
            String key,
            boolean defaultValue) {
        return defaultValue;
    }

    @Override
    protected double onParseDouble(
            String key,
            double defaultValue) {
        return defaultValue;
    }

    @Override
    protected float onParseFloat(
            String key,
            float defaultValue) {
        return defaultValue;
    }

    @Override
    protected int onParseInt(
            String key,
            int defaultValue) {
        return defaultValue;
    }

    @Override
    protected long onParseLong(
            String key,
            long defaultValue) {
        return defaultValue;
    }

    @Override
    protected String onParseString(
            String key,
            String defaultValue) {
        return defaultValue;
    }

    @Override
    protected Preferences onPutBoolean(
            String key,
            boolean value) {
        return this;
    }

    @Override
    protected Preferences onPutDouble(
            String key,
            double value) {
        return this;
    }

    @Override
    protected Preferences onPutFloat(
            String key,
            float value) {
        return this;
    }

    @Override
    protected Preferences onPutInt(
            String key,
            int value) {
        return this;
    }

    @Override
    protected Preferences onPutLong(
            String key,
            long value) {
        return this;
    }

    @Override
    protected Preferences onPutString(
            String key,
            String value) {
        return this;
    }

    @Override
    protected boolean onSave() {
        return false;
    }
}
