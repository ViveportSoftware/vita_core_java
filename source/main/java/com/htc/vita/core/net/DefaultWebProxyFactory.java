package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class DefaultWebProxyFactory extends WebProxyFactory {
    private static final String TEST_URL = "https://www.microsoft.com/";
    private static final long CACHE_PERIOD_IN_MILLI = 1000L * 5;

    private static final Map<String, Map.Entry<WebProxyStatus, Long>> PROXY_STATUS_MAP = new HashMap<String, Map.Entry<WebProxyStatus, Long>>();

    @Override
    protected Proxy onGetWebProxy() {
        ProxySelector proxySelector = ProxySelector.getDefault();
        if (proxySelector == null) {
            return Proxy.NO_PROXY;
        }

        try {
            List<Proxy> proxyList = proxySelector.select(new URI(TEST_URL));
            if (proxyList == null || proxyList.size() <= 0) {
                return Proxy.NO_PROXY;
            }

            for (Proxy proxy : proxyList) {
                if (proxy == null) {
                    continue;
                }

                WebProxyStatus webProxyStatus = onGetWebProxyStatus(proxy);
                if (webProxyStatus == WebProxyStatus.Working || webProxyStatus == WebProxyStatus.NotSet) {
                    return proxy;
                }
                return proxy;
            }
        } catch (URISyntaxException e) {
            Logger.getInstance(DefaultWebProxyFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                    "Can not parse web proxy test URI, error: %s",
                    e.getMessage()
            ));
        }
        return Proxy.NO_PROXY;
    }

    @Override
    protected WebProxyStatus onGetWebProxyStatus(Proxy proxy) {
        SocketAddress socketAddress = proxy.address();
        if (socketAddress == null) {
            return WebProxyStatus.NotSet;
        }

        if (!(socketAddress instanceof InetSocketAddress)) {
            return WebProxyStatus.CannotTest;
        }

        long currentTimeInMilli = System.currentTimeMillis();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        String key = inetSocketAddress.toString();
        if (PROXY_STATUS_MAP.containsKey(key)) {
            Map.Entry<WebProxyStatus, Long> pair = PROXY_STATUS_MAP.get(key);
            if (pair != null) {
                WebProxyStatus webProxyStatus = pair.getKey();
                Long value = pair.getValue();
                if (value != null) {
                    long lastTimeInMilli = value;
                    if (currentTimeInMilli - lastTimeInMilli > 0L
                            && currentTimeInMilli - lastTimeInMilli < CACHE_PERIOD_IN_MILLI) {
                        return webProxyStatus;
                    }
                }
            }
        }

        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(TEST_URL);
            URLConnection urlConnection = url.openConnection(proxy);
            if (urlConnection == null) {
                return WebProxyStatus.CannotTest;
            }
            if (!(urlConnection instanceof HttpURLConnection)) {
                return WebProxyStatus.CannotTest;
            }
            httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setReadTimeout(2000);
            httpURLConnection.connect();
            httpURLConnection.getResponseMessage();
            PROXY_STATUS_MAP.put(
                    key,
                    new AbstractMap.SimpleEntry<WebProxyStatus, Long>(
                            WebProxyStatus.Working,
                            System.currentTimeMillis()
                    )
            );
            return WebProxyStatus.Working;
        } catch (MalformedURLException e) {
            Logger.getInstance(DefaultWebProxyFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                    "Can not parse web proxy test URI, error: %s",
                    e.getMessage()
            ));
        } catch (IOException e) {
            Logger.getInstance(DefaultWebProxyFactory.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                    "Can not get web proxy status, error: %s",
                    e.getMessage()
            ));
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return WebProxyStatus.Unknown;
    }
}
