package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

public abstract class JsonFactory {
    static {
        TypeRegistry.registerDefault(
                JsonFactory.class,
                DummyJsonFactory.class
        );
    }

    public static <T extends JsonFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                JsonFactory.class,
                clazz
        );
    }

    public static JsonFactory getInstance() {
        return TypeRegistry.getInstance(JsonFactory.class);
    }

    public static <T extends JsonFactory> JsonFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                JsonFactory.class,
                clazz
        );
    }

    public JsonArray createJsonArray() {
        JsonArray result = null;
        try {
            result = onCreateJsonArray();
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject createJsonObject() {
        JsonObject result = null;
        try {
            result = onCreateJsonObject();
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public <T> T deserializeObject(String content, Class<T> type) {
        if (StringUtils.isNullOrWhiteSpace(content)) {
            return null;
        }

        T result = null;
        try {
            result = onDeserializeObject(content, type);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray getJsonArray(String content) {
        if (StringUtils.isNullOrWhiteSpace(content)) {
            return null;
        }

        JsonArray result = null;
        try {
            result = onGetJsonArray(content);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject getJsonObject(String content) {
        if (StringUtils.isNullOrWhiteSpace(content)) {
            return null;
        }

        JsonObject result = null;
        try {
            result = onGetJsonObject(content);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String serializeObject(Object content) {
        if (content == null) {
            return null;
        }

        String result = null;
        try {
            result = onSerializeObject(content);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract JsonArray onCreateJsonArray() throws Exception;
    protected abstract JsonObject onCreateJsonObject() throws Exception;
    protected abstract <T> T onDeserializeObject(
            String content,
            Class<T> type
    ) throws Exception;
    protected abstract JsonArray onGetJsonArray(String content) throws Exception;
    protected abstract JsonObject onGetJsonObject(String content) throws Exception;
    protected abstract String onSerializeObject(Object content) throws Exception;
}
