package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyOAuth2ClientFactory extends OAuth2ClientFactory {
    public DummyOAuth2ClientFactory() {
        Logger.getInstance(DummyOAuth2ClientFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2ClientFactory.class.getSimpleName()
        ));
    }

    @Override
    protected OAuth2AuthorizationCodeClient onGetAuthorizationCodeClient(OAuth2AuthorizationCodeClientConfig clientConfig) {
        return new DummyOAuth2AuthorizationCodeClient().initialize(clientConfig);
    }
}
