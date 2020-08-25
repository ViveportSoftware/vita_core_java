package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public class WebRequestFactoryTest {
    @Test
    public void default_0_getInstance() {
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
    }

    @Test
    public void default_1_getHttpWebRequest() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://www.google.com/search?q=firefox"));
        Assert.assertNotNull(httpWebRequest);
        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        InputStream responseStream = httpWebResponse.getResponseStream();
        Assert.assertNotNull(responseStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            buffer.append(line);
        }
        String response = buffer.toString();
        bufferedReader.close();
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(response));
        // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error("response: " + response);

        HttpWebResponseStatusCode statusCode = httpWebResponse.getStatusCode();
        Assert.assertEquals(HttpWebResponseStatusCode.Ok, statusCode);

        Map<String, List<String>> responseHeaders = httpWebResponse.getHeaders();
        for (String headerKey : responseHeaders.keySet()) {
            if (StringUtils.isNullOrWhiteSpace(headerKey)) {
                continue;
            }

            List<String> header = responseHeaders.get(headerKey);
            int index = 0;
            for (String item : header) {
                Assert.assertFalse(StringUtils.isNullOrWhiteSpace(item));
                // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).info("header[" + headerKey + "][" + index + "]: " + item);
                index++;
            }
        }

        httpWebResponse.close();
        httpWebRequest.close();
    }

    @Test
    public void default_1_getHttpWebRequest_withNonExistingHost() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://www.google.com2/search?q=firefox"));
        Assert.assertNotNull(httpWebRequest);
        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        try {
            InputStream responseStream = httpWebResponse.getResponseStream();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof UnknownHostException);
        }
    }

    @Test
    public void default_1_getHttpWebRequest_withNonExistingPort() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("http://localhost:51469/search?q=firefox"));
        Assert.assertNotNull(httpWebRequest);
        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        try {
            InputStream responseStream = httpWebResponse.getResponseStream();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ConnectException);
        }
    }

    @Test
    public void default_1_getHttpWebRequest_withNonExistingAddress() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("http://169.254.13.216/search?q=firefox"));
        Assert.assertNotNull(httpWebRequest);
        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        try {
            InputStream responseStream = httpWebResponse.getResponseStream();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof SocketException);
        }
    }
}
