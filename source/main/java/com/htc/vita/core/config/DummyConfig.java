package com.htc.vita.core.config;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.Set;

public class DummyConfig extends Config {
    public DummyConfig() {
        Logger.getInstance(DummyConfig.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                Config.class.getSimpleName()
        ));
    }

    @Override
    protected Set<String> onAllKeys() {
        return null;
    }

    @Override
    protected String onGet(String key) {
        return null;
    }

    @Override
    protected boolean onHasKey(String key) {
        return false;
    }
}
