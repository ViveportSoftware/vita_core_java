package com.htc.vita.core.net;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlBuilderTest {
    @Test
    public void default_0_setScheme() {
        UrlBuilder builder = new UrlBuilder();
        Assert.assertNotNull(builder.setScheme(UrlScheme.HTTP));
        Assert.assertNotNull(builder.setScheme(UrlScheme.HTTPS));
    }

    @Test
    public void default_1_setAuthority() {
        UrlBuilder builder = new UrlBuilder();
        Assert.assertNotNull(builder.setAuthority("localhost"));
    }

    @Test
    public void default_2_setPort() {
        UrlBuilder builder = new UrlBuilder();
        Assert.assertNotNull(builder.setPort(32768));
    }

    @Test
    public void default_3_appendPath() {
        UrlBuilder builder = new UrlBuilder();
        Assert.assertNotNull(builder.appendPath("test1"));
        Assert.assertNotNull(builder.appendPath("test2"));
    }

    @Test
    public void default_4_appendQueryParameter() {
        UrlBuilder builder = new UrlBuilder();
        Assert.assertNotNull(builder.appendQueryParameter("testKey1", "testValue1"));
        Assert.assertNotNull(builder.appendQueryParameter("testKey2", "testValue2"));
    }

    @Test
    public void default_5_build() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendQueryParameter("a", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback?a=1", url.toString());
    }

    @Test
    public void default_5_build_withIPAddress() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("192.168.0.1")
                .setPort(8080)
                .appendPath("callback")
                .appendQueryParameter("a", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://192.168.0.1:8080/callback?a=1", url.toString());
    }

    @Test
    public void default_5_build_withDefaultPort() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(-1)
                .appendPath("callback")
                .appendQueryParameter("a", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost/callback?a=1", url.toString());
    }

    @Test
    public void default_5_build_withMultiPaths() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendPath("test")
                .appendQueryParameter("a", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback/test?a=1", url.toString());
    }

    @Test
    public void default_5_build_withTrailingPathSlash() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendPath("")
                .appendQueryParameter("a", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback/?a=1", url.toString());
    }

    @Test
    public void default_5_build_withEmptyQueryKey() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendQueryParameter("", "1")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback?=1", url.toString());
    }

    @Test
    public void default_5_build_withEmptyQueryValue() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendQueryParameter("a", "")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback?a=", url.toString());
    }

    @Test
    public void default_5_build_withEncodedQuery() throws MalformedURLException {
        URL url = new UrlBuilder()
                .setScheme(UrlScheme.HTTP)
                .setAuthority("localhost")
                .setPort(8080)
                .appendPath("callback")
                .appendQueryParameter("a", " b ")
                .build();
        Assert.assertNotNull(url);
        Assert.assertEquals("http://localhost:8080/callback?a=%20b%20", url.toString());
    }
}
