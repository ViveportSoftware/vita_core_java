package com.htc.vita.core.preference;

import java.util.Map;

public class DummyPreferenceStorage extends PreferenceStorage {
    @Override
    protected Map<String, String> onLoad() {
        return null;
    }

    @Override
    protected boolean onSave(Map<String, String> data) {
        return false;
    }
}
