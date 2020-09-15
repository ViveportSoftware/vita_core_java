package com.htc.vita.core.preference;

import com.htc.vita.core.concurrent.InternalTaskRunner;
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

    private Map<String, String> doLoad() {
        synchronized (mLock) {
            Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Try to load data on thread[%s] from category: \"%s\", label: \"%s\"",
                    Thread.currentThread().getId(),
                    getCategory(),
                    getLabel()
            ));
            return null;
        }
    }

    private boolean doSave(Map<String, String> data) {
        synchronized (mLock) {
            Logger.getInstance(DummyPreferenceStorage.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                    "Try to save data on thread[%s] to category: \"%s\", label: \"%s\"",
                    Thread.currentThread().getId(),
                    getCategory(),
                    getLabel()
            ));
            return false;
        }
    }

    @Override
    protected Map<String, String> onLoad() {
        return doLoad();
    }

    @Override
    protected Future<Map<String, String>> onLoadAsync() {
        return InternalTaskRunner.submit(new Callable<Map<String, String>>() {
                @Override
                public Map<String, String> call() {
                    return doLoad();
                }
        });
    }

    @Override
    protected boolean onSave(Map<String, String> data) {
        return doSave(data);
    }

    @Override
    protected Future<Boolean> onSaveAsync(final Map<String, String> data) {
        return InternalTaskRunner.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    return doSave(Collections.unmodifiableMap(data));
                }
        });
    }
}
