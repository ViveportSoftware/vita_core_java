package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class WebUserAgentFactory {
    private static final Map<String, WebUserAgentFactory> sInstances = new HashMap<String, WebUserAgentFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends WebUserAgentFactory> sDefaultClass = DummyWebUserAgentFactory.class;

    public static <T extends WebUserAgentFactory> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                WebUserAgentFactory.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static WebUserAgentFactory getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends WebUserAgentFactory> WebUserAgentFactory getInstance(Class<T> clazz) {
        WebUserAgentFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyWebUserAgentFactory.class.getName()
            ));
            instance = new DummyWebUserAgentFactory();
        }
        return instance;
    }

    private static <T extends WebUserAgentFactory> WebUserAgentFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    WebUserAgentFactory.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        WebUserAgentFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyWebUserAgentFactory.class.getName()
            ));
            instance = new DummyWebUserAgentFactory();
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

    public WebUserAgent getWebUserAgent() {
        WebUserAgent result = null;
        try {
            result = onGetWebUserAgent();
        } catch (Exception e) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract WebUserAgent onGetWebUserAgent();
}
