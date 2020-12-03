package com.htc.vita.core.preference;

import com.htc.vita.core.concurrent.CompletedFuture;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public abstract class PreferenceStorage {
    private static final String DEFAULT_CATEGORY = "Vita";
    private static final String DEFAULT_LABEL = "default";

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

    public Map<String, String> load(
            String category,
            String label) {
        String realCategory = category;
        if (StringUtils.isNullOrWhiteSpace(realCategory)) {
            realCategory = DEFAULT_CATEGORY;
        }
        String realLabel = label;
        if (StringUtils.isNullOrWhiteSpace(realLabel)) {
            realLabel = DEFAULT_LABEL;
        }

        Map<String, String> result = null;
        try {
            result = onLoad(
                    realCategory,
                    realLabel
            );
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new HashMap<String, String>();
        }
        return result;
    }

    public Future<Map<String, String>> loadAsync(
            String category,
            String label) {
        String realCategory = category;
        if (StringUtils.isNullOrWhiteSpace(realCategory)) {
            realCategory = DEFAULT_CATEGORY;
        }
        String realLabel = label;
        if (StringUtils.isNullOrWhiteSpace(realLabel)) {
            realLabel = DEFAULT_LABEL;
        }

        Future<Map<String, String>> result = null;
        try {
            result = onLoadAsync(
                    realCategory,
                    realLabel
            );
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new CompletedFuture<Map<String, String>>(new HashMap<String, String>());
        }
        return result;
    }

    public boolean save(
            String category,
            String label,
            Map<String, String> data) {
        String realCategory = category;
        if (StringUtils.isNullOrWhiteSpace(realCategory)) {
            realCategory = DEFAULT_CATEGORY;
        }
        String realLabel = label;
        if (StringUtils.isNullOrWhiteSpace(realLabel)) {
            realLabel = DEFAULT_LABEL;
        }

        boolean result = false;
        try {
            result = onSave(
                    realCategory,
                    realLabel,
                    data
            );
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public Future<Boolean> saveAsync(
            String category,
            String label,
            Map<String, String> data) {
        String realCategory = category;
        if (StringUtils.isNullOrWhiteSpace(realCategory)) {
            realCategory = DEFAULT_CATEGORY;
        }
        String realLabel = label;
        if (StringUtils.isNullOrWhiteSpace(realLabel)) {
            realLabel = DEFAULT_LABEL;
        }

        Future<Boolean> result = null;
        try {
            result = onSaveAsync(
                    realCategory,
                    realLabel,
                    data
            );
        } catch (Exception e) {
            Logger.getInstance(PreferenceStorage.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract Map<String, String> onLoad(
            String category,
            String label
    ) throws Exception;
    protected abstract Future<Map<String, String>> onLoadAsync(
            String category,
            String label
    ) throws Exception;
    protected abstract boolean onSave(
            String category,
            String label,
            Map<String, String> data
    ) throws Exception;
    protected abstract Future<Boolean> onSaveAsync(
            String category,
            String label,
            Map<String, String> data
    ) throws Exception;
}
