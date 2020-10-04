package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OAuth2AuthorizationCodeUserAgentFactoryTest {
    @Test
    public void default_0_getInstance() {
        OAuth2AuthorizationCodeUserAgentFactory oAuth2AuthorizationCodeUserAgentFactory = OAuth2AuthorizationCodeUserAgentFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgentFactory);
    }

    @Test
    public void default_1_getUserAgent() {
        OAuth2AuthorizationCodeUserAgentFactory oAuth2AuthorizationCodeUserAgentFactory = OAuth2AuthorizationCodeUserAgentFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgentFactory);
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = oAuth2AuthorizationCodeUserAgentFactory.getUserAgent();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
    }

    @Test
    public void userAgent_0_publicConstants() {
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeUserAgent.OBJECT_ANDROID_WEBVIEW_INSTANCE));
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeUserAgent.OPTION_ANDROID_JAVASCRIPT_ACTION_MAP_ON_URL_PREFIX_FINISHED));
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeUserAgent.OPTION_AUTHORIZATION_URL));
    }

    @Test
    public void userAgent_1_launch() {
        OAuth2AuthorizationCodeUserAgentFactory oAuth2AuthorizationCodeUserAgentFactory = OAuth2AuthorizationCodeUserAgentFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgentFactory);
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = oAuth2AuthorizationCodeUserAgentFactory.getUserAgent();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
        OAuth2AuthorizationCodeUserAgent.LaunchResult launchResult = oAuth2AuthorizationCodeUserAgent.launch();
        OAuth2AuthorizationCodeUserAgent.LaunchStatus launchStatus = launchResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeUserAgent.LaunchStatus.NotImplemented, launchStatus);
    }

    @Test
    public void userAgent_2_close() throws IOException {
        OAuth2AuthorizationCodeUserAgentFactory oAuth2AuthorizationCodeUserAgentFactory = OAuth2AuthorizationCodeUserAgentFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgentFactory);
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = oAuth2AuthorizationCodeUserAgentFactory.getUserAgent();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
        OAuth2AuthorizationCodeUserAgent.LaunchResult launchResult = oAuth2AuthorizationCodeUserAgent.launch();
        OAuth2AuthorizationCodeUserAgent.LaunchStatus launchStatus = launchResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeUserAgent.LaunchStatus.NotImplemented, launchStatus);
        oAuth2AuthorizationCodeUserAgent.close();
    }
}
