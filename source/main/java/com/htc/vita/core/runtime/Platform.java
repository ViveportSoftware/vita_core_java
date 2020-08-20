package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class Platform {
    private static final Map<String, Platform> sInstances = new HashMap<String, Platform>();
    private static final Object sInstancesLock = new Object();

    private static Class<? extends Platform> sDefaultClass = DummyPlatform.class;

    public static <T extends Platform> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(Platform.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                Platform.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static Platform getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends Platform> Platform getInstance(Class<T> clazz) {
        Platform instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(Platform.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(Platform.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyPlatform.class.getName()
            ));
            instance = new DummyPlatform();
        }
        return instance;
    }

    private static <T extends Platform> Platform doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    Platform.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        Platform instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(Platform.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    key
            ));
            try {
                Constructor<T> constructor = clazz.getConstructor();
                instance = constructor.newInstance();
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(Platform.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyPlatform.class.getName()
            ));
            instance = new DummyPlatform();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(
                        key,
                        instance
                );
            }
        }
        return instance;
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
