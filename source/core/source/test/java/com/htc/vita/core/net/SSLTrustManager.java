package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLTrustManager {
    public static void trustAll() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(
                    null,
                    new TrustManager[] {
                            new X509TrustManager() {
                                    @Override
                                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                        return null;
                                    }

                                    @Override
                                    public void checkClientTrusted(
                                            java.security.cert.X509Certificate[] certs,
                                            String authType) {
                                    }

                                    @Override
                                    public void checkServerTrusted(
                                            java.security.cert.X509Certificate[] certs,
                                            String authType) {
                                    }
                            }
                    },
                    new java.security.SecureRandom()
            );
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            Logger.getInstance(SSLTrustManager.class.getSimpleName()).error(e.toString());
        }
    }
}
