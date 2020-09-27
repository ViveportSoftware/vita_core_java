package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OAuth2AuthorizationCodeClientConfig {
    private final Map<String, Object> mObjects = new HashMap<String, Object>();
    private final Map<String, String> mOptions = new HashMap<String, String>();

    private URL mAccessTokenUri;
    private URL mAuthorizationUri;
    private String mClientId;
    private String mClientSecret;
    private URI mRedirectUri;
    private URL mRefreshTokenUri;
    private URL mIntrospectTokenUri;
    private Set<String> mTokenScope = new HashSet<String>();

    public URL getAccessTokenUri() {
        return mAccessTokenUri;
    }

    public URL getAuthorizationUri() {
        return mAuthorizationUri;
    }

    public String getClientId() {
        return mClientId;
    }

    public String getClientSecret() {
        return mClientSecret;
    }

    public URL getIntrospectTokenUri() {
        return mIntrospectTokenUri;
    }

    public Map<String, Object> getObjects() {
        return mObjects;
    }

    public Map<String, String> getOptions() {
        return mOptions;
    }

    public URI getRedirectUri() {
        return mRedirectUri;
    }

    public URL getRefreshTokenUri() {
        return mRefreshTokenUri;
    }

    public Set<String> getTokenScope() {
        return mTokenScope;
    }

    public OAuth2AuthorizationCodeClientConfig setAccessTokenUri(URL accessTokenUri) {
        if (accessTokenUri != null) {
            mAccessTokenUri = accessTokenUri;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setAuthorizationUri(URL authorizationUri) {
        if (authorizationUri != null) {
            mAuthorizationUri = authorizationUri;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setClientId(String clientId) {
        if (!StringUtils.isNullOrWhiteSpace(clientId)) {
            mClientId = clientId;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setClientSecret(String clientSecret) {
        if (!StringUtils.isNullOrWhiteSpace(clientSecret)) {
            mClientSecret = clientSecret;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setIntrospectTokenUri(URL introspectTokenUri) {
        if (introspectTokenUri != null) {
            mIntrospectTokenUri = introspectTokenUri;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setObject(
            String key,
            Object value) {
        if (StringUtils.isNullOrWhiteSpace(key)) {
            return this;
        }
        mObjects.put(
                key,
                value
        );
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setOption(
            String key,
            String value) {
        if (StringUtils.isNullOrWhiteSpace(key)) {
            return this;
        }
        if (StringUtils.isNullOrWhiteSpace(value)) {
            return this;
        }
        mOptions.put(
                key,
                value
        );
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setRedirectUri(URI redirectUri) {
        if (redirectUri != null) {
            mRedirectUri = redirectUri;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setRefreshTokenUri(URL refreshTokenUri) {
        if (refreshTokenUri != null) {
            mRefreshTokenUri = refreshTokenUri;
        }
        return this;
    }

    public OAuth2AuthorizationCodeClientConfig setTokenScope(Set<String> tokenScope) {
        if (tokenScope != null) {
            mTokenScope = tokenScope;
        }
        return this;
    }
}
