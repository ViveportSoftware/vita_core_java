package com.htc.vita.core.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {
    @Test
    public void dummy_0_getInstance() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
    }

    @Test
    public void dummy_1_hasKey() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertFalse(config.hasKey("test"));
    }

    @Test
    public void dummy_2_get() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertNull(config.get("test"));
    }

    @Test
    public void dummy_3_getBoolean() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertFalse(config.getBoolean("test"));
    }

    @Test
    public void dummy_4_getDouble() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertEquals(0d, config.getDouble("test"), 0.0000000001d);
    }

    @Test
    public void dummy_5_getFloat() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertEquals(0f, config.getFloat("test"), 0.0000000001f);
    }

    @Test
    public void dummy_6_getInt() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertEquals(0, config.getInt("test"));
    }

    @Test
    public void dummy_7_getLong() {
        Config config = Config.getInstance();
        Assert.assertNotNull(config);
        Assert.assertEquals(0L, config.getLong("test"));
    }
}
