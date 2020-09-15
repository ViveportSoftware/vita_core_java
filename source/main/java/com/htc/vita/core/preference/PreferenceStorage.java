package com.htc.vita.core.preference;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public abstract class PreferenceStorage {
    private String mCategory = "Vita";
    private String mLabel = "default";

    static {
        TypeRegistry.registerDefault(
                PreferenceStorage.class,
                DummyPreferenceStorage.class
        );
    }

    public static <T extends PreferenceStorage> void register(Class<T> clazz) {
        TypeRegistry.register(
                PreferenceStorage.class,
                clazz
        );
    }

    public static PreferenceStorage getInstance() {
        return TypeRegistry.getInstance(PreferenceStorage.class);
    }

    public static <T extends PreferenceStorage> PreferenceStorage getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                PreferenceStorage.class,
                clazz
        );
    }

    public String getCategory() {
        return mCategory;
    }

    public String getLabel() {
        return mLabel;
    }

    public Map<String, String> load() {
        Map<String, String> result = null;
        try {
            result = onLoad();
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new HashMap<String, String>();
        }
        return result;
    }

    public Future<Map<String, String>> loadAsync() {
        Future<Map<String, String>> result = null;
        try {
            result = onLoadAsync();
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean save(Map<String, String> data) {
        boolean result = false;
        try {
            result = onSave(data);
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Future<Boolean> saveAsync(Map<String, String> data) {
        Future<Boolean> result = null;
        try {
            result = onSaveAsync(data);
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public PreferenceStorage setCategory(String category) {
        if (!StringUtils.isNullOrWhiteSpace(category)) {
            mCategory = category;
        }
        return this;
    }

    public PreferenceStorage setLabel(String label) {
        if (!StringUtils.isNullOrWhiteSpace(label)) {
            mLabel = label;
        }
        return this;
    }

    protected abstract Map<String, String> onLoad() throws Exception;
    protected abstract Future<Map<String, String>> onLoadAsync() throws Exception;
    protected abstract boolean onSave(Map<String, String> data) throws Exception;
    protected abstract Future<Boolean> onSaveAsync(Map<String, String> data) throws Exception;
}
