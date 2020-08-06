package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class EventBus {
    private static final Map<String, EventBus> sInstances = new HashMap<String, EventBus>();
    private static final Object sInstancesLock = new Object();

    private static Class<? extends EventBus> sDefaultClass = DefaultEventBus.class;

    public static <T extends EventBus> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(EventBus.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                EventBus.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static EventBus getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends EventBus> EventBus getInstance(Class<T> clazz) {
        EventBus instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(EventBus.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(EventBus.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultEventBus.class.getName()
            ));
            instance = new DefaultEventBus();
        }
        return instance;
    }

    private static <T extends EventBus> EventBus doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    EventBus.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        EventBus instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(EventBus.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    key
            ));
            try {
                Constructor<T> constructor = clazz.getConstructor();
                instance = constructor.newInstance();
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null) {
            Logger.getInstance(EventBus.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultEventBus.class.getName()
            ));
            instance = new DefaultEventBus();
        }
        synchronized (sInstancesLock) {
            if (!sInstances.containsKey(key)) {
                sInstances.put(
                        key,
                        instance
                );
            }
        }
        return instance;
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
