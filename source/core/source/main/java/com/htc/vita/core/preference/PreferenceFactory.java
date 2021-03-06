package com.htc.vita.core.preference;

import com.htc.vita.core.concurrent.CompletedFuture;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

import java.util.concurrent.Future;

public abstract class PreferenceFactory {
    static {
        TypeRegistry.registerDefault(
                PreferenceFactory.class,
                DefaultPreferenceFactory.class
        );
    }

    public static <T extends PreferenceFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                PreferenceFactory.class,
                clazz
        );
    }

    public static PreferenceFactory getInstance() {
        return TypeRegistry.getInstance(PreferenceFactory.class);
    }

    public static <T extends PreferenceFactory> PreferenceFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                PreferenceFactory.class,
                clazz
        );
    }

    public Preferences loadPreferences() {
        return loadPreferences("");
    }

    public Preferences loadPreferences(String label) {
        String preferenceLabel = label;
        if (StringUtils.isNullOrWhiteSpace(preferenceLabel)) {
            preferenceLabel = "default";
        }

        Preferences result = null;
        try {
            result = onLoadPreferences(preferenceLabel);
        } catch (Exception e) {
            Logger.getInstance(PreferenceFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Future<Preferences> loadPreferencesAsync() {
        return loadPreferencesAsync("");
    }

    public Future<Preferences> loadPreferencesAsync(String label) {
        String preferenceLabel = label;
        if (StringUtils.isNullOrWhiteSpace(preferenceLabel)) {
            preferenceLabel = "default";
        }

        Future<Preferences> result = null;
        try {
            result = onLoadPreferencesAsync(preferenceLabel);
        } catch (Exception e) {
            Logger.getInstance(PreferenceFactory.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new CompletedFuture<Preferences>(null);
        }
        return result;
    }

    protected abstract Preferences onLoadPreferences(String label) throws Exception;
    protected abstract Future<Preferences> onLoadPreferencesAsync(String label) throws Exception;
}
