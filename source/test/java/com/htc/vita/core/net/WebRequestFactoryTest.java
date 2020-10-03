package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.Convert;
import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WebRequestFactoryTest {

    static {
        // SSLTrustManager.trustAll();
    }

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
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
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
        httpWebResponse.close();
        httpWebRequest.close();
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
        httpWebResponse.close();
        httpWebRequest.close();
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
            Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error(e.toString());
            Assert.assertTrue(e instanceof SocketException || e instanceof SocketTimeoutException);
        }
        httpWebResponse.close();
        httpWebRequest.close();
    }

    @Test
    public void default_1_getHttpWebRequest_withGet() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
        Assert.assertNotNull(httpWebRequest);
        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        HttpWebResponseStatusCode statusCode = httpWebResponse.getStatusCode();
        Assert.assertEquals(HttpWebResponseStatusCode.Ok, statusCode);

        InputStream responseStream = httpWebResponse.getResponseStream();
        Assert.assertNotNull(responseStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line);
        }
        String response = buffer.toString();
        bufferedReader.close();
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(response));
        // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error("response: " + response);
        responseStream.close();

        httpWebResponse.close();
        httpWebRequest.close();
    }

    @Test
    public void default_1_getHttpWebRequest_withPost() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://postman-echo.com/post"));
        Assert.assertNotNull(httpWebRequest);
        httpWebRequest.setMethod(HttpWebRequestMethod.Post);
        OutputStream requestStream = httpWebRequest.getRequestStream();
        Assert.assertNotNull(requestStream);

        String data = "This is expected to be sent back as part of response body.";
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(requestStream));
        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();

        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        HttpWebResponseStatusCode statusCode = httpWebResponse.getStatusCode();
        Assert.assertEquals(HttpWebResponseStatusCode.Ok, statusCode);

        InputStream responseStream = httpWebResponse.getResponseStream();
        Assert.assertNotNull(responseStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder buffer = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            buffer.append(line);
        }
        String response = buffer.toString();
        bufferedReader.close();
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(response));
        // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error("response: " + response);
        responseStream.close();

        httpWebResponse.close();
        httpWebRequest.close();
    }

    @Test
    public void default_1_getHttpWebRequest_withPost2() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://postman-echo.com/post"));
        Assert.assertNotNull(httpWebRequest);
        httpWebRequest.setMethod(HttpWebRequestMethod.Post);
        String data = "This is expected to be sent back as part of response body.";
        Assert.assertNotNull(httpWebRequest.writeStringByUtf8(data));

        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        HttpWebResponseStatusCode statusCode = httpWebResponse.getStatusCode();
        Assert.assertEquals(HttpWebResponseStatusCode.Ok, statusCode);

        String response = httpWebResponse.readStringByUtf8();
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(response));
        // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error("response: " + response);

        httpWebResponse.close();
        httpWebRequest.close();
    }

    @Ignore("Not stable")
    @Test
    public void default_1_getHttpWebRequest_withStatus429() throws IOException {
        // System.setProperty("java.net.useSystemProxies", "true");
        WebRequestFactory webRequestFactory = WebRequestFactory.getInstance();
        Assert.assertNotNull(webRequestFactory);
        HttpWebRequest httpWebRequest = webRequestFactory.getHttpWebRequest(new URL("https://httpstat.us/429"));
        Assert.assertNotNull(httpWebRequest);

        HttpWebResponse httpWebResponse = httpWebRequest.getResponse();
        Assert.assertNotNull(httpWebResponse);
        HttpWebResponseStatusCode statusCode = httpWebResponse.getStatusCode();
        Assert.assertEquals(HttpWebResponseStatusCode.TooManyRequests, statusCode);
        Map<String, List<String>> headers = httpWebResponse.getHeaders();
        Assert.assertTrue(headers.containsKey("Retry-After"));
        String retryAfterString = headers.get("Retry-After").get(0);
        int retryAfter = Convert.toInt32(retryAfterString);
        // Logger.getInstance(WebRequestFactoryTest.class.getSimpleName()).error("retryAfter: " + retryAfter);
        Assert.assertTrue(retryAfter > 0);

        httpWebResponse.close();
        httpWebRequest.close();
    }
}
