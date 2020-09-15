package com.htc.vita.core.preference;

import java.util.concurrent.Future;

public class DefaultPreferenceFactory extends PreferenceFactory {
    private static final String DEFAULT_CATEGORY = "Vita";

    @Override
    protected Preferences onLoadPreferences(String label) {
        return new DefaultPreferences(
                DEFAULT_CATEGORY,
                label
        ).initialize();
    }

    @Override
    protected Future<Preferences> onLoadPreferencesAsync(String label) {
        return new DefaultPreferences(
                DEFAULT_CATEGORY,
                label
        ).initializeAsync();
    }
}
