package com.htc.vita.core.auth;

public enum OAuth2ResponseType {
    Code("code"),
    Token("token");

    private final String mValue;

    OAuth2ResponseType(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
