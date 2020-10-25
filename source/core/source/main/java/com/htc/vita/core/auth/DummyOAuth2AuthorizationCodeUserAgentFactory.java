package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.Map;

public class DummyOAuth2AuthorizationCodeUserAgentFactory extends OAuth2AuthorizationCodeUserAgentFactory {
    public DummyOAuth2AuthorizationCodeUserAgentFactory() {
        Logger.getInstance(DummyOAuth2AuthorizationCodeUserAgentFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2AuthorizationCodeUserAgentFactory.class.getSimpleName()
        ));
    }

    @Override
    protected OAuth2AuthorizationCodeUserAgent onGetUserAgent(
            Map<String, Object> options,
            CancellationToken cancellationToken) {
        return new DummyOAuth2AuthorizationCodeUserAgent().initialize(
                options,
                cancellationToken
        );
    }
}
