package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OAuth2AuthorizationCodeUserAgentTest {
    @Test
    public void default_0_getInstance() {
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = OAuth2AuthorizationCodeUserAgent.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
    }

    @Test
    public void default_1_publicConstants() {
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeUserAgent.OPTION_AUTHORIZATION_URL));
    }

    @Test
    public void default_2_initialize() {
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = OAuth2AuthorizationCodeUserAgent.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent.initialize());
    }

    @Test
    public void default_3_launch() {
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = OAuth2AuthorizationCodeUserAgent.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent.initialize());
        OAuth2AuthorizationCodeUserAgent.LaunchResult launchResult = oAuth2AuthorizationCodeUserAgent.launch();
        OAuth2AuthorizationCodeUserAgent.LaunchStatus launchStatus = launchResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeUserAgent.LaunchStatus.NotImplemented, launchStatus);
    }

    @Test
    public void default_4_close() throws IOException {
        OAuth2AuthorizationCodeUserAgent oAuth2AuthorizationCodeUserAgent = OAuth2AuthorizationCodeUserAgent.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent);
        Assert.assertNotNull(oAuth2AuthorizationCodeUserAgent.initialize());
        OAuth2AuthorizationCodeUserAgent.LaunchResult launchResult = oAuth2AuthorizationCodeUserAgent.launch();
        OAuth2AuthorizationCodeUserAgent.LaunchStatus launchStatus = launchResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeUserAgent.LaunchStatus.NotImplemented, launchStatus);
        oAuth2AuthorizationCodeUserAgent.close();
    }
}
