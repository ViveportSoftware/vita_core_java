package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HttpWebResponse implements Closeable {

    public Map<String, List<String>> getHeaders() {
        Map<String, List<String>> result = null;
        try {
            result = onGetHeaders();
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new HashMap<String, List<String>>();
        }
        return result;
    }

    public InputStream getResponseStream() {
        InputStream result = null;
        try {
            result = onGetResponseStream();
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    public HttpWebResponseStatusCode getStatusCode() {
        HttpWebResponseStatusCode result = HttpWebResponseStatusCode.Unknown;
        try {
            result = onGetStatusCode();
        } catch (Exception e) {
            Logger.getInstance(HttpWebRequest.class.getSimpleName()).error(e.toString());
        }
        return result;
    }

    protected abstract Map<String, List<String>> onGetHeaders() throws Exception;
    protected abstract InputStream onGetResponseStream() throws Exception;
    protected abstract HttpWebResponseStatusCode onGetStatusCode() throws Exception;
}
