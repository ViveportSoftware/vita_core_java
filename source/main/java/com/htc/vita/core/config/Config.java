package com.htc.vita.core.config;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

public abstract class Config {
    static {
        TypeRegistry.registerDefault(
                Config.class,
                DummyConfig.class
        );
    }

    public static <T extends Config> void register(Class<T> clazz) {
        TypeRegistry.register(
                Config.class,
                clazz
        );
    }

    public static Config getInstance() {
        return TypeRegistry.getInstance(Config.class);
    }

    public static <T extends Config> Config getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                Config.class,
                clazz
        );
    }

    public boolean hasKey(String key) {
        if (StringUtils.isNullOrWhiteSpace(key)) {
            return false;
        }

        boolean result = false;
        try {
            result = onHasKey(key);
        } catch (Exception e) {
            Logger.getInstance(Config.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String get(String key) {
        return get(
                key,
                null
        );
    }

    public String get(
            String key,
            String defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(key)) {
            return defaultValue;
        }

        String result = null;
        try {
            result = onGet(key);
        } catch (Exception e) {
            Logger.getInstance(Config.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    public boolean getBoolean(String key) {
        return getBoolean(
                key,
                false
        );
    }

    public boolean getBoolean(
            String key,
            boolean defaultValue) {
        return Convert.toBoolean(
                get(key),
                defaultValue
        );
    }

    public double getDouble(String key) {
        return getDouble(
                key,
                0.0D
        );
    }

    public double getDouble(
            String key,
            double defaultValue) {
        return Convert.toDouble(
                key,
                defaultValue
        );
    }

    public float getFloat(String key) {
        return getFloat(
                key,
                0.0f
        );
    }

    public float getFloat(
            String key,
            float defaultValue) {
        return (float) Convert.toDouble(
                key,
                defaultValue
        );
    }

    public int getInt(String key) {
        return getInt(
                key,
                0
        );
    }

    public int getInt(
            String key,
            int defaultValue) {
        return Convert.toInt32(
                key,
                defaultValue
        );
    }

    public long getLong(String key) {
        return getLong(
                key,
                0L
        );
    }

    public long getLong(
            String key,
            long defaultValue) {
        return Convert.toLong(
                key,
                defaultValue
        );
    }

    protected abstract boolean onHasKey(String key) throws Exception;
    protected abstract String onGet(String key) throws Exception;
}
