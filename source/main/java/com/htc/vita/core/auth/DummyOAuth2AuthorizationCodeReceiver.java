package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.util.Map;

public class DummyOAuth2AuthorizationCodeReceiver extends OAuth2AuthorizationCodeReceiver {
    @Override
    public void close() {
    }

    @Override
    protected OAuth2AuthorizationCodeReceiver onInitialize(
            Map<String, String> options,
            CancellationToken cancellationToken) {
        return this;
    }

    @Override
    protected ReceiveResult onReceive() {
        return new ReceiveResult()
                .setStatus(ReceiveStatus.NotImplemented);
    }
}
