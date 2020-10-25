package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OAuth2ClientTokenInfo {
    private String mAccessToken;
    private int mExpiresInSec;
    private Map<String, String> mOptions;
    private String mRefreshToken;
    private Set<String> mTokenScope;
    private OAuth2TokenType mTokenType;

    public String getAccessToken() {
        return mAccessToken;
    }

    public int getExpiresInSec() {
        return mExpiresInSec;
    }

    public Map<String, String> getOptions() {
        if (mOptions == null) {
            return new HashMap<String, String>();
        }
        return mOptions;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public Set<String> getTokenScope() {
        if (mTokenScope == null) {
            return new HashSet<String>();
        }
        return mTokenScope;
    }

    public OAuth2TokenType getTokenType() {
        if (mTokenType == null) {
            return OAuth2TokenType.Bearer;
        }
        return mTokenType;
    }

    public OAuth2ClientTokenInfo fillEmptyPropertiesWith(OAuth2ClientTokenInfo another) {
        if (another == null) {
            return this;
        }
        if (StringUtils.isNullOrWhiteSpace(mAccessToken)) {
            String newAccessToken = another.getAccessToken();
            if (!StringUtils.isNullOrWhiteSpace(newAccessToken)) {
                mAccessToken = newAccessToken;
            }
        }
        if (mExpiresInSec <= 0) {
            int newExpiresInSec = another.getExpiresInSec();
            if (newExpiresInSec > 0) {
                mExpiresInSec = newExpiresInSec;
            }
        }
        if (mOptions == null || mOptions.isEmpty()) {
            Map<String, String> newOptions = another.getOptions();
            if (newOptions != null && !newOptions.isEmpty()) {
                mOptions = newOptions;
            }
        }
        if (StringUtils.isNullOrWhiteSpace(mRefreshToken)) {
            String newRefreshToken = another.getRefreshToken();
            if (!StringUtils.isNullOrWhiteSpace(newRefreshToken)) {
                mRefreshToken = newRefreshToken;
            }
        }
        if (mTokenScope == null) {
            Set<String> newTokenScope = another.getTokenScope();
            if (newTokenScope != null) {
                mTokenScope = newTokenScope;
            }
        }
        if (mTokenType == null) {
            OAuth2TokenType newTokenType = another.getTokenType();
            if (newTokenType != null) {
                mTokenType = newTokenType;
            }
        }
        return this;
    }

    public OAuth2ClientTokenInfo setAccessToken(String accessToken) {
        mAccessToken = accessToken;
        return this;
    }

    public OAuth2ClientTokenInfo setExpiresInSec(int expiresInSec) {
        if (expiresInSec >= 0) {
            mExpiresInSec = expiresInSec;
        }
        return this;
    }

    public OAuth2ClientTokenInfo setOptions(Map<String, String> options) {
        mOptions = options;
        return this;
    }

    public OAuth2ClientTokenInfo setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
        return this;
    }

    public OAuth2ClientTokenInfo setTokenScope(Set<String> tokenScope) {
        mTokenScope = tokenScope;
        return this;
    }

    public OAuth2ClientTokenInfo setTokenType(OAuth2TokenType tokenType) {
        mTokenType = tokenType;
        return this;
    }
}
