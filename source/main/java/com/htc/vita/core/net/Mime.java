package com.htc.vita.core.net;

public enum Mime {
    Application_OctetStream("application/octet-stream"),
    Application_Json("application/json"),
    Application_XWwwFormUrlencoded("application/x-www-form-urlencoded"),
    Text_Plain("text/plain"),
    Any_Any("*/*");

    private final String mValue;

    Mime(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
