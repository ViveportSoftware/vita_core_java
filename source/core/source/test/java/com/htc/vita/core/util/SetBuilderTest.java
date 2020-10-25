package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class SetBuilderTest {
    @Test
    public void default_0_add() {
        SetBuilder<String> setBuilder = new SetBuilder<String>();
        Assert.assertNotNull(setBuilder.add("testItem0"));
    }

    @Test
    public void default_1_size() {
        SetBuilder<String> setBuilder = new SetBuilder<String>();
        Assert.assertNotNull(setBuilder.add("testItem0"));
        Assert.assertEquals(1, setBuilder.size());
        Assert.assertNotNull(setBuilder.add("testItem0"));
        Assert.assertEquals(1, setBuilder.size());
    }

    @Test
    public void default_2_addIfNotNull() {
        SetBuilder<String> setBuilder = new SetBuilder<String>();
        Assert.assertNotNull(setBuilder.add("testItem0"));
        Assert.assertEquals(1, setBuilder.size());
        Assert.assertNotNull(setBuilder.addIfNotNull(null));
        Assert.assertEquals(1, setBuilder.size());
    }

    @Test
    public void default_3_clear() {
        SetBuilder<String> setBuilder = new SetBuilder<String>();
        Assert.assertNotNull(setBuilder.add("testItem0"));
        Assert.assertEquals(1, setBuilder.size());
        Assert.assertNotNull(setBuilder.clear());
        Assert.assertEquals(0, setBuilder.size());
    }

    @Test
    public void default_4_toSet() {
        SetBuilder<String> setBuilder = new SetBuilder<String>();
        Assert.assertNotNull(setBuilder.add("testItem0"));
        Assert.assertNotNull(setBuilder.add("testItem1"));
        Assert.assertEquals(2, setBuilder.size());
        Set<String> set0 = setBuilder.toSet();
        Assert.assertEquals(2, set0.size());
    }
}
