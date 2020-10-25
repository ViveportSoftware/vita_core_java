package com.htc.vita.core.json;

public class DummyJsonArray extends JsonArray {
    @Override
    protected JsonArray onAppendBoolean(boolean value) {
        return this;
    }

    @Override
    protected JsonArray onAppendDouble(double value) {
        return this;
    }

    @Override
    protected JsonArray onAppendFloat(float value) {
        return this;
    }

    @Override
    protected JsonArray onAppendInt(int value) {
        return this;
    }

    @Override
    protected JsonArray onAppendLong(long value) {
        return this;
    }

    @Override
    protected JsonArray onAppendString(String value) {
        return this;
    }

    @Override
    protected JsonArray onAppendJsonArray(JsonArray value) {
        return this;
    }

    @Override
    protected JsonArray onAppendJsonObject(JsonObject value) {
        return this;
    }

    @Override
    protected JsonArray onInsertBoolean(
            int index,
            boolean value) {
        return this;
    }

    @Override
    protected JsonArray onInsertDouble(
            int index,
            double value) {
        return this;
    }

    @Override
    protected JsonArray onInsertFloat(
            int index,
            float value) {
        return this;
    }

    @Override
    protected JsonArray onInsertInt(
            int index,
            int value) {
        return this;
    }

    @Override
    protected JsonArray onInsertLong(
            int index,
            long value) {
        return this;
    }

    @Override
    protected JsonArray onInsertString(
            int index,
            String value) {
        return this;
    }

    @Override
    protected JsonArray onInsertJsonArray(
            int index,
            JsonArray value) {
        return this;
    }

    @Override
    protected JsonArray onInsertJsonObject(
            int index,
            JsonObject value) {
        return this;
    }

    @Override
    protected boolean onParseBoolean(
            int index,
            boolean defaultValue) {
        return defaultValue;
    }

    @Override
    protected double onParseDouble(
            int index,
            double defaultValue) {
        return defaultValue;
    }

    @Override
    protected float onParseFloat(
            int index,
            float defaultValue) {
        return defaultValue;
    }

    @Override
    protected int onParseInt(
            int index,
            int defaultValue) {
        return defaultValue;
    }

    @Override
    protected long onParseLong(
            int index,
            long defaultValue) {
        return defaultValue;
    }

    @Override
    protected String onParseString(
            int index,
            String defaultValue) {
        return defaultValue;
    }

    @Override
    protected JsonArray onParseJsonArray(int index) {
        return null;
    }

    @Override
    protected JsonObject onParseJsonObject(int index) {
        return null;
    }

    @Override
    protected int onSize() {
        return 0;
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
