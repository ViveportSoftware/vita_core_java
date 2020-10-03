package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OAuth2AuthorizationCodeReceiverTest {
    @Test
    public void default_0_getInstance() {
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = OAuth2AuthorizationCodeReceiver.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
    }

    @Test
    public void default_1_publicConstants() {
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeReceiver.OPTION_REDIRECT_URI));
    }

    @Test
    public void default_2_initialize() {
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = OAuth2AuthorizationCodeReceiver.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver.initialize());
    }

    @Test
    public void default_3_receive() {
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = OAuth2AuthorizationCodeReceiver.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver.initialize());
        OAuth2AuthorizationCodeReceiver.ReceiveResult receiveResult = oAuth2AuthorizationCodeReceiver.receive();
        OAuth2AuthorizationCodeReceiver.ReceiveStatus receiveStatus = receiveResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeReceiver.ReceiveStatus.NotImplemented, receiveStatus);
    }

    @Test
    public void default_4_close() throws IOException {
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = OAuth2AuthorizationCodeReceiver.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver.initialize());
        OAuth2AuthorizationCodeReceiver.ReceiveResult receiveResult = oAuth2AuthorizationCodeReceiver.receive();
        OAuth2AuthorizationCodeReceiver.ReceiveStatus receiveStatus = receiveResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeReceiver.ReceiveStatus.NotImplemented, receiveStatus);
        oAuth2AuthorizationCodeReceiver.close();
    }
}
