package com.htc.vita.core.auth;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyOAuth2AuthorizationCodeUserAgentFactory extends OAuth2AuthorizationCodeUserAgentFactory {
    public DummyOAuth2AuthorizationCodeUserAgentFactory() {
        Logger.getInstance(DummyOAuth2AuthorizationCodeUserAgentFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2AuthorizationCodeUserAgentFactory.class.getSimpleName()
        ));
    }

    @Override
    protected OAuth2AuthorizationCodeUserAgent onGetUserAgent() {
        return new DummyOAuth2AuthorizationCodeUserAgent().initialize();
    }
}
