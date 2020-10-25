package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class LocalPortManagerTest {
    @Test
    public void default_0_getRandomUnusedPort() {
        int unusedPort0 = LocalPortManager.getInstance().getRandomUnusedPort();
        Assert.assertFalse(unusedPort0 < 0);
        Logger.getInstance(LocalPortManagerTest.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                "unused port: %d",
                unusedPort0
        ));
        int unusedPort1 = LocalPortManager.getInstance().getRandomUnusedPort();
        Assert.assertFalse(unusedPort1 < 0);
        Assert.assertEquals(unusedPort0, unusedPort1);
    }
}
