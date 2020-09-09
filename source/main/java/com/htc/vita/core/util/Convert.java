package com.htc.vita.core.util;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.text.Base64;

import java.util.Locale;

public class Convert {
    public static byte[] fromBase64String(String data) {
        if (StringUtils.isNullOrEmpty(data)) {
            return null;
        }

        return Base64.getInstance().decode(data);
    }

    public static byte[] fromHexString(String data) {
        if (StringUtils.isNullOrEmpty(data)) {
            return null;
        }

        int size = data.length();
        byte[] result = new byte[size / 2];
        for (int i = 0; i < size; i += 2) {
            int upperByte = Character.digit(
                    data.charAt(i),
                    16
            );
            int lowerByte = Character.digit(
                    data.charAt(i + 1),
                    16
            );
            result[i / 2] = (byte) (upperByte * 16 + lowerByte);
        }
        return result;
    }

    public static String toBase64String(byte[] data) {
        if (data == null) {
            return "";
        }

        return Base64.getInstance().encodeToString(data);
    }

    public static boolean toBoolean(String data) {
        return toBoolean(
                data,
                false
        );
    }

    public static boolean toBoolean(
            String data,
            boolean defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        boolean result = defaultValue;
        try
        {
            result = Boolean.parseBoolean(data);
        }
        catch (Exception e) {
            Logger.getInstance(Convert.class.getSimpleName()).error(String.format(
                    Locale.ROOT,
                    "Can not parse \"%s\" to boolean",
                    data
            ));
        }
        return result;
    }

    public static double toDouble(String data) {
        return toDouble(
                data,
                0.0D
        );
    }

    public static double toDouble(
            String data,
            double defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        double result = defaultValue;
        try
        {
            result = Double.parseDouble(data);
        }
        catch (Exception e) {
            Logger.getInstance(Convert.class.getSimpleName()).error(String.format(
                    Locale.ROOT,
                    "Can not parse \"%s\" to double",
                    data
            ));
        }
        return result;
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return "";
        }

        StringBuilder buffer = new StringBuilder(data.length * 2);
        for (byte b : data) {
            buffer.append(String.format(
                    Locale.ROOT,
                    "%02x",
                    b
            ));
        }
        return buffer.toString();
    }

    public static int toInt32(String data) {
        return toInt32(
                data,
                0
        );
    }

    public static int toInt32(
            String data,
            int defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        int result = defaultValue;
        try
        {
            result = Integer.parseInt(data);
        }
        catch (Exception e) {
            Logger.getInstance(Convert.class.getSimpleName()).error(String.format(
                    Locale.ROOT,
                    "Can not parse \"%s\" to int/int32",
                    data
            ));
        }
        return result;
    }

    public static long toLong(String data) {
        return toLong(
                data,
                0L
        );
    }

    public static long toLong(
            String data,
            long defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(data)) {
            return defaultValue;
        }

        long result = defaultValue;
        try
        {
            result = Long.parseLong(data);
        }
        catch (Exception e) {
            Logger.getInstance(Convert.class.getSimpleName()).error(String.format(
                    Locale.ROOT,
                    "Can not parse \"%s\" to long/int64",
                    data
            ));
        }
        return result;
    }
}
