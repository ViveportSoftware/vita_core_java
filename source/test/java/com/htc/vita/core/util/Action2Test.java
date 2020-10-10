package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class Action2Test {
    @Test
    public void default_0_add() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        Action2<String, String> action = new Action2<String, String>();
        action.add(new Action2.Handler<String, String>() {
                @Override
                protected void onHandle(String s, String s2) {
                    item0List[0] = s;
                    item1List[0] = s2;
                }
        });
        String value0 = "test0";
        String value1 = "test1";
        action.invoke(
                value0,
                value1
        );
        Assert.assertEquals(value0, item0List[0]);
        Assert.assertEquals(value1, item1List[0]);
    }

    @Test
    public void default_1_remove() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        Action2<String, String> action = new Action2<String, String>();
        Action2.Handler<String, String> handler = new Action2.Handler<String, String>() {
                @Override
                protected void onHandle(String s, String s2) {
                    item0List[0] = s;
                    item1List[0] = s2;
                }
        };
        action.remove(handler);
        String value0 = "test0";
        String value1 = "test1";
        action.invoke(
                value0,
                value1
        );
        Assert.assertNull(item0List[0]);
        Assert.assertNull(item1List[0]);
    }

    @Test
    public void default_2_addAndThenRemove() throws Exception {
        final String[] item0List = { null };
        final String[] item1List = { null };
        Action2<String, String> action = new Action2<String, String>();
        Action2.Handler<String, String> handler = new Action2.Handler<String, String>() {
                @Override
                protected void onHandle(String s, String s2) {
                    item0List[0] = s;
                    item1List[0] = s2;
                }
        };
        action.add(handler);
        action.remove(handler);
        String value0 = "test0";
        String value1 = "test1";
        action.invoke(
                value0,
                value1
        );
        Assert.assertNull(item0List[0]);
        Assert.assertNull(item1List[0]);
    }
}
