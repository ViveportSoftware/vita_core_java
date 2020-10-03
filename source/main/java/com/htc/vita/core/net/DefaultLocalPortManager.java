package com.htc.vita.core.net;

import java.io.IOException;
import java.net.ServerSocket;

public class DefaultLocalPortManager extends LocalPortManager {
    @Override
    protected int onGetRandomUnusedPort() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(0);
            return serverSocket.getLocalPort();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // Skip
                }
            }
        }
    }
}
