package com.htc.vita.core.preference;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;

import java.util.*;
import java.util.concurrent.Future;

public class DefaultPreferences extends Preferences {
    private static boolean sUseAsync = false;

    private Map<String, String> mProperties = new HashMap<String, String>();
    private Future<Map<String, String>> mPropertiesFuture;
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

    private Map<String, String> getProperties() {
        if (!sUseAsync) {
            return mProperties;
        }

        if (mPropertiesFuture == null) {
            return null;
        }
        Map<String, String> result = null;
        try {
            result = mPropertiesFuture.get();
        } catch (Exception e) {
            Logger.getInstance(DefaultPreferences.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    @Override
    protected Set<String> onAllKeys() {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return new HashSet<String>();
        }
        return properties.keySet();
    }

    @Override
    protected Preferences onClear() {
        Map<String, String> properties = getProperties();
        if (properties != null) {
            properties.clear();
        }
        return this;
    }

    @Override
    protected boolean onHasKey(String key) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return false;
        }
        return properties.containsKey(key);
    }

    @Override
    protected Preferences onInitialize() {
        if (!sUseAsync) {
            mProperties = mPreferenceStorage.load();
        } else {
            mPropertiesFuture = mPreferenceStorage.loadAsync();
        }
        return this;
    }

    @Override
    protected boolean onParseBoolean(
            String key,
            boolean defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toBoolean(
                properties.get(key),
                defaultValue
        );
    }

    @Override
    protected double onParseDouble(
            String key,
            double defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toDouble(
                properties.get(key),
                defaultValue
        );
    }

    @Override
    protected float onParseFloat(
            String key,
            float defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return (float) Convert.toDouble(
                properties.get(key),
                defaultValue
        );
    }

    @Override
    protected int onParseInt(
            String key,
            int defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toInt32(
                properties.get(key),
                defaultValue
        );
    }

    @Override
    protected long onParseLong(
            String key,
            long defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        return Convert.toLong(
                properties.get(key),
                defaultValue
        );
    }

    @Override
    protected String onParseString(
            String key,
            String defaultValue) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return defaultValue;
        }
        if (!properties.containsKey(key)) {
            return defaultValue;
        }
        String result = properties.get(key);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    @Override
    protected Preferences onPutBoolean(
            String key,
            boolean value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutDouble(
            String key,
            double value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutFloat(
            String key,
            float value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutInt(
            String key,
            int value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutLong(
            String key,
            long value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected Preferences onPutString(
            String key,
            String value) {
        Map<String, String> properties = getProperties();
        if (properties == null) {
            return this;
        }
        properties.put(
                key,
                "" + value
        );
        return this;
    }

    @Override
    protected boolean onSave() {
        if (!sUseAsync) {
            return mPreferenceStorage.save(mProperties);
        }

        Map<String, String> properties = getProperties();
        if (properties == null) {
            return false;
        }
        boolean result = false;
        try {
            result = mPreferenceStorage.saveAsync(properties).get();
        } catch (Exception e) {
            Logger.getInstance(DefaultPreferences.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public static void useAsync(boolean useAsync) {
        if (sUseAsync != useAsync) {
            sUseAsync = useAsync;
            System.err.printf(
                    Locale.ROOT,
                    "Set async method usage to %s%n",
                    sUseAsync
            );
        }
    }
}
