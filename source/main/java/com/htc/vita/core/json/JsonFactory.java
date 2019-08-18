package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonFactory {
    private static final Map<String, JsonFactory> sInstances = new HashMap<String, JsonFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends JsonFactory> sDefaultClass = DummyJsonFactory.class;

    public static <T extends JsonFactory> void register(Class<T> clazz) {
        sDefaultClass = clazz;
        System.err.println("Registered default " + JsonFactory.class.getName() + " type to " + sDefaultClass.getName());
    }

    public static JsonFactory getInstance() {
        JsonFactory instance;
        try {
            instance = doGetInstance(sDefaultClass);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal("Instance initialization error: " + e);
            Logger.getInstance(JsonFactory.class.getSimpleName()).info("Initializing " + DummyJsonFactory.class.getName() + "...");
            instance = new DummyJsonFactory();
        }
        return instance;
    }

    private static <T extends JsonFactory> JsonFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Invalid argument to get %s instance", JsonFactory.class.getName()));
        }

        String key = clazz.getName() + "_";
        JsonFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).info("Initializing " + key + "...");
            try {
                Constructor constructor = clazz.getConstructor();
                if (constructor != null) {
                    instance = (JsonFactory) constructor.newInstance();
                }
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).info("Initializing " + DummyJsonFactory.class.getName() + "...");
            instance = new DummyJsonFactory();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(key, instance);
            }
        }
        return instance;
    }

    public JsonArray createJsonArray() {
        return onCreateJsonArray();
    }

    public JsonObject createJsonObject() {
        return onCreateJsonObject();
    }

    public <T> T deserializeObject(String content, Class<T> type) {
        T result = null;
        if (StringUtils.isNullOrWhiteSpace(content)) {
            return result;
        }

        try {
            result = onDeserializeObject(content, type);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal("Deserializing object error: " + e);
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
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal("Getting json array error: " + e);
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
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal("Getting json object error: " + e);
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
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal("Serializing object error: " + e);
        }
        return result;
    }

    protected abstract JsonArray onCreateJsonArray();
    protected abstract JsonObject onCreateJsonObject();
    protected abstract <T> T onDeserializeObject(String content, Class<T> type);
    protected abstract JsonArray onGetJsonArray(String content);
    protected abstract JsonObject onGetJsonObject(String content);
    protected abstract String onSerializeObject(Object content);
}
