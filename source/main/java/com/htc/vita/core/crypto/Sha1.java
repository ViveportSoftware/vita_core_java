package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Sha1 {
    private static final Map<String, Sha1> sInstances = new HashMap<String, Sha1>();
    private static final Object sInstancesLock = new Object();
    private static Class<? extends Sha1> sDefaultClass = DefaultSha1.class;

    public static <T extends Sha1> void register(Class<T> clazz) {
        sDefaultClass = clazz;
        System.err.printf(
                "Registered default %s type to %s%n",
                Sha1.class.getSimpleName(),
                sDefaultClass.getName()
        );
    }

    public static Sha1 getInstance() {
        return getInstance(sDefaultClass);
    }

    public static <T extends Sha1> Sha1 getInstance(Class<T> clazz) {
        Sha1 instance;
        try {
            instance = doGetInstance(clazz);
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).fatal(String.format(
                    "Instance initialization error: %s",
                    e
            ));
            Logger.getInstance(Sha1.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultSha1.class.getName()
            ));
            instance = new DefaultSha1();
        }
        return instance;
    }

    private static <T extends Sha1> Sha1 doGetInstance(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid argument to get %s instance",
                    Sha1.class.getName()
            ));
        }

        String key = String.format(
                "%s_",
                clazz.getName()
        );
        Sha1 instance = null;
        if (sInstances.containsKey(key)) {
            instance = sInstances.get(key);
        }
        if (instance == null) {
            Logger.getInstance(Sha1.class.getSimpleName()).info(String.format(
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
            Logger.getInstance(Sha1.class.getSimpleName()).info(String.format(
                    "Initializing %s...",
                    DefaultSha1.class.getName()
            ));
            instance = new DefaultSha1();
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

    public String generateInHex(File file) {
        if (file == null || !file.isFile()) {
            return "";
        }

        String result = "";
        try {
            result = onGenerateInHex(file);
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public String generateInHex(String content) {
        if (content == null) {
            return "";
        }

        String result = "";
        try {
            result = onGenerateInHex(content);
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInHex(File file, String checksum) {
        if (file == null || !file.isFile() || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.toLowerCase(Locale.ENGLISH).equals(onGenerateInHex(file));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInHex(String content, String checksum) {
        if (content == null || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.toLowerCase(Locale.ENGLISH).equals(onGenerateInHex(content));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract String onGenerateInHex(File file) throws Exception;
    protected abstract String onGenerateInHex(String content) throws Exception;
}
