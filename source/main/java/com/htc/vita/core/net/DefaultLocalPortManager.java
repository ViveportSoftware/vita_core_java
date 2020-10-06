package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;

public class DefaultLocalPortManager extends LocalPortManager {
    private static int lastLocalPort = 0;

    private static int doGetUnusedPort(int preferredPort) throws IOException {
        int realPreferredPort = preferredPort;
        if (realPreferredPort < 0 || realPreferredPort > 65535) {
            Logger.getInstance(DefaultLocalPortManager.class.getSimpleName()).warn(StringUtils.rootLocaleFormat(
                    "preferred port number %d is invalid.",
                    realPreferredPort
            ));
            realPreferredPort = 0;
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(realPreferredPort);
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            if (realPreferredPort == 0) {
                throw e;
            }
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // Skip
                }
            }
        }
        return 0;
    }

    @Override
    protected int onGetRandomUnusedPort() throws IOException {
        lastLocalPort = doGetUnusedPort(lastLocalPort);
        if (lastLocalPort == 0) {
            lastLocalPort = doGetUnusedPort(lastLocalPort);
        }
        return lastLocalPort;
    }
}
