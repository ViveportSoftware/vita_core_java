package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class AesFactory {
    static {
        TypeRegistry.registerDefault(
                AesFactory.class,
                DefaultAesFactory.class
        );
    }

    public static <T extends AesFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                AesFactory.class,
                clazz
        );
    }

    public static AesFactory getInstance() {
        return TypeRegistry.getInstance(AesFactory.class);
    }

    public static <T extends AesFactory> AesFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                AesFactory.class,
                clazz
        );
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
