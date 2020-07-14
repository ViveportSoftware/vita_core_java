package com.htc.vita.core.text;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class Base64Test {
    @Test
    public void dummy_0_getInstance() {
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
    }

    @Test
    public void dummy_1_decode() {
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
        Assert.assertNull(base64.decode("VGVzdA=="));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.Basic));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.Mime));
        Assert.assertNull(base64.decode("VGVzdA==", Base64Option.UrlSafe));
    }

    @Test
    public void dummy_2_encodeToString() throws UnsupportedEncodingException {
        Base64 base64 = Base64.getInstance();
        Assert.assertNotNull(base64);
        byte[] data = "Test".getBytes("UTF-8");
        Assert.assertNull(base64.encodeToString(data));
        Assert.assertNull(base64.encodeToString(data, Base64Option.Basic));
        Assert.assertNull(base64.encodeToString(data, Base64Option.Mime));
        Assert.assertNull(base64.encodeToString(data, Base64Option.UrlSafe));
    }
}
