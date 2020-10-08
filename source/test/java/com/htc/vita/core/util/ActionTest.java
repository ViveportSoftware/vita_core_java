package com.htc.vita.core.util;

import org.junit.Assert;
import org.junit.Test;

public class ActionTest {
    @Test
    public void default_0_add() throws Exception {
        final boolean[] itemList = { false };
        Action action = new Action();
        action.add(new Action.Handler() {
                @Override
                protected void onHandle() {
                    itemList[0] = true;
                }
        });
        action.invoke();
        Assert.assertTrue(itemList[0]);
    }

    @Test
    public void default_1_remove() throws Exception {
        final boolean[] itemList = { false };
        Action action = new Action();
        Action.Handler handler = new Action.Handler() {
                @Override
                protected void onHandle() {
                    itemList[0] = true;
                }
        };
        action.remove(handler);
        action.invoke();
        Assert.assertFalse(itemList[0]);
    }

    @Test
    public void default_2_addAndThenRemove() throws Exception {
        final boolean[] itemList = { false };
        Action action = new Action();
        Action.Handler handler = new Action.Handler() {
            @Override
            protected void onHandle() {
                itemList[0] = true;
            }
        };
        action.add(handler);
        action.remove(handler);
        action.invoke();
        Assert.assertFalse(itemList[0]);
    }
}
