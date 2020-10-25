package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public class DummyOAuth2AuthorizationCodeClient extends OAuth2AuthorizationCodeClient {
    private OAuth2AuthorizationCodeClientConfig mConfig;

    public DummyOAuth2AuthorizationCodeClient() {
        Logger.getInstance(DummyOAuth2AuthorizationCodeClient.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                "You are using dummy %s instance!!",
                OAuth2AuthorizationCodeClient.class.getSimpleName()
        ));
    }

    @Override
    protected AuthorizeResult onAuthorize(CancellationToken cancellationToken) {
        return new AuthorizeResult()
                .setStatus(AuthorizeStatus.NotImplemented);
    }

    @Override
    protected OAuth2AuthorizationCodeClientConfig onGetConfig() {
        return mConfig;
    }

    @Override
    protected OAuth2ClientTokenInfo onGetToken() {
        return null;
    }

    @Override
    protected OAuth2AuthorizationCodeClient onInitialize(OAuth2AuthorizationCodeClientConfig config) {
        mConfig = config;
        return this;
    }

    @Override
    protected IntrospectTokenResult onIntrospectToken(OAuth2ClientTokenInfo token) {
        return new IntrospectTokenResult()
                .setStatus(IntrospectTokenStatus.NotImplemented);
    }

    @Override
    protected RefreshTokenResult onRefreshToken(OAuth2ClientTokenInfo token) {
        return new RefreshTokenResult()
                .setStatus(RefreshTokenStatus.NotImplemented);
    }
}
