package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

import java.util.Map;

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
        return getReceiver(
                null,
                null
        );
    }

    public OAuth2AuthorizationCodeReceiver getReceiver(
            Map<String, String> options,
            CancellationToken cancellationToken) {
        OAuth2AuthorizationCodeReceiver result = null;
        try {
            result = onGetReceiver(
                    options,
                    cancellationToken
            );
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeReceiverFactory.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeReceiver onGetReceiver(
            Map<String, String> options,
            CancellationToken cancellationToken
    ) throws Exception;
}
