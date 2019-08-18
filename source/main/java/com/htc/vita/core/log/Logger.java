package com.htc.vita.core.log;

import com.htc.vita.core.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class Logger {
    private static final Map<String, Logger> sInstances = new HashMap<String, Logger>();
    private static final Object sInstancesLock = new Object();

    private static boolean sShouldSkipMethodResolution = false;
    private static Class<? extends Logger> sDefaultClass = ConsoleLogger.class;

    private String mName;

    public static <T extends Logger> void register(Class<T> clazz) {
        sDefaultClass = clazz;
        System.err.printf(
                "Registered default %s type to %s%n",
                Logger.class.getName(),
                sDefaultClass.getName()
        );
    }

    public static Logger getInstance() {
        return getInstance("");
    }

    public static Logger getInstance(String name) {
        Logger instance;
        try
        {
            instance = doGetInstance(sDefaultClass, name);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.getInstance(name)] " + e);
            System.err.println("Initializing " + ConsoleLogger.class.getName() + "...");
            instance = new ConsoleLogger(name);
        }
        return instance;
    }

    public static <T extends Logger> Logger getInstance(Class<T> clazz, Class type) {
        String name = "";
        if (type != null)
        {
            name = type.getName();
        }
        return getInstance(clazz, name);
    }

    public static <T extends Logger> Logger getInstance(Class<T> clazz) {
        return getInstance(clazz, "");
    }

    public static <T extends Logger> Logger getInstance(Class<T> clazz, String name) {
        Logger instance;
        try
        {
            instance = doGetInstance(clazz, name);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.getInstance(clazz, name))] " + e);
            System.err.println("Initializing " + ConsoleLogger.class.getName() + "...");
            instance = new ConsoleLogger(name);
        }
        return instance;
    }

    private static <T extends Logger> Logger doGetInstance(Class<T> type, String name) throws IllegalArgumentException {
        if (type == null || name == null)
        {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    Logger.class.getName())
            );
        }

        String key = type.getName() + "_" + name;
        Logger instance = null;
        if (sInstances.containsKey(key))
        {
            instance = sInstances.get(key);
        }
        if (instance == null)
        {
            System.err.println("Initializing " + key + "...");
            try {
                Constructor constructor = type.getConstructor(new Class[] { String.class });
                if (constructor != null) {
                    instance = (Logger) constructor.newInstance(new Object[] { name });
                }
            } catch (Exception e) {
                // Skip
            }
        }
        if (instance == null)
        {
            System.err.printf(
                    "Initializing %s[%s]...%n",
                    ConsoleLogger.class.getName(),
                    name
            );
            instance = new ConsoleLogger(name);
        }
        synchronized (sInstancesLock)
        {
            if (!sInstances.containsKey(key))
            {
                sInstances.put(key, instance);
            }
        }
        return instance;
    }

    protected Logger(String name) {
        if (!StringUtils.isNullOrWhiteSpace(name))
        {
            mName = name;
        }
    }

    public void debug(String message) {
        debug(getMethodName(), message);
    }

    public void debug(String tag, String message) {
        try
        {
            onDebug(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.debug(tag, message)] " + e);
        }
    }

    public void debug(String message, Exception exception) {
        debug(getMethodName(), message, exception);
    }

    public void debug(String tag, String message, Exception exception) {
        try
        {
            onDebug(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.debug(tag, message, exception)] " + e);
        }
    }

    public void error(String message) {
        error(getMethodName(), message);
    }

    public void error(String tag, String message) {
        try
        {
            onError(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.error(tag, message)] " + e);
        }
    }

    public void error(String message, Exception exception) {
        error(getMethodName(), message, exception);
    }

    public void error(String tag, String message, Exception exception) {
        try
        {
            onError(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.error(tag, message, exception)] " + e);
        }
    }

    public void fatal(String message) {
        fatal(getMethodName(), message);
    }

    public void fatal(String tag, String message) {
        try
        {
            onFatal(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.fatal(tag, message)] " + e);
        }
    }

    public void fatal(String message, Exception exception) {
        fatal(getMethodName(), message, exception);
    }

    public void fatal(String tag, String message, Exception exception) {
        try
        {
            onFatal(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.fatal(tag, message, exception)] " + e);
        }
    }

    private static String getMethodName() {
        String result = "";
        if (sShouldSkipMethodResolution) {
            return result;
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements != null && stackTraceElements.length >= 4) {
            result = stackTraceElements[3].getMethodName();
        }
        return result;
    }

    protected String getName() {
        return mName;
    }

    public void info(String message) {
        info(getMethodName(), message);
    }

    public void info(String tag, String message) {
        try
        {
            onInfo(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.info(tag, message)] " + e);
        }
    }

    public void info(String message, Exception exception) {
        info(getMethodName(), message, exception);
    }

    public void info(String tag, String message, Exception exception) {
        try
        {
            onInfo(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.info(tag, message, exception)] " + e);
        }
    }

    public void shutdown() {
        try
        {
            onShutdown();
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.shutdown] " + e);
        }
    }

    public void trace(String message) {
        trace(getMethodName(), message);
    }

    public void trace(String tag, String message) {
        try
        {
            onTrace(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.trace(tag, message)] " + e);
        }
    }

    public void trace(String message, Exception exception) {
        trace(getMethodName(), message, exception);
    }

    public void trace(String tag, String message, Exception exception) {
        try
        {
            onTrace(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.trace(tag, message, exception)] " + e);
        }
    }

    public void warn(String message) {
        warn(getMethodName(), message);
    }

    public void warn(String tag, String message) {
        try
        {
            onWarn(tag, message);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.warn(tag, message)] " + e);
        }
    }

    public void warn(String message, Exception exception) {
        warn(getMethodName(), message, exception);
    }

    public void warn(String tag, String message, Exception exception) {
        try
        {
            onWarn(tag, message, exception);
        }
        catch (Exception e)
        {
            System.err.println("[Fatal][Logger.warn(tag, message, exception)] " + e);
        }
    }

    protected abstract void onDebug(String tag, String message);
    protected abstract void onDebug(String tag, String message, Exception exception);
    protected abstract void onError(String tag, String message);
    protected abstract void onError(String tag, String message, Exception exception);
    protected abstract void onFatal(String tag, String message);
    protected abstract void onFatal(String tag, String message, Exception exception);
    protected abstract void onInfo(String tag, String message);
    protected abstract void onInfo(String tag, String message, Exception exception);
    protected abstract void onShutdown();
    protected abstract void onTrace(String tag, String message);
    protected abstract void onTrace(String tag, String message, Exception exception);
    protected abstract void onWarn(String tag, String message);
    protected abstract void onWarn(String tag, String message, Exception exception);
}
