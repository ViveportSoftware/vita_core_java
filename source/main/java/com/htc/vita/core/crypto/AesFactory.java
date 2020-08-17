package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class AesFactory {
    private static final Map<String, AesFactory> sInstances = new HashMap<String, AesFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends AesFactory> sDefaultClass = DefaultAesFactory.class;

    public static <T extends AesFactory> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(AesFactory.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                AesFactory.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static AesFactory getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends AesFactory> AesFactory getInstance(Class<T> clazz) {
        AesFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(AesFactory.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(AesFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultAesFactory.class.getName()
            ));
            instance = new DefaultAesFactory();
        }
        return instance;
    }

    private static <T extends AesFactory> AesFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    AesFactory.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        AesFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(AesFactory.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(AesFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultAesFactory.class.getName()
            ));
            instance = new DefaultAesFactory();
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

    public Aes get() {
        return get(
                AesCipherMode.Cbc,
                AesPaddingMode.Pkcs5
        );
    }

    public Aes get(
            AesCipherMode cipherMode,
            AesPaddingMode paddingMode) {
        Aes result = null;
        try {
            result = onGet(
                    cipherMode,
                    paddingMode
            );
        } catch (Exception e) {
            Logger.getInstance(AesFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract Aes onGet(
            AesCipherMode cipherMode,
            AesPaddingMode paddingMode
    ) throws Exception;
}
