package com.htc.vita.core.runtime;

import org.junit.Assert;
import org.junit.Test;

public class EventBusTest {
    @Test
    public void default_0_getInstance() {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
    }

    @Test
    public void default_1_registerListener() {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener));
    }

    @Test
    public void default_2_unregisterListener() {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener));
    }

    @Test
    public void default_3_trigger() throws InterruptedException {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener0 = new MyEventListener();
        MyEventListener myEventListener1 = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener1));
        MyEventData myEventData = new MyEventData();
        Assert.assertNotNull(eventBus.trigger(MyEventData.class, myEventData));

        Thread.sleep(1000);

        Assert.assertTrue(myEventListener0.isEventProcessed());
        Assert.assertTrue(myEventListener1.isEventProcessed());
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener1));
    }

    @Test
    public void default_3_trigger_withOtherType() throws InterruptedException {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener0 = new MyEventListener();
        MyEventListener myEventListener1 = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener1));
        OtherEventData otherEventData = new OtherEventData();
        Assert.assertNotNull(eventBus.trigger(MyEventData.class, otherEventData));

        Thread.sleep(1000);

        Assert.assertFalse(myEventListener0.isEventProcessed());
        Assert.assertFalse(myEventListener1.isEventProcessed());
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener1));
    }

    @Test
    public void default_3_trigger_withSubType() throws InterruptedException {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener0 = new MyEventListener();
        MyEventListener myEventListener1 = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener1));
        MySubEventData mySubEventData = new MySubEventData();
        Assert.assertNotNull(eventBus.trigger(MyEventData.class, mySubEventData));

        Thread.sleep(1000);

        Assert.assertTrue(myEventListener0.isEventProcessed());
        Assert.assertTrue(myEventListener1.isEventProcessed());
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener1));
    }

    @Test
    public void default_3_trigger_afterUnregisterListener() throws InterruptedException {
        EventBus eventBus = EventBus.getInstance();
        Assert.assertNotNull(eventBus);
        MyEventListener myEventListener0 = new MyEventListener();
        MyEventListener myEventListener1 = new MyEventListener();
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.registerListener(MyEventData.class, myEventListener1));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener0));
        Assert.assertTrue(eventBus.unregisterListener(MyEventData.class, myEventListener1));
        MyEventData myEventData = new MyEventData();
        Assert.assertNotNull(eventBus.trigger(MyEventData.class, myEventData));

        Thread.sleep(1000);

        Assert.assertFalse(myEventListener0.isEventProcessed());
        Assert.assertFalse(myEventListener1.isEventProcessed());
    }

    public static class MyEventData extends EventData {
    }

    public static class MyEventListener implements IEventListener<MyEventData> {
        private boolean mEventProcessed;

        public boolean isEventProcessed() {
            return mEventProcessed;
        }

        @Override
        public void processEvent(MyEventData eventData) {
            mEventProcessed = true;
        }
    }

    public static class MySubEventData extends MyEventData {
    }

    public static class OtherEventData extends EventData {
    }
}
