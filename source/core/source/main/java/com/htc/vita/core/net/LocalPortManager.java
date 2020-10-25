package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class LocalPortManager {
    static {
        TypeRegistry.registerDefault(
                LocalPortManager.class,
                DefaultLocalPortManager.class
        );
    }

    public static <T extends LocalPortManager> void register(Class<T> clazz) {
        TypeRegistry.register(
                LocalPortManager.class,
                clazz
        );
    }

    public static LocalPortManager getInstance() {
        return TypeRegistry.getInstance(LocalPortManager.class);
    }

    public static <T extends LocalPortManager> LocalPortManager getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                LocalPortManager.class,
                clazz
        );
    }

    public int getRandomUnusedPort() {
        int result = -1;
        try {
            result = onGetRandomUnusedPort();
        } catch (Exception e) {
            Logger.getInstance(LocalPortManager.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract int onGetRandomUnusedPort() throws Exception;
}
