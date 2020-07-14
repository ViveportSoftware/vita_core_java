package com.htc.vita.core.text;

import com.htc.vita.core.log.Logger;

import java.util.Base64;

/* package */ class Java8Base64 {
    private static boolean isReady = false;
    private static boolean isReadyChecked = false;

    private Java8Base64() {
    }

    private static boolean checkIsReady() {
        try {
            Class.forName("java.util.Base64");
            return true;
        } catch (ClassNotFoundException e) {
            // Skip
        }
        return false;
    }

    /* package */ static byte[] decode(
            String data,
            Base64Option base64Option) {
        if (base64Option == Base64Option.Basic) {
            return java.util.Base64.getDecoder().decode(data);
        }
        if (base64Option == Base64Option.UrlSafe) {
            return java.util.Base64.getUrlDecoder().decode(data);
        }
        if (base64Option == Base64Option.Mime) {
            return java.util.Base64.getMimeDecoder().decode(data);
        }
        Logger.getInstance(Java8Base64.class.getSimpleName()).error("Can not find available implementation");
        return null;
    }

    /* package */ static String encodeToString(
            byte[] data,
            Base64Option base64Option) {
        if (base64Option == Base64Option.Basic) {
            return java.util.Base64.getEncoder().encodeToString(data);
        }
        if (base64Option == Base64Option.UrlSafe) {
            return java.util.Base64.getUrlEncoder().encodeToString(data).replace(
                    "=",
                    ""
            );
        }
        if (base64Option == Base64Option.Mime) {
            return Base64.getMimeEncoder().encodeToString(data);
        }
        Logger.getInstance(Java8Base64.class.getSimpleName()).error("Can not find available implementation");
        return null;
    }

    /* package */ static boolean isReady() {
        if (isReadyChecked) {
            return isReady;
        }

        isReady = checkIsReady();
        isReadyChecked = true;
        return isReady;
    }
}
