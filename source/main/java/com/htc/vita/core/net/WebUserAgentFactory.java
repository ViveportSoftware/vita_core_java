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
        sDefaultClass = clazz;
        System.err.println("Registered default " + WebUserAgentFactory.class.getName() + " type to " + sDefaultClass.getName());
    }

    public static WebUserAgentFactory getInstance() {
        WebUserAgentFactory instance;
        try {
            instance = doGetInstance(sDefaultClass);
        } catch (Exception e) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).fatal("Instance initialization error: " + e);
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info("Initializing " + DummyWebUserAgentFactory.class.getName() + "...");
            instance = new DummyWebUserAgentFactory();
        }
        return instance;
    }

    public static <T extends WebUserAgentFactory> WebUserAgentFactory getInstance(Class<T> clazz) {
        WebUserAgentFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).fatal("Instance initialization error: " + e);
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info("Initializing " + DummyWebUserAgentFactory.class.getName() + "...");
            instance = new DummyWebUserAgentFactory();
        }
        return instance;
    }

    private static <T extends WebUserAgentFactory> WebUserAgentFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Invalid argument to get %s instance", WebUserAgentFactory.class.getName()));
        }

        String key = clazz.getName() + "_";
        WebUserAgentFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info("Initializing " + key + "...");
            try {
                Constructor constructor = clazz.getConstructor();
                if (constructor != null) {
                    instance = (WebUserAgentFactory) constructor.newInstance();
                }
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).info("Initializing " + DummyWebUserAgentFactory.class.getName() + "...");
            instance = new DummyWebUserAgentFactory();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(key, instance);
            }
        }
        return instance;
    }

    public WebUserAgent getWebUserAgent() {
        WebUserAgent result = null;
        try {
            result = onGetWebUserAgent();
        } catch (Exception e) {
            Logger.getInstance(WebUserAgentFactory.class.getSimpleName()).fatal("Getting web user agent error: " + e);
        }
        return result;
    }

    protected abstract WebUserAgent onGetWebUserAgent();
}
