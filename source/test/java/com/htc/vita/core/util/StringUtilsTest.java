package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Test
    public void default_2_join() {
        Assert.assertNull(StringUtils.join("+", (String[]) null));
        Assert.assertEquals("a", StringUtils.join("+", "a"));
        Assert.assertEquals("a+b+c", StringUtils.join("+", "a", "b", "c"));
        Assert.assertEquals("a+b+c", StringUtils.join("+", new String[] {"a", "b", "c"}));
        Assert.assertEquals("anullbnullc", StringUtils.join(null, "a", "b", "c"));
        Assert.assertNull(StringUtils.join(null, (String[]) null));

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Assert.assertEquals("a+b+c", StringUtils.join("+", list));

        Assert.assertNull(StringUtils.join("+", (Set) null));
        Set<String> s = new HashSet<String>();
        s.add("a");
        Assert.assertEquals("a", StringUtils.join("+", s));
        s.add("b");
        Assert.assertEquals("a+b", StringUtils.join("+", s));
        s.add("c");
        Assert.assertEquals("a+b+c", StringUtils.join("+", s));
    }

    @Test
    public void default_3_urlDecodeByUtf8() {
        Assert.assertEquals("a b", StringUtils.urlDecodeByUtf8("a%20b"));
    }

    @Test
    public void default_4_urlEncodeByUtf8() {
        Assert.assertEquals("a+b", StringUtils.urlEncodeByUtf8("a b"));
    }

    @Test
    public void default_5_toRootLocaleUpperCase() {
        Assert.assertEquals("A", StringUtils.toRootLocaleUpperCase("a"));
        Assert.assertEquals("A", StringUtils.toRootLocaleUpperCase("A"));
        Assert.assertNull(StringUtils.toRootLocaleUpperCase(null));
    }

    @Test
    public void default_5_toRootLocaleLowerCase() {
        Assert.assertEquals("a", StringUtils.toRootLocaleLowerCase("a"));
        Assert.assertEquals("a", StringUtils.toRootLocaleLowerCase("A"));
        Assert.assertNull(StringUtils.toRootLocaleLowerCase(null));
    }
}
