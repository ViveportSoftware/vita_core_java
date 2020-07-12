package com.htc.vita.core.text;

public class DummyBase64 extends Base64 {
    @Override
    protected byte[] onDecode(
            String data,
            Base64Option option) {
        return null;
    }

    @Override
    protected String onEncodeToString(
            byte[] data,
            Base64Option option) {
        return null;
    }
}
