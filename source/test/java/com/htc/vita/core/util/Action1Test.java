package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class Action1Test {
    @Test
    public void default_0_add() throws Exception {
        final String[] itemList = { null };
        Action1<String> action = new Action1<String>();
        action.add(new Action1.Handler<String>() {
                @Override
                protected void onHandle(String s) {
                    itemList[0] = s;
                }
        });
        String value = "test";
        action.invoke(value);
        Assert.assertEquals(value, itemList[0]);
    }

    @Test
    public void default_1_remove() throws Exception {
        final String[] itemList = { null };
        Action1<String> action = new Action1<String>();
        Action1.Handler<String> handler = new Action1.Handler<String>() {
                @Override
                protected void onHandle(String s) {
                    itemList[0] = s;
                }
        };
        action.remove(handler);
        String value = "test";
        action.invoke(value);
        Assert.assertNull(itemList[0]);
    }

    @Test
    public void default_2_addAndThenRemove() throws Exception {
        final String[] itemList = { null };
        Action1<String> action = new Action1<String>();
        Action1.Handler<String> handler = new Action1.Handler<String>() {
                @Override
                protected void onHandle(String s) {
                    itemList[0] = s;
                }
        };
        action.add(handler);
        action.remove(handler);
        String value = "test";
        action.invoke(value);
        Assert.assertNull(itemList[0]);
    }
}
