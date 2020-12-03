package com.htc.vita.core.preference;

import com.htc.vita.core.concurrent.CompletedFuture;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

public abstract class Preferences {
    private String mCategory = "";
    private String mLabel = "";

    protected Preferences(
            String category,
            String label) {
        if (!StringUtils.isNullOrWhiteSpace(category)) {
            mCategory = category;
        }
        if (!StringUtils.isNullOrWhiteSpace(label)) {
            mLabel = label;
        }
    }

    public Set<String> allKeys() {
        Set<String> result = null;
        try {
            result = onAllKeys();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new HashSet<String>();
        }
        return result;
    }

    public Preferences clear() {
        Preferences result = null;
        try {
            result = onClear();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public boolean commit() {
        boolean result = false;
        try {
            result = onSave();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Future<Boolean> commitAsync() {
        Future<Boolean> result = null;
        try {
            result = onSaveAsync();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new CompletedFuture<Boolean>(false);
        }
        return result;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getLabel() {
        return mLabel;
    }

    public boolean hasKey(String key) {
        if (key == null) {
            return false;
        }

        boolean result = false;
        try {
            result = onHasKey(key);
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Preferences initialize() {
        Preferences result = null;
        try {
            result = onInitialize();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Future<Preferences> initializeAsync() {
        Future<Preferences> result = null;
        try {
            result = onInitializeAsync();
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Preferences put(
            String key,
            boolean value) {
        Preferences result = null;
        try {
            result = onPutBoolean(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Preferences put(
            String key,
            double value) {
        Preferences result = null;
        try {
            result = onPutDouble(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Preferences put(
            String key,
            float value) {
        Preferences result = null;
        try {
            result = onPutFloat(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Preferences put(
            String key,
            int value) {
        Preferences result = null;
        try {
            result = onPutInt(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Preferences put(
            String key,
            long value) {
        Preferences result = null;
        try {
            result = onPutLong(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public Preferences put(
            String key,
            String value) {
        Preferences result = null;
        try {
            result = onPutString(
                    key,
                    value
            );
        } catch (Exception e) {
            Logger.getInstance(Preferences.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    protected abstract Set<String> onAllKeys() throws Exception;
    protected abstract Preferences onClear() throws Exception;
    protected abstract boolean onHasKey(String key) throws Exception;
    protected abstract Preferences onInitialize() throws Exception;
    protected abstract Future<Preferences> onInitializeAsync() throws Exception;
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
    protected abstract Preferences onPutBoolean(
            String key,
            boolean value
    ) throws Exception;
    protected abstract Preferences onPutDouble(
            String key,
            double value
    ) throws Exception;
    protected abstract Preferences onPutFloat(
            String key,
            float value
    ) throws Exception;
    protected abstract Preferences onPutInt(
            String key,
            int value
    ) throws Exception;
    protected abstract Preferences onPutLong(
            String key,
            long value
    ) throws Exception;
    protected abstract Preferences onPutString(
            String key,
            String value
    ) throws Exception;
    protected abstract boolean onSave() throws Exception;
    protected abstract Future<Boolean> onSaveAsync() throws Exception;
}
