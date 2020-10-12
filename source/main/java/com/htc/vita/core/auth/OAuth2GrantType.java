package com.htc.vita.core.auth;

public enum OAuth2GrantType {
    AuthorizationCode("authorization_code"),
    ClientCredentials("client_credentials"),
    DeviceCode("urn:ietf:params:oauth:grant-type:device_code"),
    Password("password"),
    RefreshToken("refresh_token");

    private final String mValue;

    OAuth2GrantType(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
