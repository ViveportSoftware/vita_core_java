package com.htc.vita.core.text;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import com.htc.vita.core.util.TypeRegistry;

public abstract class Base64 {
    private static boolean sIsJava8ImplPreferred = true;

    static {
        TypeRegistry.registerDefault(
                Base64.class,
                DummyBase64.class
        );
    }

    public static <T extends Base64> void register(Class<T> clazz) {
        TypeRegistry.register(
                Base64.class,
                clazz
        );
    }

    public static void preferJava8Impl(boolean preferred) {
        if (sIsJava8ImplPreferred == preferred) {
            return;
        }

        Logger.getInstance(Base64.class.getSimpleName()).info(StringUtils.rootLocaleFormat(
                "Change Java8 implementation preference to: %s",
                preferred
        ));
        sIsJava8ImplPreferred = preferred;
    }

    public static boolean isJava8ImplPreferred() {
        return sIsJava8ImplPreferred;
    }

    public static Base64 getInstance() {
        return TypeRegistry.getInstance(Base64.class);
    }

    public static <T extends Base64> Base64 getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                Base64.class,
                clazz
        );
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
