package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;

public abstract class HttpWebRequest implements Closeable {
    private URL mUrl = null;

    public HttpWebRequest(URL url) {
        mUrl = url;
    }

    public InputStream getInputStream() {
        InputStream result = null;
        try {
            result = onGetInputStream();
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public OutputStream getOutputStream() {
        OutputStream result = null;
        try {
            result = onGetOutputStream();
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected URL getUrl() {
        return mUrl;
    }

    public HttpWebRequest setAccept(String accept) {
        if (StringUtils.isNullOrWhiteSpace(accept)) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetAccept(accept);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setConnectTimeoutInMilli(int connectTimeoutInMilli) {
        if (connectTimeoutInMilli < 0) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetConnectTimeoutInMilli(connectTimeoutInMilli);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setContentType(String contentType) {
        if (StringUtils.isNullOrWhiteSpace(contentType)) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetContentType(contentType);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setCustomHeader(String key, String value) {
        if (StringUtils.isNullOrWhiteSpace(key) || StringUtils.isNullOrWhiteSpace(value)) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetCustomHeader(key, value);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setMethod(HttpWebRequestMethod httpWebRequestMethod) {
        if (httpWebRequestMethod == null) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetMethod(httpWebRequestMethod);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setProxy(Proxy proxy) {
        if (proxy == null) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetProxy(proxy);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebRequest setUserAgent(String userAgent) {
        if (StringUtils.isNullOrWhiteSpace(userAgent)) {
            return this;
        }

        HttpWebRequest result = null;
        try {
            result = onSetUserAgent(userAgent);
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract InputStream onGetInputStream() throws Exception;
    protected abstract OutputStream onGetOutputStream() throws Exception;
    protected abstract HttpWebRequest onSetAccept(String accept) throws Exception;
    protected abstract HttpWebRequest onSetConnectTimeoutInMilli(int connectTimeoutInMilli) throws Exception;
    protected abstract HttpWebRequest onSetContentType(String contentType) throws Exception;
    protected abstract HttpWebRequest onSetCustomHeader(
            String key,
            String value
    ) throws Exception;
    protected abstract HttpWebRequest onSetMethod(HttpWebRequestMethod httpWebRequestMethod) throws Exception;
    protected abstract HttpWebRequest onSetProxy(Proxy proxy) throws Exception;
    protected abstract HttpWebRequest onSetUserAgent(String userAgent) throws Exception;
}
