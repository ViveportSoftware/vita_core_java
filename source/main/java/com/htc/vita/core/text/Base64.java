package com.htc.vita.core.text;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class Base64 {
    private static final Map<String, Base64> sInstances = new HashMap<String, Base64>();
    private static final Object sInstancesLock = new Object();

    private static Class<? extends Base64> sDefaultClass = DummyBase64.class;
    private static boolean sIsJava8ImplPreferred = true;

    public static <T extends Base64> void register(Class<T> clazz) {
        if (sDefaultClass == clazz) {
            return;
        }

        sDefaultClass = clazz;
        Logger.getInstance(Base64.class.getSimpleName()).info(String.format(
                "Registered default %s type to %s%n",
                Base64.class.getSimpleName(),
                sDefaultClass.getName()
        ));
    }

    public static void preferJava8Impl(boolean preferred) {
        if (sIsJava8ImplPreferred == preferred) {
            return;
        }

        Logger.getInstance(Base64.class.getSimpleName()).info(String.format(
                "Change Java8 implementation preference to: %s",
                preferred
        ));
        sIsJava8ImplPreferred = preferred;
    }

    public static boolean isJava8ImplPreferred() {
        return sIsJava8ImplPreferred;
    }

    public static Base64 getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends Base64> Base64 getInstance(Class<T> clazz) {
        Base64 instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(Base64.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(Base64.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyBase64.class.getName()
            ));
            instance = new DummyBase64();
        }
        return instance;
    }

    private static <T extends Base64> Base64 doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    Base64.class.getSimpleName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        Base64 instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(Base64.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(Base64.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DummyBase64.class.getName()
            ));
            instance = new DummyBase64();
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

    public byte[] decode(String data) {
        return decode(
                data,
                Base64Option.Basic
        );
    }

    public byte[] decode(
            String data,
            Base64Option option) {
        if (StringUtils.isNullOrEmpty(data)) {
            return null;
        }

        if (sIsJava8ImplPreferred && Java8Base64.isReady()) {
            return Java8Base64.decode(
                    data,
                    option
            );
        }

        byte[] result = null;
        try {
            result = onDecode(
                    data,
                    option
            );
        } catch (Exception e) {
            Logger.getInstance(Base64.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String encodeToString(byte[] data) {
        return encodeToString(
                data,
                Base64Option.Basic
        );
    }

    public String encodeToString(
            byte[] data,
            Base64Option option) {
        if (data == null) {
            return null;
        }

        if (sIsJava8ImplPreferred && Java8Base64.isReady()) {
            return Java8Base64.encodeToString(
                    data,
                    option
            );
        }

        String result = null;
        try {
            result = onEncodeToString(
                    data,
                    option
            );
        } catch (Exception e) {
            Logger.getInstance(Base64.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract byte[] onDecode(
            String data,
            Base64Option option
    ) throws Exception;
    protected abstract String onEncodeToString(
            byte[] data,
            Base64Option option
    ) throws Exception;
}
