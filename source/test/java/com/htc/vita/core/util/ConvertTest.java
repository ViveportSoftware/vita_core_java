package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class ConvertTest {
    @Test
    public void default_1_toInt32() {
        Assert.assertEquals(0, Convert.toInt32("0"));
        Assert.assertEquals(1, Convert.toInt32("1"));
        Assert.assertEquals(0, Convert.toInt32("A0"));
        Assert.assertEquals(3, Convert.toInt32("A0", 3));
    }
}
