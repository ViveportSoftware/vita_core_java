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
        Assert.assertEquals("74657374", Convert.toHexString(StringUtils.toBytesByUtf8("test")));
    }

    @Test
    public void default_3_fromHexString() throws Exception {
        String oldData = "test";
        byte[] oldDataInBytes = StringUtils.toBytesByUtf8(oldData);
        String oldDataInHexString = Convert.toHexString(oldDataInBytes);
        byte[] newDataInBytes = Convert.fromHexString(oldDataInHexString);
        String newData = StringUtils.fromBytesByUtf8(newDataInBytes);
        Assert.assertEquals(oldData, newData);
    }

    @Test
    public void default_4_toBase64String() throws Exception {
        Assert.assertEquals("dGVzdA==", Convert.toBase64String(StringUtils.toBytesByUtf8("test")));
    }

    @Test
    public void default_5_fromBase64String() throws Exception {
        String oldData = "test";
        byte[] oldDataInBytes = StringUtils.toBytesByUtf8(oldData);
        String oldDataInBase64String = Convert.toBase64String(oldDataInBytes);
        byte[] newDataInBytes = Convert.fromBase64String(oldDataInBase64String);
        String newData = StringUtils.fromBytesByUtf8(newDataInBytes);
        Assert.assertEquals(oldData, newData);
    }

    @Test
    public void default_6_toBoolean() {
        Assert.assertFalse(Convert.toBoolean(null));
        Assert.assertFalse(Convert.toBoolean(""));
        Assert.assertFalse(Convert.toBoolean("0"));
        Assert.assertFalse(Convert.toBoolean("1"));
        Assert.assertFalse(Convert.toBoolean("12"));
        Assert.assertFalse(Convert.toBoolean("no"));
        Assert.assertFalse(Convert.toBoolean("No"));
        Assert.assertFalse(Convert.toBoolean("NO"));
        Assert.assertFalse(Convert.toBoolean("yes"));
        Assert.assertFalse(Convert.toBoolean("Yes"));
        Assert.assertFalse(Convert.toBoolean("YES"));
        Assert.assertFalse(Convert.toBoolean("false"));
        Assert.assertFalse(Convert.toBoolean("False"));
        Assert.assertFalse(Convert.toBoolean("FALSE"));
        Assert.assertFalse(Convert.toBoolean("FAlse"));
        Assert.assertTrue(Convert.toBoolean("true"));
        Assert.assertTrue(Convert.toBoolean("True"));
        Assert.assertTrue(Convert.toBoolean("TRUE"));
        Assert.assertTrue(Convert.toBoolean("TRue"));
        Assert.assertFalse(Convert.toBoolean("TRue1"));
    }

    @Test
    public void default_7_toDouble() {
        Assert.assertEquals(0.0D, Convert.toDouble("0.0"), 0.00000001D);
        Assert.assertEquals(0.1D, Convert.toDouble("0.2"), 0.1D);
        Assert.assertNotEquals(0.1D, Convert.toDouble("0.3"), 0.1D);
    }
}
