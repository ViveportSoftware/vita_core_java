package com.htc.vita.core.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

public class StringUtils {
    public static final String STRING_ENCODING_UTF_8 = "UTF-8";

    public static String fromBytesByUtf8(byte[] data) {
        if (data == null) {
            return null;
        }

        String result = null;
        try {
            result = new String(
                    data,
                    STRING_ENCODING_UTF_8
            );
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

    public static String join(
            String separator,
            List<String> params){
        if (params == null) {
            return null;
        }
        if (params.size() <= 0) {
            return "";
        }

        if (params.size() == 1) {
            return params.get(0);
        }

        StringBuilder builder = new StringBuilder(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            builder.append(separator).append(params.get(i));
        }
        return builder.toString();
    }

    public static String join(
            String separator,
            String... params) {
        if (params == null) {
            return null;
        }
        if (params.length == 0) {
            return "";
        }

        if (params.length == 1) {
            return params[0];
        }

        StringBuilder builder = new StringBuilder(params[0]);
        for (int i = 1; i < params.length; i++) {
            builder.append(separator).append(params[i]);
        }
        return builder.toString();
    }

    public static String rootLocaleFormat(
            String format,
            Object... args) {
        return String.format(
                Locale.ROOT,
                format,
                args
        );
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
