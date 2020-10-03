package com.htc.vita.core.net;

public enum UrlScheme {
    HTTP("http"),
    HTTPS("https");

    private final String mValue;

    UrlScheme(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
