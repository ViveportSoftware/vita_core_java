package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyPlatform extends Platform {
    public DummyPlatform() {
        Logger.getInstance(DummyPlatform.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                Platform.class.getSimpleName()
        ));
    }

    @Override
    protected String onGetMachineId() {
        return null;
    }
}
