package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class KeyValuePairTest {
    @Test
    public void default_0_ctor() {
        String key = "testKey";
        String value = "testValue";
        KeyValuePair<String, String> keyValuePair = new KeyValuePair<String, String>(key, value);
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(keyValuePair.getKey()));
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(keyValuePair.getValue()));
        Assert.assertSame(key, keyValuePair.getKey());
        Assert.assertSame(value, keyValuePair.getValue());
    }

    @Test
    public void default_0_ctor_withNullKey() {
        String value = "testValue";
        KeyValuePair<String, String> keyValuePair = new KeyValuePair<String, String>(null, value);
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(keyValuePair.getKey()));
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(keyValuePair.getValue()));
        Assert.assertNull(keyValuePair.getKey());
        Assert.assertSame(value, keyValuePair.getValue());
    }

    @Test
    public void default_0_ctor_withNullValue() {
        String key = "testKey";
        KeyValuePair<String, String> keyValuePair = new KeyValuePair<String, String>(key, null);
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(keyValuePair.getKey()));
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(keyValuePair.getValue()));
        Assert.assertSame(key, keyValuePair.getKey());
        Assert.assertNull(keyValuePair.getValue());
    }

    @Test
    public void default_0_ctor_withNullKeyAndValue() {
        KeyValuePair<String, String> keyValuePair = new KeyValuePair<String, String>(null, null);
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(keyValuePair.getKey()));
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(keyValuePair.getValue()));
        Assert.assertNull(keyValuePair.getKey());
        Assert.assertNull(keyValuePair.getValue());
    }
}
