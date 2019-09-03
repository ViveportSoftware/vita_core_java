package com.htc.vita.core.net;

import org.junit.Assert;
import org.junit.Test;

import java.net.Proxy;

public class WebProxyFactoryTest {
    @Test
    public void default_0_getInstance() {
        WebProxyFactory webProxyFactory = WebProxyFactory.getInstance();
        Assert.assertNotNull(webProxyFactory);
    }

    @Test
    public void default_1_getWebProxy() {
        WebProxyFactory webProxyFactory = WebProxyFactory.getInstance();
        Assert.assertNotNull(webProxyFactory);
        Proxy proxy = webProxyFactory.getWebProxy();
        Assert.assertNotNull(proxy);
    }

    @Test
    public void default_2_getWebProxyStatus() {
        WebProxyFactory webProxyFactory = WebProxyFactory.getInstance();
        Assert.assertNotNull(webProxyFactory);
        Proxy proxy = webProxyFactory.getWebProxy();
        Assert.assertNotNull(proxy);
        // proxy = new Proxy(Proxy.Type.HTTP, new java.net.InetSocketAddress("localhost", 8888));
        WebProxyFactory.WebProxyStatus webProxyStatus = webProxyFactory.getWebProxyStatus(proxy);
        Assert.assertNotSame(webProxyStatus, WebProxyFactory.WebProxyStatus.Unknown);
    }
}
