package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyOAuth2AuthorizationCodeReceiverFactory extends OAuth2AuthorizationCodeReceiverFactory {
    public DummyOAuth2AuthorizationCodeReceiverFactory() {
        Logger.getInstance(DummyOAuth2AuthorizationCodeReceiverFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2AuthorizationCodeReceiverFactory.class.getSimpleName()
        ));
    }

    @Override
    protected OAuth2AuthorizationCodeReceiver onGetReceiver() {
        return new DummyOAuth2AuthorizationCodeReceiver().initialize();
    }
}
