package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

public abstract class OAuth2AuthorizationCodeClient {

    public AuthorizeResult authorize() {
        return authorize(null);
    }

    public AuthorizeResult authorize(CancellationToken cancellationToken) {
        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }

        AuthorizeResult result = null;
        try {
            result = onAuthorize(realCancellationToken);
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new AuthorizeResult();
        }
        return result;
    }

    public OAuth2AuthorizationCodeClientConfig getConfig() {
        OAuth2AuthorizationCodeClientConfig result = null;
        try {
            result = onGetConfig();
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public OAuth2ClientTokenInfo getToken() {
        OAuth2ClientTokenInfo result = null;
        try {
            result = onGetToken();
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public OAuth2AuthorizationCodeClient initialize(OAuth2AuthorizationCodeClientConfig config) {
        OAuth2AuthorizationCodeClient result = null;
        try {
            result = onInitialize(config);
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public IntrospectTokenResult introspectToken(OAuth2ClientTokenInfo token) {
        if (token == null || StringUtils.isNullOrWhiteSpace(token.getAccessToken())) {
            return new IntrospectTokenResult().setStatus(IntrospectTokenStatus.InvalidToken);
        }

        IntrospectTokenResult result = null;
        try {
            result = onIntrospectToken(token);
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new IntrospectTokenResult();
        }
        return result;
    }

    public IntrospectTokenResult introspectToken(String accessTokenString) {
        return introspectToken(new OAuth2ClientTokenInfo().setAccessToken(accessTokenString));
    }

    public RefreshTokenResult refreshToken(OAuth2ClientTokenInfo token) {
        if (token == null || StringUtils.isNullOrWhiteSpace(token.getRefreshToken())) {
            return new RefreshTokenResult().setStatus(RefreshTokenStatus.InvalidToken);
        }

        RefreshTokenResult result = null;
        try {
            result = onRefreshToken(token);
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeClient.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new RefreshTokenResult();
        }
        return result;
    }

    public RefreshTokenResult refreshToken(String refreshTokenString) {
        return refreshToken(new OAuth2ClientTokenInfo().setRefreshToken(refreshTokenString));
    }

    protected abstract AuthorizeResult onAuthorize(CancellationToken cancellationToken) throws Exception;
    protected abstract OAuth2AuthorizationCodeClientConfig onGetConfig() throws Exception;
    protected abstract OAuth2ClientTokenInfo onGetToken() throws Exception;
    protected abstract OAuth2AuthorizationCodeClient onInitialize(OAuth2AuthorizationCodeClientConfig config) throws Exception;
    protected abstract IntrospectTokenResult onIntrospectToken(OAuth2ClientTokenInfo token) throws Exception;
    protected abstract RefreshTokenResult onRefreshToken(OAuth2ClientTokenInfo token) throws Exception;

    public static class AuthorizeResult {
        private AuthorizeStatus mStatus = AuthorizeStatus.Unknown;
        private OAuth2ClientTokenInfo mToken;

        public AuthorizeStatus getStatus() {
            return mStatus;
        }

        public OAuth2ClientTokenInfo getToken() {
            return mToken;
        }

        public AuthorizeResult setStatus(AuthorizeStatus status) {
            if (status != null) {
                mStatus = status;
            }
            return this;
        }

        public AuthorizeResult setToken(OAuth2ClientTokenInfo token) {
            if (token != null) {
                mToken = token;
            }
            return this;
        }
    }

    public static class IntrospectTokenResult {
        private IntrospectTokenStatus mStatus = IntrospectTokenStatus.Unknown;
        private OAuth2ClientTokenInfo mToken;

        public IntrospectTokenStatus getStatus() {
            return mStatus;
        }

        public OAuth2ClientTokenInfo getToken() {
            return mToken;
        }

        public IntrospectTokenResult setStatus(IntrospectTokenStatus status) {
            if (status != null) {
                mStatus = status;
            }
            return this;
        }

        public IntrospectTokenResult setToken(OAuth2ClientTokenInfo token) {
            if (token != null) {
                mToken = token;
            }
            return this;
        }
    }

    public static class RefreshTokenResult {
        private RefreshTokenStatus mStatus = RefreshTokenStatus.Unknown;
        private OAuth2ClientTokenInfo mToken;

        public RefreshTokenStatus getStatus() {
            return mStatus;
        }

        public OAuth2ClientTokenInfo getToken() {
            return mToken;
        }

        public RefreshTokenResult setStatus(RefreshTokenStatus status) {
            if (status != null) {
                mStatus = status;
            }
            return this;
        }

        public RefreshTokenResult setToken(OAuth2ClientTokenInfo token) {
            if (token != null) {
                mToken = token;
            }
            return this;
        }
    }

    public enum AuthorizeStatus {
        Unknown,
        Ok,
        NetworkError,
        ServerError,
        CancelledAuthorization,
        InvalidAuthorizationCode,
        InvalidAuthorizationUri,
        InvalidConfig,
        InvalidRedirectUri,
        NotImplemented,
        UnsupportedReceiver,
        UnsupportedUserAgent
    }

    public enum IntrospectTokenStatus {
        Unknown,
        Ok,
        NetworkError,
        ServerError,
        CancelledTokenIntrospection,
        ChangedCredential,
        ExpiredToken,
        InvalidConfig,
        InvalidTokenIntrospectionUri,
        InvalidToken,
        NonExistentAccount,
        NotImplemented
    }

    public enum RefreshTokenStatus {
        Unknown,
        Ok,
        NetworkError,
        ServerError,
        CancelledTokenRefresh,
        InvalidConfig,
        InvalidTokenRefreshUri,
        InvalidToken,
        NotImplemented
    }
}
