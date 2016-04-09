package com.example.preben.portscanbuddy;

import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by Preben on 18/03/2016.
 */
public class Host {

    private ArrayList<Integer> ports;
    private String ip;
    private String hostName;
    private Boolean isAlive;
    private TextView txtOpenPorts;

    public Host(String ip, TextView txtOpenPorts)
    {
        this.ip = ip;
        this.txtOpenPorts = txtOpenPorts;

        ports = new ArrayList<>();
    }

    public Host(String ip)
    {
        this.ip = ip;
        ports = new ArrayList<>();
    }

    public void addOpenPort(int portNumber)
    {
        ports.add(portNumber);
        updateUI();
    }

    public void setIsAlive(Boolean isAlive)
    {
        this.isAlive = isAlive;
    }

    private void updateUI()
    {
        if (ports.size() <= 1) {
            txtOpenPorts.setText("Open port: ");
            txtOpenPorts.append(ports.toString());
        }
        else
        {
            txtOpenPorts.setText("Open ports: ");
            txtOpenPorts.append(ports.toString());
        }
    }

    public String getIp()
    {
        return ip;
    }

}
