package com.htc.vita.core.preference;

import com.htc.vita.core.internal.TaskRunner;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class DummyPreferenceStorage extends PreferenceStorage {
    private final Object mLock = new Object();

    public DummyPreferenceStorage() {
        Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                PreferenceStorage.class.getSimpleName()
        ));
    }

    private Map<String, String> doLoad(
            String category,
            String label) {
        synchronized (mLock) {
            Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Try to load data on thread[%s] from category: \"%s\", label: \"%s\"",
                    Thread.currentThread().getId(),
                    category,
                    label
            ));
            return null;
        }
    }

    private boolean doSave(
            String category,
            String label,
            Map<String, String> data) {
        synchronized (mLock) {
            Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Try to save data on thread[%s] to category: \"%s\", label: \"%s\"",
                    Thread.currentThread().getId(),
                    category,
                    label
            ));
            return false;
        }
    }

    @Override
    protected Map<String, String> onLoad(
            String category,
            String label) {
        return doLoad(
                category,
                label
        );
    }

    @Override
    protected Future<Map<String, String>> onLoadAsync(
            final String category,
            final String label) {
        return TaskRunner.submit(new Callable<Map<String, String>>() {
                @Override
                public Map<String, String> call() {
                    return doLoad(
                            category,
                            label
                    );
                }
        });
    }

    @Override
    protected boolean onSave(
            String category,
            String label,
            Map<String, String> data) {
        return doSave(
                category,
                label,
                data
        );
    }

    @Override
    protected Future<Boolean> onSaveAsync(
            final String category,
            final String label,
            final Map<String, String> data) {
        return TaskRunner.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    return doSave(
                            category,
                            label,
                            Collections.unmodifiableMap(data)
                    );
                }
        });
    }
}
