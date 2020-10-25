package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class WebUserAgentFactory {
    static {
        TypeRegistry.registerDefault(
                WebUserAgentFactory.class,
                DummyWebUserAgentFactory.class
        );
    }

    public static <T extends WebUserAgentFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                WebUserAgentFactory.class,
                clazz
        );
    }

    public static WebUserAgentFactory getInstance() {
        return TypeRegistry.getInstance(WebUserAgentFactory.class);
    }

    public static <T extends WebUserAgentFactory> WebUserAgentFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                WebUserAgentFactory.class,
                clazz
        );
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

    protected abstract WebUserAgent onGetWebUserAgent() throws Exception;
}
