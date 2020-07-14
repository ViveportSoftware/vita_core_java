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

    @Test
    public void default_4_toBase64String() throws Exception {
        Assert.assertEquals("dGVzdA==", Convert.toBase64String("test".getBytes("UTF-8")));
    }

    @Test
    public void default_5_fromBase64String() throws Exception {
        String oldData = "test";
        byte[] oldDataInBytes = oldData.getBytes("UTF-8");
        String oldDataInBase64String = Convert.toBase64String(oldDataInBytes);
        byte[] newDataInBytes = Convert.fromBase64String(oldDataInBase64String);
        String newData = new String(newDataInBytes, "UTF-8");
        Assert.assertEquals(oldData, newData);
    }
}
