package com.htc.vita.core.runtime;

import com.htc.vita.core.internal.TaskRunner;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

public class DefaultEventBus extends EventBus {
    private final Map<Class<?>, List<IEventListener<?>>> mListenerListMap = new HashMap<Class<?>, List<IEventListener<?>>>();

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
            mListenerListMap.put(
                    clazz,
                    listenerList
            );
        }

        return true;
    }

    @Override
    protected <T extends IEventData> EventBus onTrigger(
            Class<T> clazz,
            final IEventData eventData) {
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
        for (final IEventListener<?> eventListener : listenerList) {
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

            final Method methodInTask = method;
            TaskRunner.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            methodInTask.invoke(
                                    eventListener,
                                    eventData
                            );
                        } catch (Exception e) {
                            Logger.getInstance(DefaultEventBus.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                                    "Can not execute task successfully. %s",
                                    e.toString()
                            ));
                        }
                    }
            });
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
            listenerList.remove(eventListener);
        }

        return true;
    }
}
