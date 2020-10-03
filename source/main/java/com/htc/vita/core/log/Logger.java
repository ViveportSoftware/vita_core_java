package com.htc.vita.core.log;

import com.htc.vita.core.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Logger {
    private static final Map<String, Logger> INSTANCE_MAP = new HashMap<String, Logger>();

    private static boolean sShouldSkipMethodResolution = false;
    private static Class<? extends Logger> sDefaultClass = ConsoleLogger.class;

    private String mName;

    public static <T extends Logger> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        System.err.printf(
                Locale.ROOT,
                "Registered default %s type to %s%n",
                Logger.class.getSimpleName(),
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
            instance = doGetInstance(
                    sDefaultClass,
                    name
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.getInstance(name)] %s%n",
                    e
            );
            System.err.printf(
                    Locale.ROOT,
                    "Initializing %s...%n",
                    ConsoleLogger.class.getName()
            );
            instance = new ConsoleLogger(name);
        }
        return instance;
    }

    public static <T extends Logger> Logger getInstance(
            Class<T> clazz,
            Class<?> type) {
        String name = "";
        if (type != null)
        {
            name = type.getName();
        }
        return getInstance(
                clazz,
                name
        );
    }

    public static <T extends Logger> Logger getInstance(Class<T> clazz) {
        return getInstance(
                clazz,
                ""
        );
    }

    public static <T extends Logger> Logger getInstance(
            Class<T> clazz,
            String name) {
        Logger instance;
        try
        {
            instance = doGetInstance(
                    clazz,
                    name
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.getInstance(clazz, name))] %s%n",
                    e
            );
            System.err.printf(
                    Locale.ROOT,
                    "Initializing %s...%n",
                    ConsoleLogger.class.getName()
            );
            instance = new ConsoleLogger(name);
        }
        return instance;
    }

    private static <T extends Logger> Logger doGetInstance(
            Class<T> type,
            String name)
                    throws IllegalArgumentException {
        if (type == null || name == null)
        {
            throw new IllegalArgumentException(
                    String.format(
                            Locale.ROOT,
                            "Invalid argument to get %s instance",
                            Logger.class.getSimpleName()
                    )
            );
        }

        String key = String.format(
                Locale.ROOT,
                "%s_%s",
                type.getName(),
                name
        );
        Logger instance = null;
        if (INSTANCE_MAP.containsKey(key))
        {
            instance = INSTANCE_MAP.get(key);
        }
        if (instance == null)
        {
            System.err.printf(
                    Locale.ROOT,
                    "Initializing %s...%n",
                    key
            );
            try {
                Constructor<T> constructor = type.getConstructor(String.class);
                instance = constructor.newInstance(name);
            } catch (Exception e) {
                System.err.printf(
                        Locale.ROOT,
                        "[Fatal][Logger.doGetInstance(type, name)] %s%n",
                        e
                );
            }
        }
        if (instance == null)
        {
            System.err.printf(
                    Locale.ROOT,
                    "Initializing %s[%s]...%n",
                    ConsoleLogger.class.getName(),
                    name
            );
            instance = new ConsoleLogger(name);
        }
        synchronized (INSTANCE_MAP)
        {
            if (!INSTANCE_MAP.containsKey(key))
            {
                INSTANCE_MAP.put(
                        key,
                        instance
                );
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
        debug(
                getMethodName(),
                message
        );
    }

    public void debug(
            String tag,
            String message) {
        try
        {
            onDebug(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.debug(tag, message)] %s%n",
                    e
            );
        }
    }

    public void debug(
            String message,
            Exception exception) {
        debug(
                getMethodName(),
                message,
                exception
        );
    }

    public void debug(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onDebug(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.debug(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    public void error(String message) {
        error(
                getMethodName(),
                message
        );
    }

    public void error(
            String tag,
            String message) {
        try
        {
            onError(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.error(tag, message)] %s%n",
                    e
            );
        }
    }

    public void error(
            String message,
            Exception exception) {
        error(
                getMethodName(),
                message,
                exception
        );
    }

    public void error(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onError(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.error(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    public void fatal(String message) {
        fatal(
                getMethodName(),
                message
        );
    }

    public void fatal(
            String tag,
            String message) {
        try
        {
            onFatal(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.fatal(tag, message)] %s%n",
                    e
            );
        }
    }

    public void fatal(
            String message,
            Exception exception) {
        fatal(
                getMethodName(),
                message,
                exception
        );
    }

    public void fatal(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onFatal(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.fatal(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    private static String getMethodName() {
        String result = "";
        if (sShouldSkipMethodResolution) {
            return result;
        }
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 4) {
            // In generic JVM. StackTraceElement array may like the following:
            //
            // 0 = {StackTraceElement@1755} "java.lang.Thread.getStackTrace(Thread.java:1559)"
            // 1 = {StackTraceElement@1756} "com.htc.vita.core.log.Logger.getMethodName(Logger.java:363)"
            // 2 = {StackTraceElement@1757} "com.htc.vita.core.log.Logger.debug(Logger.java:183)"
            // 3 = {StackTraceElement@1750} "com.htc.vita.core.runtime.NativePlatformTest.java_1_getMachineId(NativePlatformTest.java:31)"
            result = stackTraceElements[3].getMethodName();
        }
        if (stackTraceElements.length >= 5) {
            String className = stackTraceElements[0].getClassName();
            if ("dalvik.system.VMStack".equals(className)) {
                // In DalvikVM. StackTraceElement array may like the following:
                //
                // 0 = {StackTraceElement@10985} "dalvik.system.VMStack.getThreadStackTrace(Native Method)"
                // 1 = {StackTraceElement@10986} "java.lang.Thread.getStackTrace(Thread.java:1538)"
                // 2 = {StackTraceElement@10987} "com.htc.vita.core.log.Logger.getMethodName(Logger.java:363)"
                // 3 = {StackTraceElement@10983} "com.htc.vita.core.log.Logger.debug(Logger.java:183)"
                // 4 = {StackTraceElement@10988} "com.htc.vita.mod.android.log.AndroidLoggerTest.default_0_debug(AndroidLoggerTest.java:13)"
                result = stackTraceElements[4].getMethodName();
            }
        }
        return result;
    }

    protected String getName() {
        return mName;
    }

    public void info(String message) {
        info(
                getMethodName(),
                message
        );
    }

    public void info(
            String tag,
            String message) {
        try
        {
            onInfo(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.info(tag, message)] %s%n",
                    e
            );
        }
    }

    public void info(
            String message,
            Exception exception) {
        info(
                getMethodName(),
                message,
                exception
        );
    }

    public void info(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onInfo(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.info(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    public static void skipMethodResolution(boolean shouldSkipMethodResolution) {
        if (sShouldSkipMethodResolution != shouldSkipMethodResolution) {
            sShouldSkipMethodResolution = shouldSkipMethodResolution;
            System.err.printf(
                    Locale.ROOT,
                    "Set method resolution skipping to %s%n",
                    sShouldSkipMethodResolution
            );
        }
    }

    public void shutdown() {
        try
        {
            onShutdown();
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.shutdown] %s%n",
                    e
            );
        }
    }

    public void trace(String message) {
        trace(
                getMethodName(),
                message
        );
    }

    public void trace(
            String tag,
            String message) {
        try
        {
            onTrace(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.trace(tag, message)] %s%n",
                    e
            );
        }
    }

    public void trace(
            String message,
            Exception exception) {
        trace(
                getMethodName(),
                message,
                exception
        );
    }

    public void trace(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onTrace(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.trace(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    public void warn(String message) {
        warn(
                getMethodName(),
                message
        );
    }

    public void warn(
            String tag,
            String message) {
        try
        {
            onWarn(
                    tag,
                    message
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.warn(tag, message)] %s%n",
                    e
            );
        }
    }

    public void warn(
            String message,
            Exception exception) {
        warn(
                getMethodName(),
                message,
                exception
        );
    }

    public void warn(
            String tag,
            String message,
            Exception exception) {
        try
        {
            onWarn(
                    tag,
                    message,
                    exception
            );
        }
        catch (Exception e)
        {
            System.err.printf(
                    Locale.ROOT,
                    "[Fatal][Logger.warn(tag, message, exception)] %s%n",
                    e
            );
        }
    }

    protected abstract void onDebug(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onDebug(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
    protected abstract void onError(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onError(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
    protected abstract void onFatal(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onFatal(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
    protected abstract void onInfo(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onInfo(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
    protected abstract void onShutdown() throws Exception;
    protected abstract void onTrace(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onTrace(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
    protected abstract void onWarn(
            String tag,
            String message
    ) throws Exception;
    protected abstract void onWarn(
            String tag,
            String message,
            Exception exception
    ) throws Exception;
}
