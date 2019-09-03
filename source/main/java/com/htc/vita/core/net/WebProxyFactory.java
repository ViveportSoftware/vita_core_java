package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public abstract class WebProxyFactory {
    public enum WebProxyStatus {
        Unknown,
        NotSet,
        Working,
        CannotTest
    }

    private static final Map<String, WebProxyFactory> sInstances = new HashMap<String, WebProxyFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends WebProxyFactory> sDefaultClass = DefaultWebProxyFactory.class;

    public static <T extends WebProxyFactory> void register(Class<T> clazz) {
        sDefaultClass = clazz;
        System.err.println("Registered default " + WebProxyFactory.class.getName() + " type to " + sDefaultClass.getName());
    }

    public static WebProxyFactory getInstance() {
        WebProxyFactory instance;
        try {
            instance = doGetInstance(sDefaultClass);
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).fatal("Instance initialization error: " + e);
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info("Initializing " + DefaultWebProxyFactory.class.getName() + "...");
            instance = new DefaultWebProxyFactory();
        }
        return instance;
    }

    public static <T extends WebProxyFactory> WebProxyFactory getInstance(Class<T> clazz) {
        WebProxyFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).fatal("Instance initialization error: " + e);
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info("Initializing " + DefaultWebProxyFactory.class.getName() + "...");
            instance = new DefaultWebProxyFactory();
        }
        return instance;
    }

    private static <T extends WebProxyFactory> WebProxyFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Invalid argument to get %s instance", WebProxyFactory.class.getName()));
        }

        String key = clazz.getName() + "_";
        WebProxyFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info("Initializing " + key + "...");
            try {
                Constructor constructor = clazz.getConstructor();
                if (constructor != null) {
                    instance = (WebProxyFactory) constructor.newInstance();
                }
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info("Initializing " + DefaultWebProxyFactory.class.getName() + "...");
            instance = new DefaultWebProxyFactory();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(key, instance);
            }
        }
        return instance;
    }

    public Proxy getWebProxy() {
        Proxy result = null;
        try {
            result = onGetWebProxy();
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).fatal("Getting web proxy error: " + e);
        }
        return result;
    }

    public WebProxyStatus getWebProxyStatus(Proxy proxy) {
        if (proxy == null) {
            return WebProxyStatus.NotSet;
        }

        WebProxyStatus result = WebProxyStatus.Unknown;
        try {
            result = onGetWebProxyStatus(proxy);
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).fatal("Getting web proxy status error: " + e);
        }
        return result;
    }

    protected abstract Proxy onGetWebProxy();
    protected abstract WebProxyStatus onGetWebProxyStatus(Proxy proxy);
}
