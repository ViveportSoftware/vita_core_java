package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class MapBuilderTest {
    @Test
    public void default_0_put() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
    }

    @Test
    public void default_1_size() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
        Assert.assertEquals(1, mapBuilder.size());
    }

    @Test
    public void default_2_clear() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
        Assert.assertEquals(1, mapBuilder.size());
        Assert.assertNotNull(mapBuilder.clear());
        Assert.assertEquals(0, mapBuilder.size());
    }

    @Test
    public void default_3_putIfNotNull() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
        Assert.assertEquals(1, mapBuilder.size());
        Assert.assertNotNull(mapBuilder.putIfNotNull("testKey1", null));
        Assert.assertEquals(1, mapBuilder.size());
    }

    @Test
    public void default_4_toMap() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
        Assert.assertEquals(1, mapBuilder.size());
        Assert.assertNotNull(mapBuilder.putIfNotNull("testKey1", "testValue0"));
        Assert.assertEquals(2, mapBuilder.size());
        Map<String, String> map = mapBuilder.toMap();
        Assert.assertNotNull(map);
        Assert.assertEquals(2, map.size());
    }

    @Test
    public void default_5_putIfNotExists() {
        MapBuilder<String, String> mapBuilder = new MapBuilder<String, String>();
        Assert.assertNotNull(mapBuilder.put("testKey0", "testValue0"));
        Assert.assertEquals(1, mapBuilder.size());
        Assert.assertNotNull(mapBuilder.putIfNotExists("testKey0", "testValue1"));
        Assert.assertEquals(1, mapBuilder.size());
        Map<String, String> map = mapBuilder.toMap();
        Assert.assertNotNull(map);
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("testValue0", map.get("testKey0"));
    }
}
