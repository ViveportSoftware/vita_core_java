package com.htc.vita.core.net;

import java.net.URL;

public class DefaultWebRequestFactory extends WebRequestFactory {
    private static final int TIMEOUT_IN_MILLI = 1000 * 20;

    @Override
    protected HttpWebRequest onGetHttpWebRequest(URL url) {
        return new DefaultHttpWebRequest(url)
                .setConnectTimeoutInMilli(onOverrideGetConnectTimeoutInMilli())
                .setProxy(WebProxyFactory.getInstance().getWebProxy())
                .setUserAgent(onOverrideGetUserAgentString());
    }

    protected int onOverrideGetConnectTimeoutInMilli() {
        return TIMEOUT_IN_MILLI;
    }

    protected String onOverrideGetUserAgentString() {
        return WebUserAgentFactory.getInstance()
                .getWebUserAgent()
                .toString();
    }
}
