package com.htc.vita.core.json;

import java.util.HashSet;
import java.util.Set;

public class DummyJsonObject extends JsonObject {
    @Override
    protected Set<String> onAllKeys() {
        return new HashSet<String>();
    }

    @Override
    protected boolean onHasKey(String key) {
        return false;
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
    protected JsonArray onParseJsonArray(String key) {
        return null;
    }

    @Override
    protected JsonObject onParseJsonObject(String key) {
        return null;
    }

    @Override
    protected JsonObject onPutBoolean(
            String key,
            boolean value) {
        return this;
    }

    @Override
    protected JsonObject onPutDouble(
            String key,
            double value) {
        return this;
    }

    @Override
    protected JsonObject onPutFloat(
            String key,
            float value) {
        return this;
    }

    @Override
    protected JsonObject onPutInt(
            String key,
            int value) {
        return this;
    }

    @Override
    protected JsonObject onPutLong(
            String key,
            long value) {
        return this;
    }

    @Override
    protected JsonObject onPutString(
            String key,
            String value) {
        return this;
    }

    @Override
    protected JsonObject onPutJsonArray(
            String key,
            JsonArray value) {
        return this;
    }

    @Override
    protected JsonObject onPutJsonObject(
            String key,
            JsonObject value) {
        return this;
    }

    @Override
    protected String onToPrettyString() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }
}
