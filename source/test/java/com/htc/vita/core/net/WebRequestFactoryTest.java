package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
        InputStream inputStream = httpWebRequest.getInputStream();
        Assert.assertNotNull(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            buffer.append(line);
        }
        String input = buffer.toString();
        bufferedReader.close();
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(input));
        // Logger.getInstance().error("input: " + input);
    }
}
