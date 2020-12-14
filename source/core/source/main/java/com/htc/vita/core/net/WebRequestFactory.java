package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

import java.net.URL;

public abstract class WebRequestFactory {
    static {
        TypeRegistry.registerDefault(
                WebRequestFactory.class,
                DefaultWebRequestFactory.class
        );
    }

    public static <T extends WebRequestFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                WebRequestFactory.class,
                clazz
        );
    }

    public static WebRequestFactory getInstance() {
        return TypeRegistry.getInstance(WebRequestFactory.class);
    }

    public static <T extends WebRequestFactory> WebRequestFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                WebRequestFactory.class,
                clazz
        );
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

    protected abstract HttpWebRequest onGetHttpWebRequest(URL url) throws Exception;
}
