package com.htc.vita.core.util;

public class StringUtils {
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
}
