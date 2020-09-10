package com.htc.vita.core.preference;

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
}
