package com.htc.vita.core.util;

import com.htc.vita.core.log.Logger;

public class Convert {
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
