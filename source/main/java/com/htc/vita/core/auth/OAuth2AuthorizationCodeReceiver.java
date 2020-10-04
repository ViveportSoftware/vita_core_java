package com.htc.vita.core.auth;

import com.htc.vita.core.concurrent.CancellationToken;
import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;

public abstract class OAuth2AuthorizationCodeReceiver implements Closeable {
    public static final String OPTION_REDIRECT_URI = "redirect_uri";

    public OAuth2AuthorizationCodeReceiver initialize(
            Map<String, String> options,
            CancellationToken cancellationToken) {
        Map<String, String> realOptions = options;
        if (realOptions == null) {
            realOptions = new HashMap<String, String>();
        }
        CancellationToken realCancellationToken = cancellationToken;
        if (realCancellationToken == null) {
            realCancellationToken = CancellationToken.NONE;
        }
        OAuth2AuthorizationCodeReceiver result = null;
        try {
            result = onInitialize(
                    realOptions,
                    realCancellationToken
            );
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeReceiver.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = this;
        }
        return result;
    }

    public ReceiveResult receive() {
        ReceiveResult result = null;
        try {
            result = onReceive();
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            Logger.getInstance(OAuth2AuthorizationCodeReceiver.class.getSimpleName()).error(e.toString());
        }
        if (result == null) {
            result = new ReceiveResult();
        }
        return result;
    }

    protected abstract OAuth2AuthorizationCodeReceiver onInitialize(
            Map<String, String> options,
            CancellationToken cancellationToken
    ) throws Exception;
    protected abstract ReceiveResult onReceive() throws Exception;

    public static class ReceiveResult {
        private String mCode;
        private ReceiveStatus mStatus = ReceiveStatus.Unknown;

        public String getCode() {
            return mCode;
        }

        public ReceiveStatus getStatus() {
            return mStatus;
        }

        public ReceiveResult setCode(String code) {
            if (!StringUtils.isNullOrWhiteSpace(code)) {
                mCode = code;
            }
            return this;
        }

        public ReceiveResult setStatus(ReceiveStatus status) {
            if (mStatus != null) {
                mStatus = status;
            }
            return this;
        }
    }

    public enum ReceiveStatus {
        Unknown,
        Ok,
        CancelledReceiving,
        InvalidAuthorizationCode,
        NotImplemented,
        UnsupportedReceiver
    }
}
