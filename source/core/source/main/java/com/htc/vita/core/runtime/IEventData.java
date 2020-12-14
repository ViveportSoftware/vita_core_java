package com.htc.vita.core.runtime;

public interface IEventData {
    Object getSource();
    long getTimestampUtcInMilli();
    IEventData setSource(Object source);
    IEventData setTimestampUtcInMilli(long timestampUtcInMilli);
}
