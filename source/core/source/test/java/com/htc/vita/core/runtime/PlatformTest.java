package com.htc.vita.core.runtime;

import org.junit.Assert;
import org.junit.Test;

public class PlatformTest {
    @Test
    public void dummy_0_getInstance() {
        Platform platform = Platform.getInstance();
        Assert.assertNotNull(platform);
    }

    @Test
    public void dummy_1_getMachineId() {
        Platform platform = Platform.getInstance();
        Assert.assertNotNull(platform);
        Assert.assertNull(platform.getMachineId());
    }
}
