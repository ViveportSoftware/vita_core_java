package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;

import java.util.Map;

public class DummyOAuth2AuthorizationCodeUserAgent extends OAuth2AuthorizationCodeUserAgent {
    @Override
    public void close() {
    }

    @Override
    protected OAuth2AuthorizationCodeUserAgent onInitialize(
            Map<String, Object> options,
            CancellationToken cancellationToken) {
        return this;
    }

    @Override
    protected LaunchResult onLaunch() {
        return new LaunchResult()
                .setStatus(LaunchStatus.NotImplemented);
    }
}
