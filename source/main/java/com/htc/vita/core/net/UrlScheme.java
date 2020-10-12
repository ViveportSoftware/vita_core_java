package com.htc.vita.core.net;

public enum UrlScheme {
    HTTP("http"),
    HTTPS("https");

    private final String mValue;

    UrlScheme(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
