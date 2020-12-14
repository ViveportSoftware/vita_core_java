package com.htc.vita.core.runtime;

public class EventData implements IEventData {
    private Object mSource;
    private long mTimestampUtcInMilli = 0L;

    @Override
    public Object getSource() {
        return mSource;
    }

    @Override
    public long getTimestampUtcInMilli() {
        return mTimestampUtcInMilli;
    }

    @Override
    public IEventData setSource(Object source) {
        mSource = source;
        return this;
    }

    @Override
    public IEventData setTimestampUtcInMilli(long timestampUtcInMilli) {
        mTimestampUtcInMilli = timestampUtcInMilli;
        return this;
    }
}
