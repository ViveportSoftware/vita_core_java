package com.htc.vita.core.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String s) {
        if (s == null) {
            return true;
        }
        return s.length() <= 0;
    }

    public static boolean isNullOrWhiteSpace(String s) {
        if (s == null) {
            return true;
        }
        if (s.length() <= 0) {
            return true;
        }

        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
