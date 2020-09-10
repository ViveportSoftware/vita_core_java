package com.htc.vita.core.preference;

import com.htc.vita.core.util.Convert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultPreferences extends Preferences {
    private Map<String, String> mProperties = new HashMap<String, String>();
    private final PreferenceStorage mPreferenceStorage;

    protected DefaultPreferences(
            String category,
            String label) {
        super(
                category,
                label
        );
        mPreferenceStorage = PreferenceStorage.getInstance()
                .setCategory(category)
                .setLabel(label);
    }

    @Override
    protected Set<String> onAllKeys() {
        return mProperties.keySet();
    }

    @Override
    protected Preferences onClear() {
        mProperties.clear();
        return this;
    }

    @Override
    protected boolean onHasKey(String key) {
        return mProperties.containsKey(key);
    }

    @Override
    protected Preferences onInitialize() {
        mProperties = mPreferenceStorage.load();
        return this;
    }

    @Override
    protected boolean onParseBoolean(
            String key,
            boolean defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toBoolean(
                mProperties.get(key),
                defaultValue
        );
    }

    @Override
    protected double onParseDouble(
            String key,
            double defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toDouble(
                mProperties.get(key),
                defaultValue
        );
    }

    @Override
    protected float onParseFloat(
            String key,
            float defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        return (float) Convert.toDouble(
                mProperties.get(key),
                defaultValue
        );
    }

    @Override
    protected int onParseInt(
            String key,
            int defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toInt32(
                mProperties.get(key),
                defaultValue
        );
    }

    @Override
    protected long onParseLong(
            String key,
            long defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toLong(
                mProperties.get(key),
                defaultValue
        );
    }

    @Override
    protected String onParseString(
            String key,
            String defaultValue) {
        if (!mProperties.containsKey(key)) {
            return defaultValue;
        }
        String result = mProperties.get(key);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    @Override
    protected Preferences onPutBoolean(
            String key,
            boolean value) {
        mProperties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutDouble(
            String key,
            double value) {
        mProperties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutFloat(
            String key,
            float value) {
        mProperties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutInt(
            String key,
            int value) {
        mProperties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutLong(
            String key,
            long value) {
        mProperties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutString(
            String key,
            String value) {
        mProperties.put(
                key,
                value
        );
        return this;
    }

    @Override
    protected boolean onSave() {
        return mPreferenceStorage.save(mProperties);
    }
}
