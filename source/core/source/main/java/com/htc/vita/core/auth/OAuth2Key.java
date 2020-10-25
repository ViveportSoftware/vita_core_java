package com.htc.vita.core.auth;

public enum OAuth2Key {
    AccessToken("access_token"),
    ClientId("client_id"),
    ClientSecret("client_secret"),
    Code("code"),
    ExpiresIn("expires_in"),
    GrantType("grant_type"),
    RedirectUri("redirection_uri"),
    RefreshToken("refresh_token"),
    ResponseType("response_type"),
    Scope("scope"),
    TokenType("token_type");

    private final String mValue;

    OAuth2Key(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
