package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class OAuth2AuthorizationCodeReceiverFactory {
    static {
        TypeRegistry.registerDefault(
                OAuth2AuthorizationCodeReceiverFactory.class,
                DummyOAuth2AuthorizationCodeReceiverFactory.class
        );
    }

    public static <T extends OAuth2AuthorizationCodeReceiverFactory> void register(Class<T> clazz) {
        TypeRegistry.register(
                OAuth2AuthorizationCodeReceiverFactory.class,
                clazz
        );
    }

    public static OAuth2AuthorizationCodeReceiverFactory getInstance() {
        return TypeRegistry.getInstance(OAuth2AuthorizationCodeReceiverFactory.class);
    }

    public static <T extends OAuth2AuthorizationCodeReceiverFactory> OAuth2AuthorizationCodeReceiverFactory getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                OAuth2AuthorizationCodeReceiverFactory.class,
                clazz
        );
    }

    public OAuth2AuthorizationCodeReceiver getReceiver() {
        OAuth2AuthorizationCodeReceiver result = null;
        try {
            result = onGetReceiver();
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeReceiverFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeReceiver onGetReceiver() throws Exception;
}
