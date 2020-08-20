package com.htc.vita.core.util;

import java.io.UnsupportedEncodingException;

public class StringUtils {
    private static final String STRING_ENCODING_UTF_8 = "UTF-8";

    public static String fromBytesByUtf8(byte[] data) {
        if (data == null) {
            return null;
        }

        String result = null;
        try {
            result = new String(data, STRING_ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            // Skip
        }
        return result;
    }

    public static boolean isNullOrEmpty(String data) {
        if (data == null) {
            return true;
        }
        return data.length() <= 0;
    }

    public static boolean isNullOrWhiteSpace(String data) {
        if (data == null) {
            return true;
        }
        if (data.length() <= 0) {
            return true;
        }

        int length = data.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(data.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static byte[] toBytesByUtf8(String data) {
        if (data == null) {
            return null;
        }

        byte[] result = null;
        try {
            result = data.getBytes(STRING_ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            // Skip
        }
        return result;
    }
}
