package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.TypeRegistry;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public abstract class OAuth2AuthorizationCodeUserAgent implements Closeable {
    public static final String OBJECT_ANDROID_WEBVIEW_INSTANCE = "android_webview_instance";
    public static final String OPTION_AUTHORIZATION_URL = "authorization_uri";
    public static final String OPTION_ANDROID_JAVASCRIPT_ACTION_ON_URL_PREFIX_FINISHED = "android_javascript_action_on_url_prefix_finished";

    static {
        TypeRegistry.registerDefault(
                OAuth2AuthorizationCodeUserAgent.class,
                DummyOAuth2AuthorizationCodeUserAgent.class
        );
    }

    public static <T extends OAuth2AuthorizationCodeUserAgent> void register(Class<T> clazz) {
        TypeRegistry.register(
                OAuth2AuthorizationCodeUserAgent.class,
                clazz
        );
    }

    public static OAuth2AuthorizationCodeUserAgent getInstance() {
        return TypeRegistry.getInstance(OAuth2AuthorizationCodeUserAgent.class);
    }

    public static <T extends OAuth2AuthorizationCodeUserAgent> OAuth2AuthorizationCodeUserAgent getInstance(Class<T> clazz) {
        return TypeRegistry.getInstance(
                OAuth2AuthorizationCodeUserAgent.class,
                clazz
        );
    }

    public OAuth2AuthorizationCodeUserAgent initialize() {
        return initialize(
                null,
                null
        );
    }

    public OAuth2AuthorizationCodeUserAgent initialize(
            Map<String, Object> options,
            CancellationToken cancellationToken) {
        Map<String, Object> realOptions = options;
        if (realOptions == null) {
            realOptions = new HashMap<String, Object>();
        }
        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        OAuth2AuthorizationCodeUserAgent result = null;
        try {
            result = onInitialize(
                    realOptions,
                    realCancellationToken
            );
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeUserAgent.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public LaunchResult launch() {
        LaunchResult result = null;
        try {
            result = onLaunch();
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeUserAgent.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new LaunchResult();
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeUserAgent onInitialize(
            Map<String, Object> options,
            CancellationToken cancellationToken
    ) throws Exception;
    protected abstract LaunchResult onLaunch() throws Exception;

    public static class LaunchResult {
        private LaunchStatus mStatus = LaunchStatus.Unknown;

        public LaunchStatus getStatus() {
            return mStatus;
        }

        public LaunchResult setStatus(LaunchStatus status) {
            if (mStatus != null) {
                mStatus = status;
            }
            return this;
        }
    }

    public enum LaunchStatus {
        Unknown,
        Ok,
        CancelledLaunching,
        InvalidAuthorizationUri,
        NotImplemented,
        UnsupportedUserAgent
    }
}
