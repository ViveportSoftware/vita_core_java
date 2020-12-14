package com.htc.vita.core.auth;

import org.junit.Assert;
import org.junit.Test;

public class OAuth2ClientFactoryTest {
    @Test
    public void default_0_getInstance() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
    }

    @Test
    public void default_1_getAuthorizationCodeClient() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClient client0 = oAuth2ClientFactory.getAuthorizationCodeClient(null);
        Assert.assertNotNull(client0);
        OAuth2AuthorizationCodeClientConfig clientConfig = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client1 = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig);
        Assert.assertNotNull(client1);
        Assert.assertNotSame(client0, client1);
    }

    @Test
    public void authorizationCodeClient_0_authorize() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClientConfig clientConfig = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig);
        Assert.assertNotNull(client);
        OAuth2AuthorizationCodeClient.AuthorizeResult authorizeResult = client.authorize();
        OAuth2AuthorizationCodeClient.AuthorizeStatus authorizeStatus = authorizeResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeClient.AuthorizeStatus.NotImplemented, authorizeStatus);
    }

    @Test
    public void authorizationCodeClient_1_getConfig() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClientConfig clientConfig0 = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig0);
        Assert.assertNotNull(client);
        OAuth2AuthorizationCodeClientConfig clientConfig1 = client.getConfig();
        Assert.assertNotNull(clientConfig1);
        Assert.assertSame(clientConfig0, clientConfig1);
    }

    @Test
    public void authorizationCodeClient_2_getToken() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClientConfig clientConfig = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig);
        Assert.assertNotNull(client);
        Assert.assertNull(client.getToken());
    }

    @Test
    public void authorizationCodeClient_3_refreshToken() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClientConfig clientConfig = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig);
        Assert.assertNotNull(client);
        OAuth2AuthorizationCodeClient.RefreshTokenResult refreshTokenResult = client.refreshToken("someToken");
        OAuth2AuthorizationCodeClient.RefreshTokenStatus refreshTokenStatus = refreshTokenResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeClient.RefreshTokenStatus.NotImplemented, refreshTokenStatus);
    }

    @Test
    public void authorizationCodeClient_4_introspectToken() {
        OAuth2ClientFactory oAuth2ClientFactory = OAuth2ClientFactory.getInstance();
        Assert.assertNotNull(oAuth2ClientFactory);
        OAuth2AuthorizationCodeClientConfig clientConfig = new OAuth2AuthorizationCodeClientConfig();
        OAuth2AuthorizationCodeClient client = oAuth2ClientFactory.getAuthorizationCodeClient(clientConfig);
        Assert.assertNotNull(client);
        OAuth2AuthorizationCodeClient.IntrospectTokenResult introspectTokenResult = client.introspectToken("someToken");
        OAuth2AuthorizationCodeClient.IntrospectTokenStatus introspectTokenStatus = introspectTokenResult.getStatus();
        Assert.assertEquals(OAuth2AuthorizationCodeClient.IntrospectTokenStatus.NotImplemented, introspectTokenStatus);
    }
}
