package com.htc.vita.core.net;

public enum HttpWebRequestMethod {
    Get("GET"),
    Head("HEAD"),
    Post("POST"),
    Put("PUT"),
    Delete("DELETE");

    private final String mValue;

    HttpWebRequestMethod(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
