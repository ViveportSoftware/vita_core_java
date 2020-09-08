package com.htc.vita.core.preference;

public class DummyPreferenceFactory extends PreferenceFactory {
    @Override
    protected Preferences onLoadPreferences(String label) {
        return new DummyPreferences(
                "dummyCategory",
                label
        );
    }
}
