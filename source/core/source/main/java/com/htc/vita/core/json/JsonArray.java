package com.htc.vita.core.json;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class JsonArray {
    public JsonArray append(boolean value) {
        JsonArray result = this;
        try {
            result = onAppendBoolean(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(double value) {
        JsonArray result = this;
        try {
            result = onAppendDouble(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(float value) {
        JsonArray result = this;
        try {
            result = onAppendFloat(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(int value) {
        JsonArray result = this;
        try {
            result = onAppendInt(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(long value) {
        JsonArray result = this;
        try {
            result = onAppendLong(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(String value) {
        JsonArray result = this;
        try {
            result = onAppendString(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(URI value) {
        String realValue = null;
        if (value != null) {
            realValue = value.toString();
        }
        return append(realValue);
    }

    public JsonArray append(URL value) {
        String realValue = null;
        if (value != null) {
            realValue = value.toString();
        }
        return append(realValue);
    }

    public JsonArray append(JsonArray value) {
        JsonArray result = this;
        try {
            result = onAppendJsonArray(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray append(JsonObject value) {
        JsonArray result = this;
        try {
            result = onAppendJsonObject(value);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray appendAllString(List<String> data) {
        if (data == null) {
            return this;
        }

        for (String value : data) {
            append(value);
        }
        return this;
    }

    public JsonArray appendIfNotNull(String value) {
        if (value == null) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNull(URI value) {
        if (value == null) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNull(URL value) {
        if (value == null) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNull(JsonArray value) {
        if (value == null) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNull(JsonObject value) {
        if (value == null) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNullAndNotWhiteSpace(String value) {
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNullAndNotEmpty(JsonArray value) {
        if (value == null) {
            return this;
        }
        if (value.size() <= 0) {
            return this;
        }
        return append(value);
    }

    public JsonArray appendIfNotNullAndNotEmpty(JsonObject value) {
        if (value == null) {
            return this;
        }
        if (value.allKeys().size() <= 0) {
            return this;
        }
        return append(value);
    }

    public JsonArray insert(
            int index,
            boolean value) {
        JsonArray result = this;
        try {
            result = onInsertBoolean(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            double value) {
        JsonArray result = this;
        try {
            result = onInsertDouble(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            float value) {
        JsonArray result = this;
        try {
            result = onInsertFloat(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            int value) {
        JsonArray result = this;
        try {
            result = onInsertInt(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            long value) {
        JsonArray result = this;
        try {
            result = onInsertLong(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            String value) {
        JsonArray result = this;
        try {
            result = onInsertString(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            URI value) {
        String realValue = null;
        if (value != null) {
            realValue = value.toString();
        }
        return insert(
                index,
                realValue
        );
    }

    public JsonArray insert(
            int index,
            URL value) {
        String realValue = null;
        if (value != null) {
            realValue = value.toString();
        }
        return insert(
                index,
                realValue
        );
    }

    public JsonArray insert(
            int index,
            JsonArray value) {
        JsonArray result = this;
        try {
            result = onInsertJsonArray(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insert(
            int index,
            JsonObject value) {
        JsonArray result = this;
        try {
            result = onInsertJsonObject(
                    index,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonArray insertIfNotNull(
            int index,
            String value) {
        if (value == null) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNull(
            int index,
            URI value) {
        if (value == null) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNull(
            int index,
            URL value) {
        if (value == null) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNull(
            int index,
            JsonArray value) {
        if (value == null) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNull(
            int index,
            JsonObject value) {
        if (value == null) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNullAndNotWhiteSpace(
            int index,
            String value) {
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNullAndNotEmpty(
            int index,
            JsonArray value) {
        if (value == null) {
            return this;
        }
        if (value.size() <= 0) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public JsonArray insertIfNotNullAndNotEmpty(
            int index,
            JsonObject value) {
        if (value == null) {
            return this;
        }
        if (value.allKeys().size() <= 0) {
            return this;
        }
        return insert(
                index,
                value
        );
    }

    public boolean parseBoolean(int index) {
        return parseBoolean(
                index,
                false
        );
    }

    public boolean parseBoolean(
            int index,
            boolean defaultValue) {
        boolean result = defaultValue;
        try {
            result = onParseBoolean(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public double parseDouble(int index) {
        return parseDouble(
                index,
                0.0D
        );
    }

    public double parseDouble(
            int index,
            double defaultValue) {
        double result = defaultValue;
        try {
            result = onParseDouble(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public float parseFloat(int index) {
        return parseFloat(
                index,
                0.0F
        );
    }

    public float parseFloat(
            int index,
            float defaultValue) {
        float result = defaultValue;
        try {
            result = onParseFloat(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public int parseInt(int index) {
        return parseInt(
                index,
                0
        );
    }

    public int parseInt(
            int index,
            int defaultValue) {
        int result = defaultValue;
        try {
            result = onParseInt(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public long parseLong(int index) {
        return parseLong(
                index,
                0L
        );
    }

    public long parseLong(
            int index,
            long defaultValue) {
        long result = defaultValue;
        try {
            result = onParseLong(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String parseString(int index) {
        return parseString(
                index,
                null
        );
    }

    public String parseString(
            int index,
            String defaultValue) {
        String result = defaultValue;
        try {
            result = onParseString(
                    index,
                    defaultValue
            );
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public URI parseUri(int index) {
        String value = parseString(index);
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return null;
        }

        try {
            return new URI(value);
        } catch (URISyntaxException e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return null;
    }

    public URL parseUrl(int index) {
        String value = parseString(index);
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return null;
        }

        try {
            return new URL(value);
        } catch (MalformedURLException e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return null;
    }

    public JsonArray parseJsonArray(int index) {
        JsonArray result = null;
        try {
            result = onParseJsonArray(index);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public JsonObject parseJsonObject(int index) {
        JsonObject result = null;
        try {
            result = onParseJsonObject(index);
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public int size() {
        int result = 0;
        try {
            result = onSize();
        } catch (Exception e) {
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        if (result >= 0) {
            return result;
        }
        Logger.getInstance(JsonArray.class.getSimpleName()).fatal(StringUtils.rootLocaleFormat(
                "Size abnormal: %d",
                result
        ));
        return 0;
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
            Logger.getInstance(JsonArray.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public List<String> toStringList() {
        return toStringList(false);
    }

    public List<String> toStringList(boolean shouldKeepEmptyElement) {
        List<String> result = new ArrayList<String>();
        int jsonArraySize = size();
        for (int i = 0; i < jsonArraySize; i++) {
            String value = parseString(i);
            if (StringUtils.isNullOrEmpty(value) && !shouldKeepEmptyElement) {
                continue;
            }
            result.add(value);
        }
        return result;
    }

    public Set<String> toStringSet() {
        return toStringSet(false);
    }

    public Set<String> toStringSet(boolean shouldKeepEmptyElement) {
        Set<String> result = new HashSet<String>();
        int jsonArraySize = size();
        for (int i = 0; i < jsonArraySize; i++) {
            String value = parseString(i);
            if (StringUtils.isNullOrEmpty(value) && !shouldKeepEmptyElement) {
                continue;
            }
            result.add(value);
        }
        return result;
    }

    protected abstract JsonArray onAppendBoolean(boolean value) throws Exception;
    protected abstract JsonArray onAppendDouble(double value) throws Exception;
    protected abstract JsonArray onAppendFloat(float value) throws Exception;
    protected abstract JsonArray onAppendInt(int value) throws Exception;
    protected abstract JsonArray onAppendLong(long value) throws Exception;
    protected abstract JsonArray onAppendString(String value) throws Exception;
    protected abstract JsonArray onAppendJsonArray(JsonArray value) throws Exception;
    protected abstract JsonArray onAppendJsonObject(JsonObject value) throws Exception;
    protected abstract JsonArray onInsertBoolean(
            int index,
            boolean value
    ) throws Exception;
    protected abstract JsonArray onInsertDouble(
            int index,
            double value
    ) throws Exception;
    protected abstract JsonArray onInsertFloat(
            int index,
            float value
    ) throws Exception;
    protected abstract JsonArray onInsertInt(
            int index,
            int value
    ) throws Exception;
    protected abstract JsonArray onInsertLong(
            int index,
            long value
    ) throws Exception;
    protected abstract JsonArray onInsertString(
            int index,
            String value
    ) throws Exception;
    protected abstract JsonArray onInsertJsonArray(
            int index,
            JsonArray value
    ) throws Exception;
    protected abstract JsonArray onInsertJsonObject(
            int index,
            JsonObject value
    ) throws Exception;
    protected abstract boolean onParseBoolean(
            int index,
            boolean defaultValue
    ) throws Exception;
    protected abstract double onParseDouble(
            int index,
            double defaultValue
    ) throws Exception;
    protected abstract float onParseFloat(
            int index,
            float defaultValue
    ) throws Exception;
    protected abstract int onParseInt(
            int index,
            int defaultValue
    ) throws Exception;
    protected abstract long onParseLong(
            int index,
            long defaultValue
    ) throws Exception;
    protected abstract String onParseString(
            int index,
            String defaultValue
    ) throws Exception;
    protected abstract JsonArray onParseJsonArray(int index) throws Exception;
    protected abstract JsonObject onParseJsonObject(int index) throws Exception;
    protected abstract int onSize() throws Exception;
    protected abstract String onToPrettyString() throws Exception;
}
