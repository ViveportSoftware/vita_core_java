package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void default_0_isNullOrEmpty() {
        String nullString = null;
        Assert.assertTrue(StringUtils.isNullOrEmpty(nullString));
        String emptyString = "";
        Assert.assertTrue(StringUtils.isNullOrEmpty(emptyString));
        String nonEmptyString = "nonEmptyString";
        Assert.assertFalse(StringUtils.isNullOrEmpty(nonEmptyString));
        String whiteSpaceString = " ";
        Assert.assertFalse(StringUtils.isNullOrEmpty(whiteSpaceString));
    }

    @Test
    public void default_1_isNullOrWhiteSpace() {
        String nullString = null;
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(nullString));
        String emptyString = "";
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(emptyString));
        String nonEmptyString = "nonEmptyString";
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(nonEmptyString));
        String whiteSpaceString = " ";
        Assert.assertTrue(StringUtils.isNullOrWhiteSpace(whiteSpaceString));
    }
}
