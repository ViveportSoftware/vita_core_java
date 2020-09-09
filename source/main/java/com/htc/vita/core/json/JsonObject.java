package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    public boolean parseBooleanIfKeyExist(String key) {
        return parseBooleanIfKeyExist(
                key,
                false
        );
    }

    public boolean parseBooleanIfKeyExist(
            String key,
            boolean defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseBoolean(
                key,
                defaultValue
        );
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

    public double parseDoubleIfKeyExist(String key) {
        return parseDoubleIfKeyExist(
                key,
                0.0D
        );
    }

    public double parseDoubleIfKeyExist(
            String key,
            double defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseDouble(
                key,
                defaultValue
        );
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

    public float parseFloatIfKeyExist(String key) {
        return parseFloatIfKeyExist(
                key,
                0.0F
        );
    }

    public float parseFloatIfKeyExist(
            String key,
            float defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseFloat(
                key,
                defaultValue
        );
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

    public int parseIntIfKeyExist(String key) {
        return parseIntIfKeyExist(
                key,
                0
        );
    }

    public int parseIntIfKeyExist(
            String key,
            int defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseInt(
                key,
                defaultValue
        );
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

    public long parseLongIfKeyExist(String key) {
        return parseLongIfKeyExist(
                key,
                0L
        );
    }

    public long parseLongIfKeyExist(
            String key,
            long defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseLong(
                key,
                defaultValue
        );
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

    public String parseStringIfKeyExist(String key) {
        return parseStringIfKeyExist(
                key,
                null
        );
    }

    public String parseStringIfKeyExist(
            String key,
            String defaultValue) {
        if (!hasKey(key)) {
            return defaultValue;
        }
        return parseString(
                key,
                defaultValue
        );
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

    public JsonArray parseJsonArrayIfKeyExist(String key) {
        if (!hasKey(key)) {
            return null;
        }
        return parseJsonArray(key);
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

    public JsonObject parseJsonObjectIfKeyExist(String key) {
        if (!hasKey(key)) {
            return null;
        }
        return parseJsonObject(key);
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

    public JsonObject putAllString(Map<String, String> data) {
        if (data == null) {
            return this;
        }

        for (String key: data.keySet()) {
            if (key == null) {
                continue;
            }

            put(
                    key,
                    data.get(key)
            );
        }
        return this;
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

    public Map<String, String> toStringMap() {
        Map<String, String> result = new HashMap<String, String>();
        for (String key : allKeys()) {
            if (StringUtils.isNullOrWhiteSpace(key)) {
                continue;
            }
            result.put(
                    key,
                    parseString(key)
            );
        }
        return result;
    }

    protected abstract Set<String> onAllKeys() throws Exception;
    protected abstract boolean onHasKey(String key) throws Exception;
    protected abstract boolean onParseBoolean(
            String key,
            boolean defaultValue
    ) throws Exception;
    protected abstract double onParseDouble(
            String key,
            double defaultValue
    ) throws Exception;
    protected abstract float onParseFloat(
            String key,
            float defaultValue
    ) throws Exception;
    protected abstract int onParseInt(
            String key,
            int defaultValue
    ) throws Exception;
    protected abstract long onParseLong(
            String key,
            long defaultValue
    ) throws Exception;
    protected abstract String onParseString(
            String key,
            String defaultValue
    ) throws Exception;
    protected abstract JsonArray onParseJsonArray(String key) throws Exception;
    protected abstract JsonObject onParseJsonObject(String key) throws Exception;
    protected abstract JsonObject onPutBoolean(
            String key,
            boolean value
    ) throws Exception;
    protected abstract JsonObject onPutDouble(
            String key,
            double value
    ) throws Exception;
    protected abstract JsonObject onPutFloat(
            String key,
            float value
    ) throws Exception;
    protected abstract JsonObject onPutInt(
            String key,
            int value
    ) throws Exception;
    protected abstract JsonObject onPutLong(
            String key,
            long value
    ) throws Exception;
    protected abstract JsonObject onPutString(
            String key,
            String value
    ) throws Exception;
    protected abstract JsonObject onPutJsonArray(
            String key,
            JsonArray value
    ) throws Exception;
    protected abstract JsonObject onPutJsonObject(
            String key,
            JsonObject value
    ) throws Exception;
    protected abstract String onToPrettyString() throws Exception;
}
