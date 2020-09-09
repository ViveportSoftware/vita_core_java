package com.htc.vita.core.json;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFactoryTest {
    @Test
    public void dummy_0_getInstance() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
    }

    @Test
    public void dummy_1_createJsonArray() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
    }

    @Test
    public void dummy_2_createJsonObject() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
    }

    @Test
    public void dummy_3_getJsonArray() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.getJsonArray("[]");
        Assert.assertNull(jsonArray);
    }

    @Test
    public void dummy_4_getJsonObject() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.getJsonObject("{}");
        Assert.assertNull(jsonObject);
    }

    @Test
    public void jsonArray_00_size() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(0, jsonArray.size());
    }

    @Test
    public void jsonArray_01_parseBoolean() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(false, jsonArray.parseBoolean(0));
    }

    @Test
    public void jsonArray_01_parseBoolean_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(true, jsonArray.parseBoolean(0, true));
    }

    @Test
    public void jsonArray_02_parseDouble() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(0.0D, jsonArray.parseDouble(0), 0.00001D);
    }

    @Test
    public void jsonArray_02_parseDouble_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(1.0D, jsonArray.parseDouble(0, 1.0D), 0.00001D);
    }

    @Test
    public void jsonArray_03_parseFloat() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(0.0f, jsonArray.parseFloat(0), 0.00001f);
    }

    @Test
    public void jsonArray_03_parseFloat_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(1.0f, jsonArray.parseFloat(0, 1.0f), 0.00001f);
    }

    @Test
    public void jsonArray_04_parseInt() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(0, jsonArray.parseInt(0));
    }

    @Test
    public void jsonArray_04_parseInt_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(1, jsonArray.parseInt(0, 1));
    }

    @Test
    public void jsonArray_05_parseLong() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(0L, jsonArray.parseLong(0));
    }

    @Test
    public void jsonArray_05_parseLong_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(1L, jsonArray.parseLong(0, 1L));
    }

    @Test
    public void jsonArray_06_parseString() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(null, jsonArray.parseString(0));
    }

    @Test
    public void jsonArray_06_parseString_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals("test", jsonArray.parseString(0, "test"));
    }

    @Test
    public void jsonArray_07_parseJsonArray() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(null, jsonArray.parseJsonArray(0));
    }

    @Test
    public void jsonArray_08_parseJsonObject() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(null, jsonArray.parseJsonObject(0));
    }

    @Test
    public void jsonArray_09_append() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertNotNull(jsonArray.append(false));
        Assert.assertNotNull(jsonArray.append(0.0D));
        Assert.assertNotNull(jsonArray.append(0.0f));
        Assert.assertNotNull(jsonArray.append(0));
        Assert.assertNotNull(jsonArray.append(0L));
        Assert.assertNotNull(jsonArray.append("test"));
        JsonArray jsonArray2 = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray.append(jsonArray2));
        JsonObject jsonObject2 = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonArray.append(jsonObject2));
    }

    @Test
    public void jsonArray_10_insert() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertNotNull(jsonArray.insert(0,false));
        Assert.assertNotNull(jsonArray.insert(0,0.0D));
        Assert.assertNotNull(jsonArray.insert(0,0.0f));
        Assert.assertNotNull(jsonArray.insert(0,0));
        Assert.assertNotNull(jsonArray.insert(0,0L));
        Assert.assertNotNull(jsonArray.insert(0,"test"));
        JsonArray jsonArray2 = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray.insert(0, jsonArray2));
        JsonObject jsonObject2 = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonArray.insert(0, jsonObject2));
    }

    @Test
    public void jsonArray_11_appendAllString() {
        List<String> data = new ArrayList<String>();
        data.add("someItem0");
        data.add("someItem1");
        data.add("someItem2");
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonArray jsonArray = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonArray);
        Assert.assertNotNull(jsonArray.appendAllString(data));
    }

    @Test
    public void jsonObject_00_hasKey() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertFalse(jsonObject.hasKey("test"));
    }

    @Test
    public void jsonObject_01_parseBoolean() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(false, jsonObject.parseBoolean("test"));
    }

    @Test
    public void jsonObject_01_parseBoolean_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(true, jsonObject.parseBoolean("test", true));
    }

    @Test
    public void jsonObject_02_parseDouble() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(0.0D, jsonObject.parseDouble("test"), 0.00001D);
    }

    @Test
    public void jsonObject_02_parseDouble_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(1.0D, jsonObject.parseDouble("test", 1.0D), 0.00001D);
    }

    @Test
    public void jsonObject_03_parseFloat() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(0.0f, jsonObject.parseFloat("test"), 0.00001f);
    }

    @Test
    public void jsonObject_03_parseFloat_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(1.0f, jsonObject.parseFloat("test", 1.0f), 0.00001f);
    }

    @Test
    public void jsonObject_04_parseInt() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(0, jsonObject.parseInt("test"));
    }

    @Test
    public void jsonObject_04_parseInt_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(1, jsonObject.parseInt("test", 1));
    }

    @Test
    public void jsonObject_05_parseLong() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(0L, jsonObject.parseLong("test"));
    }

    @Test
    public void jsonObject_05_parseLong_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(1L, jsonObject.parseLong("test", 1L));
    }

    @Test
    public void jsonObject_06_parseString() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(null, jsonObject.parseString("test"));
    }

    @Test
    public void jsonObject_06_parseString_withDefault() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals("test", jsonObject.parseString("test", "test"));
    }

    @Test
    public void jsonObject_07_parseJsonArray() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(null, jsonObject.parseJsonArray("test"));
    }

    @Test
    public void jsonObject_08_parseJsonObject() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(null, jsonObject.parseJsonObject("test"));
    }

    @Test
    public void jsonObject_09_put() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.put("test", false));
        Assert.assertNotNull(jsonObject.put("test", 0.0D));
        Assert.assertNotNull(jsonObject.put("test", 0.0f));
        Assert.assertNotNull(jsonObject.put("test", 0));
        Assert.assertNotNull(jsonObject.put("test", 0L));
        Assert.assertNotNull(jsonObject.put("test", "test"));
        JsonArray jsonArray2 = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonObject.put("test", jsonArray2));
        JsonObject jsonObject2 = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject.put("test", jsonObject2));
    }

    @Test
    public void jsonObject_10_insert() {
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.put("test", false));
        Assert.assertNotNull(jsonObject.put("test", 0.0D));
        Assert.assertNotNull(jsonObject.put("test", 0.0f));
        Assert.assertNotNull(jsonObject.put("test", 0));
        Assert.assertNotNull(jsonObject.put("test", 0L));
        Assert.assertNotNull(jsonObject.put("test", "test"));
        JsonArray jsonArray2 = jsonFactory.createJsonArray();
        Assert.assertNotNull(jsonObject.put("test", jsonArray2));
        JsonObject jsonObject2 = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject.put("test", jsonObject2));
        Assert.assertEquals(0, jsonObject.allKeys().size());
    }

    @Test
    public void jsonObject_11_putAllString() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("testKey0", "testValue0");
        data.put("testKey1", "testValue1");
        data.put("testKey2", "testValue2");
        JsonFactory jsonFactory = JsonFactory.getInstance();
        Assert.assertNotNull(jsonFactory);
        JsonObject jsonObject = jsonFactory.createJsonObject();
        Assert.assertNotNull(jsonObject);
        Assert.assertNotNull(jsonObject.putAllString(data));
    }
}
