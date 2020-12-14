package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class DefaultHttpWebRequest extends HttpWebRequest {
    private final Map<String, String> mHeaderMap = new HashMap<String, String>();

    private int mConnectTimeoutInMilli = -1;
    private HttpURLConnection mHttpUrlConnection = null;
    private HttpWebRequestMethod mHttpWebRequestMethod = HttpWebRequestMethod.Get;
    private Proxy mProxy = null;
    private OutputStream mRequestStream = null;

    public DefaultHttpWebRequest(URL url) {
        super(url);
    }

    private boolean checkHttpUrlConnection() throws IOException {
        if (mHttpUrlConnection != null) {
            return true;
        }

        URL url = getUrl();
        if (url == null) {
            return false;
        }

        URLConnection urlConnection;
        if (mProxy == null) {
            urlConnection = url.openConnection();
        } else {
            urlConnection = url.openConnection(mProxy);
        }

        if (!(urlConnection instanceof HttpURLConnection)) {
            return false;
        }
        mHttpUrlConnection = (HttpURLConnection)urlConnection;

        // TODO configurable
        mHttpUrlConnection.setRequestProperty(
                "Accept-Encoding",
                "gzip, deflate"
        );

        for (String headerKey : mHeaderMap.keySet()) {
            if (StringUtils.isNullOrWhiteSpace(headerKey)) {
                continue;
            }
            String headerValue = mHeaderMap.get(headerKey);
            if (StringUtils.isNullOrWhiteSpace(headerValue)) {
                continue;
            }
            mHttpUrlConnection.setRequestProperty(
                    headerKey,
                    headerValue
            );
        }
        mHttpUrlConnection.setRequestMethod(mHttpWebRequestMethod.toString());

        // TODO update method mapping
        if (mHttpWebRequestMethod == HttpWebRequestMethod.Post) {
            mHttpUrlConnection.setDoOutput(true);
        }

        if (mConnectTimeoutInMilli >= 0) {
            mHttpUrlConnection.setConnectTimeout(mConnectTimeoutInMilli);
        }

        return true;
    }

    @Override
    public void close() throws IOException {
        if (mRequestStream != null) {
            mRequestStream.close();
            mRequestStream = null;
        }
        if (mHttpUrlConnection != null) {
            // mHttpUrlConnection.disconnect();
            mHttpUrlConnection = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    protected OutputStream onGetRequestStream() throws IOException {
        if (mRequestStream != null) {
            return mRequestStream;
        }

        boolean isConnectionAvailable = checkHttpUrlConnection();
        if (!isConnectionAvailable) {
            return null;
        }

        mRequestStream = mHttpUrlConnection.getOutputStream();
        return mRequestStream;
    }

    @Override
    protected HttpWebResponse onGetResponse() throws IOException {
        boolean isConnectionAvailable = checkHttpUrlConnection();
        if (!isConnectionAvailable) {
            return null;
        }
        return new DefaultHttpWebResponse(mHttpUrlConnection);
    }

    @Override
    protected HttpWebRequest onSetAccept(String accept) {
        mHeaderMap.put(
                "Accept",
                accept
        );
        return this;
    }

    @Override
    protected HttpWebRequest onSetConnectTimeoutInMilli(int connectTimeoutInMilli) {
        mConnectTimeoutInMilli = connectTimeoutInMilli;
        return this;
    }

    @Override
    protected HttpWebRequest onSetContentType(String contentType) {
        mHeaderMap.put(
                "Content-Type",
                contentType
        );
        return this;
    }

    @Override
    protected HttpWebRequest onSetCustomHeader(
            String key,
            String value) {
        mHeaderMap.put(
                key,
                value
        );
        return this;
    }

    @Override
    protected HttpWebRequest onSetMethod(HttpWebRequestMethod httpWebRequestMethod) {
        mHttpWebRequestMethod = httpWebRequestMethod;
        return this;
    }

    @Override
    protected HttpWebRequest onSetProxy(Proxy proxy) {
        mProxy = proxy;
        return this;
    }

    @Override
    protected HttpWebRequest onSetUserAgent(String userAgent) {
        mHeaderMap.put(
                "User-Agent",
                userAgent
        );
        return this;
    }
}
