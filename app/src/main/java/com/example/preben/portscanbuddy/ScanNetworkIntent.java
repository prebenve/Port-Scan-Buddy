package com.example.preben.portscanbuddy;

import android.app.Activity;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class ScanNetworkIntent extends AppCompatActivity {

    private EditText txtIpAddress;
    private EditText txtSubnetmask;
    private Button btnScanNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_network);

        txtIpAddress = (EditText) findViewById(R.id.txtIpAddress);
        txtSubnetmask = (EditText) findViewById(R.id.txtSubnetmask);
        btnScanNetwork = (Button) findViewById(R.id.btnScanNetwork);

        txtIpAddress.setText(getIP());
        txtSubnetmask.setText(getSubnetmask());

        btnScanNetwork.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String ipAddress = txtIpAddress.getText().toString();
            String subnetMask = txtSubnetmask.getText().toString();

            ScanNetwork scanNetwork = new ScanNetwork(ipAddress, subnetMask, 500);
        }
    };

    private String getIP()
    {
        WifiManager wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        return intToIp(dhcpInfo.ipAddress);
    }

    private String getSubnetmask()
    {
        WifiManager wifiManager = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        return intToIp(dhcpInfo.netmask);
    }

    private String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ((i >> 24 ) & 0xFF) ;
    }

}
