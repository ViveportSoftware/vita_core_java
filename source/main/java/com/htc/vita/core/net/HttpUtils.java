package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    private HttpUtils() {
    }

    public static Map<String, String> toQueryParams(String query) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtils.isNullOrWhiteSpace(query)) {
            return result;
        }

        String[] queryParams = query.split("&");
        if (queryParams.length <= 0) {
            return result;
        }

        for (String queryParam: queryParams) {
            if (StringUtils.isNullOrWhiteSpace(query)) {
                continue;
            }

            int index = queryParam.indexOf("=");
            result.put(
                    StringUtils.urlDecodeByUtf8(queryParam.substring(0, index)),
                    StringUtils.urlDecodeByUtf8(queryParam.substring(index + 1))
            );
        }
        return result;
    }
}
