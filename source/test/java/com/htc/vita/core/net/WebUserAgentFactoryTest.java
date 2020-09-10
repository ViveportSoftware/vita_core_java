package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class WebUserAgentFactoryTest {
    @Test
    public void default_0_getInstance() {
        WebUserAgentFactory webUserAgentFactory = WebUserAgentFactory.getInstance();
        Assert.assertNotNull(webUserAgentFactory);
    }

    @Test
    public void default_1_getWebUserAgent() {
        WebUserAgentFactory webUserAgentFactory = WebUserAgentFactory.getInstance();
        Assert.assertNotNull(webUserAgentFactory);
        WebUserAgent webUserAgent = webUserAgentFactory.getWebUserAgent();
        Assert.assertNotNull(webUserAgent);
    }

    @Test
    public void default_2_getWebUserAgentString() {
        WebUserAgentFactory webUserAgentFactory = WebUserAgentFactory.getInstance();
        Assert.assertNotNull(webUserAgentFactory);
        WebUserAgent webUserAgent = webUserAgentFactory.getWebUserAgent();
        Assert.assertNotNull(webUserAgent);
        String webUserAgentString = webUserAgent.toString();
        Assert.assertTrue(webUserAgentString.startsWith("JRE/"));
        Logger.getInstance(WebUserAgentFactoryTest.class.getSimpleName()).info(String.format(
                Locale.ROOT,
                "user agent: %s",
                webUserAgentString
        ));
    }
}
