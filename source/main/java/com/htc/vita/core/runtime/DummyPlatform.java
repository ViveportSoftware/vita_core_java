package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;

public class DummyPlatform extends Platform {
    public DummyPlatform() {
        Logger.getInstance(DummyPlatform.class.getSimpleName()).error(String.format(
                "You are using dummy %s instance!!",
                Platform.class.getSimpleName()
        ));
    }

    @Override
    protected String onGetMachineId() {
        return null;
    }
}
