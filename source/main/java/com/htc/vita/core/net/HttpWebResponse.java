package com.htc.vita.core.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class HttpWebResponse implements Closeable {

    public Map<String, List<String>> getHeaders() {
        return onGetHeaders();
    }

    public InputStream getResponseStream() throws IOException {
        return onGetResponseStream();
    }

    public HttpWebResponseStatusCode getStatusCode() throws IOException {
        return onGetStatusCode();
    }

    protected abstract Map<String, List<String>> onGetHeaders();
    protected abstract InputStream onGetResponseStream() throws IOException;
    protected abstract HttpWebResponseStatusCode onGetStatusCode() throws IOException;
}
