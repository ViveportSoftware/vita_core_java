package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlBuilder {
    private final List<String> mPaths = new ArrayList<String>();
    private final Map<String, String> mQueryParameterMap = new HashMap<String, String>();

    private String mAuthority = "";
    private UrlScheme mUrlScheme = UrlScheme.Http;
    private int mPort = -1;

    public UrlBuilder appendPath(String path) {
        if (path != null) {
            mPaths.add(path);
        }
        return this;
    }

    public UrlBuilder appendQueryParameter(
            String key,
            String value) {
        if (key != null && value != null) {
            mQueryParameterMap.put(
                    key,
                    value
            );
        }
        return this;
    }

    public URL build() throws MalformedURLException {
        StringBuilder builder = new StringBuilder();
        builder.append(mUrlScheme.value()).append("://");
        if (!StringUtils.isNullOrWhiteSpace(mAuthority)) {
            builder.append(mAuthority);
            if (mPort >= 0) {
                builder.append(":").append(mPort);
            }
        }
        builder.append("/");

        boolean isFirst = true;
        for (String path : mPaths) {
            if (!isFirst) {
                builder.append("/");
            }
            builder.append(StringUtils.urlEncodeByUtf8(path));
            isFirst = false;
        }

        isFirst = true;
        for (String key : mQueryParameterMap.keySet()) {
            if (isFirst) {
                builder.append("?");
            } else {
                builder.append("&");
            }
            String value = mQueryParameterMap.get(key);
            String encodedKey = StringUtils.urlEncodeByUtf8(key).replace(
                    "+",
                    "%20"
            );
            String encodedValue = StringUtils.urlEncodeByUtf8(value).replace(
                    "+",
                    "%20"
            );
            builder.append(encodedKey)
                    .append("=")
                    .append(encodedValue);
            isFirst = false;
        }
        return new URL(builder.toString());
    }

    public UrlBuilder setAuthority(String authority) {
        if (!StringUtils.isNullOrWhiteSpace(authority)) {
            mAuthority = authority;
        }
        return this;
    }

    public UrlBuilder setScheme(UrlScheme urlScheme) {
        if (urlScheme != null) {
            mUrlScheme = urlScheme;
        }
        return this;
    }

    public UrlBuilder setPort(int port) {
        mPort = port;
        return this;
    }
}
