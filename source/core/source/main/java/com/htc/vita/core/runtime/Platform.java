package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class Platform {
    static {
        TypeRegistry.registerDefault(
                Platform.class,
                DummyPlatform.class
        );
    }

    public static <T extends Platform> void register(Class<T> clazz) {
        TypeRegistry.register(
                Platform.class,
                clazz
        );
    }

    public static Platform getInstance() {
        return TypeRegistry.getInstance(Platform.class);
    }

    public static <T extends Platform> Platform getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                Platform.class,
                clazz
        );
    }

    public String getMachineId() {
        String result = null;
        try {
            result = onGetMachineId();
        } catch (Exception e) {
            Logger.getInstance(Platform.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract String onGetMachineId() throws Exception;
}
