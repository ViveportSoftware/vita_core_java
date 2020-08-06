package com.htc.vita.core.runtime;

import com.htc.vita.core.log.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultEventBus extends EventBus {
    private final Map<Class, List<IEventListener<?>>> mListenerListMap = new HashMap<Class, List<IEventListener<?>>>();

    private static <T extends IEventData> Method getCandidateMethod(
            Class<T> clazz,
            IEventListener<?> eventListener) {
        if (clazz == null || eventListener == null) {
            return null;
        }

        for (Method method : eventListener.getClass().getDeclaredMethods()) {
            if (method == null) {
                continue;
            }

            Class<?>[] parameterList = method.getParameterTypes();
            if (parameterList.length != 1) {
                continue;
            }

            if (parameterList[0].isAssignableFrom(clazz)) {
                return method;
            }
        }

        return null;
    }

    @Override
    protected <T extends IEventData> boolean onRegisterListener(
            Class<T> clazz,
            IEventListener<T> eventListener) {
        synchronized (mListenerListMap) {
            List<IEventListener<?>> listenerList = null;
            if (mListenerListMap.containsKey(clazz)) {
                listenerList = mListenerListMap.get(clazz);
            }
            if (listenerList == null) {
                listenerList = new ArrayList<IEventListener<?>>();
            }
            if (!listenerList.contains(eventListener)) {
                listenerList.add(eventListener);
            }
            mListenerListMap.put(clazz, listenerList);
        }

        return true;
    }

    @Override
    protected <T extends IEventData> EventBus onTrigger(
            Class<T> clazz,
            IEventData eventData) throws Exception {
        List<IEventListener<?>> listenerList;
        synchronized (mListenerListMap) {
            if (!mListenerListMap.containsKey(clazz)) {
                return this;
            }
            listenerList = mListenerListMap.get(clazz);
            if (listenerList == null) {
                return this;
            }
        }

        Method method = null;
        for (IEventListener eventListener : listenerList) {
            if (eventListener == null) {
                continue;
            }

            if (method == null) {
                method = getCandidateMethod(
                        clazz,
                        eventListener
                );
            }

            if (method == null) {
                Logger.getInstance(DefaultEventBus.class.getSimpleName()).error("Can not find candidate method in event listener");
                return this;
            }

            method.invoke(eventListener, eventData);
        }

        return this;
    }

    @Override
    protected <T extends IEventData> boolean onUnregisterListener(
            Class<T> clazz,
            IEventListener<T> eventListener) {
        synchronized (mListenerListMap) {
            if (!mListenerListMap.containsKey(clazz)) {
                return true;
            }
            List<IEventListener<?>> listenerList = mListenerListMap.get(clazz);
            if (listenerList == null) {
                return true;
            }
            if (listenerList.contains(eventListener)) {
                listenerList.remove(eventListener);
            }
        }

        return true;
    }
}
