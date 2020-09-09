package com.htc.vita.core.preference;

public class DummyPreferenceFactory extends PreferenceFactory {
    @Override
    protected Preferences onLoadPreferences(
            String category,
            String label) {
        return new DummyPreferences(
                category,
                label
        );
    }
}
