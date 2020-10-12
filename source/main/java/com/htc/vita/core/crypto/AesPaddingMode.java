package com.htc.vita.core.crypto;

public enum AesPaddingMode {
    PKCS5("PKCS5Padding"); // For AES, PKCS5Padding is the same as PKCS7Padding

    private final String mValue;

    AesPaddingMode(String value) {
        mValue = value;
    }

    @Override
    public String toString() {
        return mValue;
    }
}
