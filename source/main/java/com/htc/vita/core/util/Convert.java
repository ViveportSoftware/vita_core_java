package com.htc.vita.core.util;

import com.htc.vita.core.log.Logger;

public class Convert {
    public static byte[] fromHexString(String content) {
        if (StringUtils.isNullOrEmpty(content)) {
            return null;
        }

        int size = content.length();
        byte[] result = new byte[size / 2];
        for (int i = 0; i < size; i += 2) {
            int upperByte = Character.digit(content.charAt(i), 16);
            int lowerByte = Character.digit(content.charAt(i + 1), 16);
            result[i / 2] = (byte) (upperByte * 16 + lowerByte);
        }
        return result;
    }

    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }

        StringBuilder buffer = new StringBuilder(bytes.length * 2);
        for (byte b: bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public static int toInt32(String content) {
        return toInt32(content, 0);
    }

    public static int toInt32(String content, int defaultValue) {
        if (StringUtils.isNullOrWhiteSpace(content)) {
            return defaultValue;
        }

        int result = defaultValue;
        try
        {
            result = Integer.parseInt(content);
        }
        catch (Exception e) {
            Logger.getInstance(Convert.class.getSimpleName()).error(String.format(
                    "Can not parse \"%s\" to int/int32",
                    content
            ));
        }
        return result;
    }
}
