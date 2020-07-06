package com.htc.vita.core.util;

import com.htc.vita.core.log.Logger;

public class Convert {
    public static byte[] fromHexString(String data) {
        if (StringUtils.isNullOrEmpty(data)) {
            return null;
        }

        int size = data.length();
        byte[] result = new byte[size / 2];
        for (int i = 0; i < size; i += 2) {
            int upperByte = Character.digit(data.charAt(i), 16);
            int lowerByte = Character.digit(data.charAt(i + 1), 16);
            result[i / 2] = (byte) (upperByte * 16 + lowerByte);
        }
        return result;
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return "";
        }

        StringBuilder buffer = new StringBuilder(data.length * 2);
        for (byte b: data) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public static int toInt32(String data) {
        return toInt32(data, 0);
    }

    public static int toInt32(String data, int defaultValue) {
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
                    "Can not parse \"%s\" to int/int32",
                    data
            ));
        }
        return result;
    }
}
