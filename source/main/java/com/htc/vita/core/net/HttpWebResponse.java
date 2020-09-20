package com.htc.vita.core.net;

import com.htc.vita.core.util.StringUtils;

import java.io.*;
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

    public String readStringByUtf8() throws IOException {
        return readString(StringUtils.STRING_ENCODING_UTF_8);
    }

    public String readString(String encoding) throws IOException {
        InputStream responseStream = getResponseStream();
        if (responseStream == null) {
            return null;
        }

        BufferedReader reader = null;
        String result;
        try {
            if (StringUtils.isNullOrWhiteSpace(encoding)) {
                reader = new BufferedReader(new InputStreamReader(responseStream));
            } else {
                reader = new BufferedReader(new InputStreamReader(
                        responseStream,
                        encoding
                ));
            }

            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                // Skip
            }
        }
        return result;
    }

    protected abstract Map<String, List<String>> onGetHeaders();
    protected abstract InputStream onGetResponseStream() throws IOException;
    protected abstract HttpWebResponseStatusCode onGetStatusCode() throws IOException;
}
