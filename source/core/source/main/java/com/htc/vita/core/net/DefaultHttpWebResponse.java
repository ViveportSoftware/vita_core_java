package com.htc.vita.core.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class DefaultHttpWebResponse extends HttpWebResponse {
    private HttpURLConnection mHttpUrlConnection;
    private InputStream mResponseStream = null;

    public DefaultHttpWebResponse(HttpURLConnection httpURLConnection) {
        mHttpUrlConnection = httpURLConnection;
    }

    @Override
    public void close() throws IOException {
        if (mResponseStream != null) {
            mResponseStream.close();
            mResponseStream = null;
        }
        if (mHttpUrlConnection != null) {
            mHttpUrlConnection.disconnect();
            mHttpUrlConnection = null;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override
    protected Map<String, List<String>> onGetHeaders() {
        return mHttpUrlConnection.getHeaderFields();
    }

    @Override
    protected InputStream onGetResponseStream() throws IOException {
        if (mResponseStream != null) {
            return mResponseStream;
        }

        InputStream inputStream = null;
        try {
            inputStream = mHttpUrlConnection.getInputStream();
        } catch (IOException e) {
            // Skip
        }
        if (inputStream == null) {
            inputStream = mHttpUrlConnection.getErrorStream();
        }
        if (inputStream == null) {
            return null;
        }

        String contentEncoding = mHttpUrlConnection.getContentEncoding();
        if ("gzip".equalsIgnoreCase(contentEncoding)) {
            mResponseStream = new GZIPInputStream(inputStream);
        }
        if ("deflate".equalsIgnoreCase(contentEncoding)) {
            mResponseStream = new InflaterInputStream(inputStream);
        }
        if (mResponseStream == null) {
            mResponseStream = inputStream;
        }
        return mResponseStream;
    }

    @Override
    protected HttpWebResponseStatusCode onGetStatusCode() throws IOException {
        return HttpWebResponseStatusCode.fromValue(mHttpUrlConnection.getResponseCode());
    }
}
