package com.htc.vita.core.net;

import com.htc.vita.core.log.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Locale;
import java.util.Map;

public class NetworkInterfaceTest {
    @Test
    public void default_0_getLocalIPv4AddressesWithBroadcastAddress() {
        Map<InetAddress, InetAddress> addressMapping = NetworkInterface.getLocalIPv4AddressesWithBroadcastAddress();
        Assert.assertNotNull(addressMapping);
        Assert.assertTrue(addressMapping.size() > 0);
        for (InetAddress localAddress : addressMapping.keySet()) {
            Assert.assertNotNull(localAddress);
            InetAddress broadcastAddress = addressMapping.get(localAddress);
            Assert.assertNotNull(broadcastAddress);
            Logger.getInstance(NetworkInterfaceTest.class.getSimpleName()).info(String.format(
                    Locale.ROOT,
                    "localAddress: %s, broadcastAddress: %s",
                    localAddress.getHostAddress(),
                    broadcastAddress.getHostAddress()
            ));
        }
    }

    @Test
    public void default_1_isNetworkAvailable() {
        Assert.assertTrue(NetworkInterface.isNetworkAvailable());
    }
}
