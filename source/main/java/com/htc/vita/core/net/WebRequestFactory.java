package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class WebRequestFactory {
    private static final Map<String, WebRequestFactory> sInstances = new HashMap<String, WebRequestFactory>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends WebRequestFactory> sDefaultClass = DefaultWebRequestFactory.class;

    public static <T extends WebRequestFactory> void register(Class<T> clazz) {
        sDefaultClass = clazz;
        System.err.printf(
                "Registered default %s type to %s%n",
                WebRequestFactory.class.getSimpleName(),
                sDefaultClass.getName()
        );
    }

    public static WebRequestFactory getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends WebRequestFactory> WebRequestFactory getInstance(Class<T> clazz) {
        WebRequestFactory instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(WebRequestFactory.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(WebRequestFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultWebRequestFactory.class.getName()
            ));
            instance = new DefaultWebRequestFactory();
        }
        return instance;
    }

    private static <T extends WebRequestFactory> WebRequestFactory doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    WebRequestFactory.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        WebRequestFactory instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(WebRequestFactory.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(WebRequestFactory.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultWebRequestFactory.class.getName()
            ));
            instance = new DefaultWebRequestFactory();
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

    public HttpWebRequest getHttpWebRequest(URL url) {
        HttpWebRequest result = null;
        try {
            result = onGetHttpWebRequest(url);
        } catch (Exception e) {
            Logger.getInstance(WebRequestFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract HttpWebRequest onGetHttpWebRequest(URL url);
}
