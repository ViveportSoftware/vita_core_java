package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class OAuth2ClientFactory {
    static {
        TypeRegistry.registerDefault(
                OAuth2ClientFactory.class,
                DummyOAuth2ClientFactory.class
        );
    }

    public static <T extends OAuth2ClientFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                OAuth2ClientFactory.class,
                clazz
        );
    }

    public static OAuth2ClientFactory getInstance() {
        return TypeRegistry.getInstance(OAuth2ClientFactory.class);
    }

    public static <T extends OAuth2ClientFactory> OAuth2ClientFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                OAuth2ClientFactory.class,
                clazz
        );
    }

    public OAuth2AuthorizationCodeClient getAuthorizationCodeClient(OAuth2AuthorizationCodeClientConfig clientConfig) {
        OAuth2AuthorizationCodeClient result = null;
        try {
            result = onGetAuthorizationCodeClient(clientConfig);
        } catch (Exception e) {
            Logger.getInstance(OAuth2ClientFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeClient onGetAuthorizationCodeClient(OAuth2AuthorizationCodeClientConfig clientConfig) throws Exception;
}
