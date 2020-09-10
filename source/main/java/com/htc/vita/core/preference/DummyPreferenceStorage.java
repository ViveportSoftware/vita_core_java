package com.htc.vita.core.preference;

import com.htc.vita.core.log.Logger;

import java.util.Locale;
import java.util.Map;

public class DummyPreferenceStorage extends PreferenceStorage {
    public DummyPreferenceStorage() {
        Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).error(String.format(
                Locale.ROOT,
                "You are using dummy %s instance!!",
                PreferenceStorage.class.getSimpleName()
        ));
    }

    @Override
    protected Map<String, String> onLoad() {
        Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(String.format(
                Locale.ROOT,
                "Try to load data from category: \"%s\", label: \"%s\"",
                getCategory(),
                getLabel()
        ));
        return null;
    }

    @Override
    protected boolean onSave(Map<String, String> data) {
        Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(String.format(
                Locale.ROOT,
                "Try to save data to category: \"%s\", label: \"%s\"",
                getCategory(),
                getLabel()
        ));
        return false;
    }
}
