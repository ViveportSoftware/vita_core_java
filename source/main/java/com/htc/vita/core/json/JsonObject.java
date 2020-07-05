package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public abstract class JsonObject {
    public Set<String> allKeys() {
        Set<String> result = null;
        try {
            result = onAllKeys();
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new HashSet<String>();
        }
        return result;
    }

    public boolean hasKey(String key) {
        boolean result = false;
        try {
            result = onHasKey(key);
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean parseBoolean(String key) {
        return parseBoolean(
                key,
                false
        );
    }

    public boolean parseBoolean(
            String key,
            boolean defaultValue) {
        boolean result = defaultValue;
        try {
            result = onParseBoolean(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public double parseDouble(String key) {
        return parseDouble(
                key,
                0.0D
        );
    }

    public double parseDouble(
            String key,
            double defaultValue) {
        double result = defaultValue;
        try {
            result = onParseDouble(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public float parseFloat(String key) {
        return parseFloat(
                key,
                0.0F
        );
    }

    public float parseFloat(
            String key,
            float defaultValue) {
        float result = defaultValue;
        try {
            result = onParseFloat(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public int parseInt(String key) {
        return parseInt(
                key,
                0
        );
    }

    public int parseInt(
            String key,
            int defaultValue) {
        int result = defaultValue;
        try {
            result = onParseInt(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public long parseLong(String key) {
        return parseLong(
                key,
                0L
        );
    }

    public long parseLong(
            String key,
            long defaultValue) {
        long result = defaultValue;
        try {
            result = onParseLong(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String parseString(String key) {
        return parseString(
                key,
                null
        );
    }

    public String parseString(
            String key,
            String defaultValue) {
        String result = defaultValue;
        try {
            result = onParseString(
                    key,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray parseJsonArray(String key) {
        JsonArray result = null;
        try {
            result = onParseJsonArray(key);
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject parseJsonObject(String key) {
        JsonObject result = null;
        try {
            result = onParseJsonObject(key);
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            boolean value) {
        JsonObject result = this;
        try {
            result = onPutBoolean(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            double value) {
        JsonObject result = this;
        try {
            result = onPutDouble(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            float value) {
        JsonObject result = this;
        try {
            result = onPutFloat(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            int value) {
        JsonObject result = this;
        try {
            result = onPutInt(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            long value)
    {
        JsonObject result = this;
        try {
            result = onPutLong(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            String value) {
        JsonObject result = this;
        try {
            result = onPutString(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            JsonArray value) {
        JsonObject result = this;
        try {
            result = onPutJsonArray(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject put(
            String key,
            JsonObject value) {
        JsonObject result = this;
        try {
            result = onPutJsonObject(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject putIfNotNull(
            String key,
            String value) {
        if (value == null) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public JsonObject putIfNotNull(
            String key,
            JsonArray value) {
        if (value == null) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public JsonObject putIfNotNull(
            String key,
            JsonObject value) {
        if (value == null) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public JsonObject putIfNotNullAndNotWhiteSpace(
            String key,
            String value) {
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public JsonObject putIfNotNullAndNotEmpty(
            String key,
            JsonArray value) {
        if (value == null)
        {
            return this;
        }
        if (value.size() <= 0)
        {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public JsonObject putIfNotNullAndNotEmpty(
            String key,
            JsonObject value)
    {
        if (value == null)
        {
            return this;
        }
        if (value.allKeys().size() <= 0)
        {
            return this;
        }
        return put(
                key,
                value
        );
    }

    public String toPrettyString() {
        String result = "";
        try {
            result = onToPrettyString();
            if (result == null) {
                result = "";
            }
            result = result.trim();
        } catch (Exception e) {
            Logger.getInstance(JsonObject.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract Set<String> onAllKeys();
    protected abstract boolean onHasKey(String key);
    protected abstract boolean onParseBoolean(
            String key,
            boolean defaultValue
    );
    protected abstract double onParseDouble(
            String key,
            double defaultValue
    );
    protected abstract float onParseFloat(
            String key,
            float defaultValue
    );
    protected abstract int onParseInt(
            String key,
            int defaultValue
    );
    protected abstract long onParseLong(
            String key,
            long defaultValue
    );
    protected abstract String onParseString(
            String key,
            String defaultValue
    );
    protected abstract JsonArray onParseJsonArray(String key);
    protected abstract JsonObject onParseJsonObject(String key);
    protected abstract JsonObject onPutBoolean(
            String key,
            boolean value
    );
    protected abstract JsonObject onPutDouble(
            String key,
            double value
    );
    protected abstract JsonObject onPutFloat(
            String key,
            float value
    );
    protected abstract JsonObject onPutInt(
            String key,
            int value
    );
    protected abstract JsonObject onPutLong(
            String key,
            long value
    );
    protected abstract JsonObject onPutString(
            String key,
            String value
    );
    protected abstract JsonObject onPutJsonArray(
            String key,
            JsonArray value
    );
    protected abstract JsonObject onPutJsonObject(
            String key,
            JsonObject value
    );
    protected abstract String onToPrettyString();
}
