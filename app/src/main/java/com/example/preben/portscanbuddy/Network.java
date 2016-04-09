package com.example.preben.portscanbuddy;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Preben on 24/03/2016.
 */
public class Network {

    private String ip;
    private String subnetmask;
    private List<Host> hostList;

    public Network()
    {
        hostList = new ArrayList<>();
    }


}
