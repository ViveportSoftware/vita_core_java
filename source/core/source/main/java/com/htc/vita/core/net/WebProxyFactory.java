package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

import java.net.Proxy;

public abstract class WebProxyFactory {
    static {
        TypeRegistry.registerDefault(
                WebProxyFactory.class,
                DefaultWebProxyFactory.class
        );
    }

    public static <T extends WebProxyFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                WebProxyFactory.class,
                clazz
        );
    }

    public static WebProxyFactory getInstance() {
        return TypeRegistry.getInstance(WebProxyFactory.class);
    }

    public static <T extends WebProxyFactory> WebProxyFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                WebProxyFactory.class,
                clazz
        );
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
