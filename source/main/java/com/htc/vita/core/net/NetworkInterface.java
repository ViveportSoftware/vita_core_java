package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import com.htc.vita.core.util.StringUtils;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class NetworkInterface {
    public static Map<InetAddress, InetAddress> getLocalIPv4AddressesWithBroadcastAddress()
    {
        Map<InetAddress, InetAddress> result = new HashMap<InetAddress, InetAddress>();
        try {
            Enumeration<java.net.NetworkInterface> networkInterfaces = java.net.NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                java.net.NetworkInterface networkInterface = networkInterfaces.nextElement();
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    if (interfaceAddress == null) {
                        continue;
                    }

                    InetAddress inetAddress = interfaceAddress.getAddress();
                    if (inetAddress == null) {
                        continue;
                    }

                    InetAddress broadcastAddress = interfaceAddress.getBroadcast();
                    if (broadcastAddress == null || "0.0.0.0".equals(broadcastAddress.getHostAddress())) {
                        continue;
                    }

                    result.put(
                            inetAddress,
                            broadcastAddress
                    );
                }
            }
        } catch (Exception e) {
            Logger.getInstance(NetworkInterface.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                    "Can not get local IP addresses with broadcast addresses, error: %s",
                    e.getMessage()
            ));
        }

        return result;
    }

    public static boolean isNetworkAvailable()
    {
        try {
            Enumeration<java.net.NetworkInterface> networkInterfaces = java.net.NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                java.net.NetworkInterface networkInterface = networkInterfaces.nextElement();

                if (!networkInterface.isLoopback() && networkInterface.isUp()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Logger.getInstance(NetworkInterface.class.getSimpleName()).error(StringUtils.rootLocaleFormat(
                    "Can not detect if network is available, error: %s",
                    e.getMessage()
            ));
        }

        return false;
    }
}
