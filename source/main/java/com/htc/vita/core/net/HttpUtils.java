package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    private HttpUtils() {
    }

    public static String toEncodedQueryString(Map<String, String> queryParams) {
        if (queryParams == null || queryParams.size() <= 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (String key : queryParams.keySet()) {
            if (!isFirst) {
                builder.append("&");
            }

            String encodedKey = StringUtils.urlEncodeByUtf8(key)
                    .replace("+", "%20");
            String encodedValue = StringUtils.urlEncodeByUtf8(queryParams.get(key))
                    .replace("+", "%20");
            builder.append(encodedKey)
                    .append("=")
                    .append(encodedValue);
            isFirst = false;
        }
        return builder.toString();
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
