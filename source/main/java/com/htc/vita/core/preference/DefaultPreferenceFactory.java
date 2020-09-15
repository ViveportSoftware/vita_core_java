package com.htc.vita.core.preference;

import java.util.concurrent.Future;

public class DefaultPreferenceFactory extends PreferenceFactory {
    @Override
    protected Preferences onLoadPreferences(
            String category,
            String label) {
        return new DefaultPreferences(
                category,
                label
        ).initialize();
    }

    @Override
    protected Future<Preferences> onLoadPreferencesAsync(
            String category,
            String label) {
        return new DefaultPreferences(
                category,
                label
        ).initializeAsync();
    }
}
