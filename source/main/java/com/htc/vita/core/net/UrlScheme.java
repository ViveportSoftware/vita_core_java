package com.htc.vita.core.net;

public enum UrlScheme {
    Http("http"),
    Https("https");

    private final String mValue;

    UrlScheme(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
