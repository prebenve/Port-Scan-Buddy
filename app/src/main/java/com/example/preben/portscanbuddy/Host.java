package com.example.preben.portscanbuddy;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Preben on 18/03/2016.
 */
public class Host {

    private ArrayList<Integer> ports;
    private String ip;
    private String hostName;
    private TextView txtPortsInfo;

    public Host(String ip, TextView txtPortsInfo)
    {
        this.ip = ip;
        this.txtPortsInfo = txtPortsInfo;

        ports = new ArrayList<>();
    }

    public void addOpenPort(int portNumber)
    {
        ports.add(portNumber);
        updateUI();
    }

    private void updateUI()
    {
        txtPortsInfo.setText(ports.toString());
    }

    public String getIp()
    {
        return ip;
    }

}
