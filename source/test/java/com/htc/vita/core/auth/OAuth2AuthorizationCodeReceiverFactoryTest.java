package com.htc.vita.core.auth;

import com.htc.vita.core.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OAuth2AuthorizationCodeReceiverFactoryTest {
    @Test
    public void default_0_getInstance() {
        OAuth2AuthorizationCodeReceiverFactory oAuth2AuthorizationCodeReceiverFactory = OAuth2AuthorizationCodeReceiverFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiverFactory);
    }

    @Test
    public void default_1_getReceiver() {
        OAuth2AuthorizationCodeReceiverFactory oAuth2AuthorizationCodeReceiverFactory = OAuth2AuthorizationCodeReceiverFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiverFactory);
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = oAuth2AuthorizationCodeReceiverFactory.getReceiver();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
    }

    @Test
    public void oAuth2AuthorizationCodeReceiver_0_publicConstants() {
        Assert.assertFalse(StringUtils.isNullOrWhiteSpace(OAuth2AuthorizationCodeReceiver.OPTION_REDIRECT_URI));
    }

    @Test
    public void oAuth2AuthorizationCodeReceiver_1_receive() {
        OAuth2AuthorizationCodeReceiverFactory oAuth2AuthorizationCodeReceiverFactory = OAuth2AuthorizationCodeReceiverFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiverFactory);
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = oAuth2AuthorizationCodeReceiverFactory.getReceiver();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
        OAuth2AuthorizationCodeReceiver.ReceiveResult receiveResult = oAuth2AuthorizationCodeReceiver.receive();
        OAuth2AuthorizationCodeReceiver.ReceiveStatus receiveStatus = receiveResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeReceiver.ReceiveStatus.NotImplemented, receiveStatus);
    }

    @Test
    public void oAuth2AuthorizationCodeReceiver_2_close() throws IOException {
        OAuth2AuthorizationCodeReceiverFactory oAuth2AuthorizationCodeReceiverFactory = OAuth2AuthorizationCodeReceiverFactory.getInstance();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiverFactory);
        OAuth2AuthorizationCodeReceiver oAuth2AuthorizationCodeReceiver = oAuth2AuthorizationCodeReceiverFactory.getReceiver();
        Assert.assertNotNull(oAuth2AuthorizationCodeReceiver);
        OAuth2AuthorizationCodeReceiver.ReceiveResult receiveResult = oAuth2AuthorizationCodeReceiver.receive();
        OAuth2AuthorizationCodeReceiver.ReceiveStatus receiveStatus = receiveResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeReceiver.ReceiveStatus.NotImplemented, receiveStatus);
        oAuth2AuthorizationCodeReceiver.close();
    }
}
