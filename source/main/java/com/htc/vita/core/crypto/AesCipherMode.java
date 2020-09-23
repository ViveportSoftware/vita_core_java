package com.htc.vita.core.crypto;

public enum AesCipherMode {
    CBC("CBC");

    private final String mValue;

    AesCipherMode(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
