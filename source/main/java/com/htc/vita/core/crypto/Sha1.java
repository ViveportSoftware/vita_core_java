package com.htc.vita.core.crypto;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

import java.io.File;

public abstract class Sha1 {
    static {
        TypeRegistry.registerDefault(
                Sha1.class,
                DefaultSha1.class
        );
    }

    public static <T extends Sha1> void register(Class<T> clazz) {
        TypeRegistry.register(
                Sha1.class,
                clazz
        );
    }

    public static Sha1 getInstance() {
        return TypeRegistry.getInstance(Sha1.class);
    }

    public static <T extends Sha1> Sha1 getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                Sha1.class,
                clazz
        );
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

    public boolean validateInHex(
            File file,
            String checksum) {
        if (file == null || !file.isFile() || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInHex(file));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public boolean validateInHex(
            String content,
            String checksum) {
        if (content == null || StringUtils.isNullOrWhiteSpace(checksum)) {
            return false;
        }

        boolean result = false;
        try {
            result = checksum.equalsIgnoreCase(onGenerateInHex(content));
        } catch (Exception e) {
            Logger.getInstance(Sha1.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract String onGenerateInHex(File file) throws Exception;
    protected abstract String onGenerateInHex(String content) throws Exception;
}
