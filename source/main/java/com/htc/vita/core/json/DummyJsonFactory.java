package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;

public class DummyJsonFactory extends JsonFactory {
    public DummyJsonFactory() {
        Logger.getInstance(DummyJsonFactory.class.getSimpleName()).error(String.format(
                "You are using dummy %s instance!!",
                JsonFactory.class.getSimpleName()
        ));
    }

    @Override
    protected JsonArray onCreateJsonArray() {
        return new DummyJsonArray();
    }

    @Override
    protected JsonObject onCreateJsonObject() {
        return new DummyJsonObject();
    }

    @Override
    protected <T> T onDeserializeObject(String content, Class<T> type) {
        return null;
    }

    @Override
    protected JsonArray onGetJsonArray(String content) {
        return null;
    }

    @Override
    protected JsonObject onGetJsonObject(String content) {
        return null;
    }

    @Override
    protected String onSerializeObject(Object content) {
        return null;
    }
}
