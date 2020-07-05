package com.htc.vita.core.net;

public enum HttpWebRequestMethod {
    Get("GET"),
    Head("HEAD"),
    Post("POST"),
    Put("PUT"),
    Delete("DELETE");

    private String mValue;

    private HttpWebRequestMethod(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
