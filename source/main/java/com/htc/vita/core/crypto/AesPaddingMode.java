package com.htc.vita.core.crypto;

public enum AesPaddingMode {
    Pkcs5("PKCS5Padding"); // For AES, PKCS5Padding is the same as PKCS7Padding

    private String mValue;

    AesPaddingMode(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
