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
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(JsonFactory.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                JsonFactory.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static JsonFactory getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends JsonFactory> JsonFactory getInstance(Class<T> clazz) {
        JsonFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(JsonFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyJsonFactory.class.getName()
            ));
            instance = new DummyJsonFactory();
        }
        return instance;
    }

    private static <T extends JsonFactory> JsonFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    JsonFactory.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        JsonFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    key
            ));
            try {
                Constructor<T> constructor = clazz.getConstructor();
                instance = constructor.newInstance();
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(JsonFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyJsonFactory.class.getName()
            ));
            instance = new DummyJsonFactory();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(
                        key,
                        instance
                );
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

    protected abstract JsonArray onCreateJsonArray();
    protected abstract JsonObject onCreateJsonObject();
    protected abstract <T> T onDeserializeObject(String content, Class<T> type);
    protected abstract JsonArray onGetJsonArray(String content);
    protected abstract JsonObject onGetJsonObject(String content);
    protected abstract String onSerializeObject(Object content);
}
