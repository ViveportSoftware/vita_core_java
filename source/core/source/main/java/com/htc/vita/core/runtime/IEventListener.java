package com.htc.vita.core.runtime;

public interface IEventListener<T extends IEventData> {
    void processEvent(T eventData);
}