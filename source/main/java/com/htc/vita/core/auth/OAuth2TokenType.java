package com.htc.vita.core.auth;

public enum OAuth2TokenType {
    Bearer("Bearer");

    private final String mValue;

    OAuth2TokenType(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
