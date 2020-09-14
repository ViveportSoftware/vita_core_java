package com.htc.vita.core.preference;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;

import java.util.*;
import java.util.concurrent.*;

public class DefaultPreferences extends Preferences {
    private Map<String, String> mProperties = new HashMap<String, String>();

    private final ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private final Object mLock = new Object();
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
        return mProperties;
    }

    @Override
    protected Set<String> onAllKeys() {
        synchronized (mLock) {
            Map<String, String> properties = getProperties();
            if (properties == null) {
                return new HashSet<String>();
            }
            return properties.keySet();
        }
    }

    @Override
    protected Preferences onClear() {
        synchronized (mLock) {
            Map<String, String> properties = getProperties();
            if (properties != null) {
                properties.clear();
            }
        }
        return this;
    }

    @Override
    protected boolean onHasKey(String key) {
        synchronized (mLock) {
            Map<String, String> properties = getProperties();
            if (properties == null) {
                return false;
            }
            return properties.containsKey(key);
        }
    }

    @Override
    protected Preferences onInitialize() {
        synchronized (mLock) {
            mProperties = mPreferenceStorage.load();
        }
        return this;
    }

    @Override
    protected Future<Preferences> onInitializeAsync() {
        return mExecutorService.submit(new Callable<Preferences>() {
                @Override
                public Preferences call() {
                    synchronized (mLock) {
                        try {
                            mProperties = mPreferenceStorage.loadAsync().get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return DefaultPreferences.this;
                }
        });
    }

    @Override
    protected boolean onParseBoolean(
            String key,
            boolean defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected double onParseDouble(
            String key,
            double defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected float onParseFloat(
            String key,
            float defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected int onParseInt(
            String key,
            int defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected long onParseLong(
            String key,
            long defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected String onParseString(
            String key,
            String defaultValue) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutBoolean(
            String key,
            boolean value) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutDouble(
            String key,
            double value) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutFloat(
            String key,
            float value) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutInt(
            String key,
            int value) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutLong(
            String key,
            long value) {
        synchronized (mLock) {
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
    }

    @Override
    protected Preferences onPutString(
            String key,
            String value) {
        synchronized (mLock) {
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
    }

    @Override
    protected boolean onSave() {
        synchronized (mLock) {
            Map<String, String> properties = getProperties();
            if (properties == null) {
                return false;
            }
            return mPreferenceStorage.save(properties);
        }
    }

    @Override
    protected Future<Boolean> onSaveAsync() {
        return mExecutorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    synchronized (mLock) {
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
                }
        });
    }
}
