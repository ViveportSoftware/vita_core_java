package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class Action3Test {
    @Test
    public void default_0_add() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        final String[] item2List = { null };
        Action3<String, String, String> action = new Action3<String, String, String>();
        action.add(new Action3.Handler<String, String, String>() {
                @Override
                protected void onHandle(String s, String s2, String s3) {
                    item0List[0] = s;
                    item1List[0] = s2;
                    item2List[0] = s3;
                }
        });
        String value0 = "test0";
        String value1 = "test1";
        String value2 = "test2";
        action.invoke(
                value0,
                value1,
                value2
        );
        Assert.assertEquals(value0, item0List[0]);
        Assert.assertEquals(value1, item1List[0]);
        Assert.assertEquals(value2, item2List[0]);
    }

    @Test
    public void default_1_remove() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        final String[] item2List = { null };
        Action3<String, String, String> action = new Action3<String, String, String>();
        Action3.Handler<String, String, String> handler = new Action3.Handler<String, String, String>() {
                @Override
                protected void onHandle(String s, String s2, String s3) {
                    item0List[0] = s;
                    item1List[0] = s2;
                    item2List[0] = s3;
                }
        };
        action.remove(handler);
        String value0 = "test0";
        String value1 = "test1";
        String value2 = "test2";
        action.invoke(
                value0,
                value1,
                value2
        );
        Assert.assertNull(item0List[0]);
        Assert.assertNull(item1List[0]);
        Assert.assertNull(item2List[0]);
    }

    @Test
    public void default_2_addAndThenRemove() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        final String[] item2List = { null };
        Action3<String, String, String> action = new Action3<String, String, String>();
        Action3.Handler<String, String, String> handler = new Action3.Handler<String, String, String>() {
                @Override
                protected void onHandle(String s, String s2, String s3) {
                    item0List[0] = s;
                    item1List[0] = s2;
                    item2List[0] = s3;
                }
        };
        action.add(handler);
        action.remove(handler);
        String value0 = "test0";
        String value1 = "test1";
        String value2 = "test2";
        action.invoke(
                value0,
                value1,
                value2
        );
        Assert.assertNull(item0List[0]);
        Assert.assertNull(item1List[0]);
        Assert.assertNull(item2List[0]);
    }
}
