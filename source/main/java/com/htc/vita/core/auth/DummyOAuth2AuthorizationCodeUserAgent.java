package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.Map;

public class DummyOAuth2AuthorizationCodeUserAgent extends OAuth2AuthorizationCodeUserAgent {
    public DummyOAuth2AuthorizationCodeUserAgent() {
        Logger.getInstance(DummyOAuth2AuthorizationCodeUserAgent.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2AuthorizationCodeUserAgent.class.getSimpleName()
        ));
    }

    @Override
    public void close() {
    }

    @Override
    protected OAuth2AuthorizationCodeUserAgent onInitialize(
            Map<String, String> options,
            CancellationToken cancellationToken) {
        return this;
    }

    @Override
    protected LaunchResult onLaunch() {
        return new LaunchResult()
                .setStatus(LaunchStatus.NotImplemented);
    }
}
