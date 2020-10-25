package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

public abstract class EventBus {
    static {
        TypeRegistry.registerDefault(
                EventBus.class,
                DefaultEventBus.class
        );
    }

    public static <T extends EventBus> void register(Class<T> clazz) {
        TypeRegistry.register(
                EventBus.class,
                clazz
        );
    }

    public static EventBus getInstance() {
        return TypeRegistry.getInstance(EventBus.class);
    }

    public static <T extends EventBus> EventBus getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                EventBus.class,
                clazz
        );
    }

    public <T extends IEventData> boolean registerListener(
            Class<T> clazz,
            IEventListener<T> eventListener) {
        if (clazz == null || eventListener == null) {
            return false;
        }

        boolean result = false;
        try {
            result = onRegisterListener(
                    clazz,
                    eventListener
            );
        } catch (Exception e) {
            Logger.getInstance(EventBus.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public <T extends IEventData> EventBus trigger(
            Class<T> clazz,
            IEventData eventData) {
        if (clazz == null || eventData == null) {
            return this;
        }

        if (!clazz.isAssignableFrom(eventData.getClass())) {
            return this;
        }

        EventBus result = null;
        try {
            result = onTrigger(
                    clazz,
                    eventData
            );
        } catch (Exception e) {
            Logger.getInstance(EventBus.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public <T extends IEventData> boolean unregisterListener(
            Class<T> clazz,
            IEventListener<T> eventListener) {
        if (clazz == null || eventListener == null) {
            return false;
        }

        boolean result = false;
        try {
            result = onUnregisterListener(
                    clazz,
                    eventListener
            );
        } catch (Exception e) {
            Logger.getInstance(EventBus.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract <T extends IEventData> boolean onRegisterListener(
            Class<T> clazz,
            IEventListener<T> eventListener
    ) throws Exception;
    protected abstract <T extends IEventData> EventBus onTrigger(
            Class<T> clazz,
            IEventData eventData
    ) throws Exception;
    protected abstract <T extends IEventData> boolean onUnregisterListener(
            Class<T> clazz,
            IEventListener<T> eventListener
    ) throws Exception;
}
