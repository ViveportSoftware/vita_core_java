package com.htc.vita.core.crypto;

public enum AesCipherMode {
    Cbc("CBC");

    private final String mValue;

    AesCipherMode(String value) {
        mValue = value;
    }

    public String value() {
        return mValue;
    }
}
