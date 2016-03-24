package com.example.preben.portscanbuddy;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;

/**
 * Created by Preben on 24/03/2016.
 */
public class Network {

    private String ip;
    private String subnetmask;
    private List<Host> hostList;

    public Network(Context context)
    {
        ip = getIP(context);

    }

    protected String getIP (Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            ipAddressString = null;
        }

        return ipAddressString;
    }
}
