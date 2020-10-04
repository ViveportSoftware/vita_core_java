package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class OAuth2AuthorizationCodeUserAgentFactory {
    static {
        TypeRegistry.registerDefault(
                OAuth2AuthorizationCodeUserAgentFactory.class,
                DummyOAuth2AuthorizationCodeUserAgentFactory.class
        );
    }

    public static <T extends OAuth2AuthorizationCodeUserAgentFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                OAuth2AuthorizationCodeUserAgentFactory.class,
                clazz
        );
    }

    public static OAuth2AuthorizationCodeUserAgentFactory getInstance() {
        return TypeRegistry.getInstance(OAuth2AuthorizationCodeUserAgentFactory.class);
    }

    public static <T extends OAuth2AuthorizationCodeUserAgentFactory> OAuth2AuthorizationCodeUserAgentFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                OAuth2AuthorizationCodeUserAgentFactory.class,
                clazz
        );
    }

    public OAuth2AuthorizationCodeUserAgent getUserAgent() {
        OAuth2AuthorizationCodeUserAgent result = null;
        try {
            result = onGetUserAgent();
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeUserAgentFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeUserAgent onGetUserAgent() throws Exception;
}
