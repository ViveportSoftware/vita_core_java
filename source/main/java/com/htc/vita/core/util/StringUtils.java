package com.htc.vita.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class StringUtils {
    public static final String STRING_ENCODING_UTF_8 = "UTF-8";

    private StringUtils() {
    }

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
            Collection<String> params) {
        if (params == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String item : params) {
            if (!isFirst) {
                builder.append(separator);
            }
            builder.append(item);
            isFirst = false;
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

    public static List<String> splitToList(
            String data,
            String regex) {
        return splitToList(
                data,
                regex,
                false
        );
    }

    public static List<String> splitToList(
            String data,
            String regex,
            boolean shouldKeepEmptyElement) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return result;
        }

        String[] itemArray = data.split(regex);
        if (itemArray.length <= 0) {
            return result;
        }
        for (String item : itemArray) {
            if (StringUtils.isNullOrEmpty(item) && !shouldKeepEmptyElement) {
                continue;
            }
            result.add(item);
        }
        return result;
    }

    public static Set<String> splitToSet(
            String data,
            String regex) {
        return splitToSet(
                data,
                regex,
                false
        );
    }

    public static Set<String> splitToSet(
            String data,
            String regex,
            boolean shouldKeepEmptyElement) {
        Set<String> result = new HashSet<String>();
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return result;
        }

        String[] itemArray = data.split(regex);
        if (itemArray.length <= 0) {
            return result;
        }
        for (String item : itemArray) {
            if (StringUtils.isNullOrEmpty(item) && !shouldKeepEmptyElement) {
                continue;
            }
            result.add(item);
        }
        return result;
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

    public static String toRootLocaleLowerCase(String data) {
        if (data == null) {
            return null;
        }
        return data.toLowerCase(Locale.ROOT);
    }

    public static String toRootLocaleUpperCase(String data) {
        if (data == null) {
            return null;
        }
        return data.toUpperCase(Locale.ROOT);
    }

    public static String urlDecodeByUtf8(String data) {
        if (data == null) {
            return null;
        }

        String result = null;
        try {
            result = URLDecoder.decode(data, STRING_ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            // Skip
        }
        return result;
    }

    public static String urlEncodeByUtf8(String data) {
        if (data == null) {
            return null;
        }

        String result = null;
        try {
            result = URLEncoder.encode(data, STRING_ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            // Skip
        }
        return result;
    }
}
