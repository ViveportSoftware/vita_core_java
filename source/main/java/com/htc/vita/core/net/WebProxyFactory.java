package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public abstract class WebProxyFactory {
    private static final Map<String, WebProxyFactory> sInstances = new HashMap<String, WebProxyFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends WebProxyFactory> sDefaultClass = DefaultWebProxyFactory.class;

    public static <T extends WebProxyFactory> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(WebProxyFactory.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                WebProxyFactory.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static WebProxyFactory getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends WebProxyFactory> WebProxyFactory getInstance(Class<T> clazz) {
        WebProxyFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultWebProxyFactory.class.getName()
            ));
            instance = new DefaultWebProxyFactory();
        }
        return instance;
    }

    private static <T extends WebProxyFactory> WebProxyFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    WebProxyFactory.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        WebProxyFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultWebProxyFactory.class.getName()
            ));
            instance = new DefaultWebProxyFactory();
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

    public Proxy getWebProxy() {
        Proxy result = null;
        try {
            result = onGetWebProxy();
        } catch (Exception e) {
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).error(e.toString());
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
            Logger.getInstance(WebProxyFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract Proxy onGetWebProxy() throws Exception;
    protected abstract WebProxyStatus onGetWebProxyStatus(Proxy proxy) throws Exception;
}
