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

    @Test
    public void default_2_toHexString() throws Exception {
        Assert.assertEquals("74657374", Convert.toHexString("test".getBytes("UTF-8")));
    }

    @Test
    public void default_3_fromHexString() throws Exception {
        String oldData = "test";
        byte[] oldDataInBytes = oldData.getBytes("UTF-8");
        String oldDataInHexString = Convert.toHexString(oldDataInBytes);
        byte[] newDataInBytes = Convert.fromHexString(oldDataInHexString);
        String newData = new String(newDataInBytes, "UTF-8");
        Assert.assertEquals(oldData, newData);
    }
}
