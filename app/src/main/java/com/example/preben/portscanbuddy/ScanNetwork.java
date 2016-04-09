package com.example.preben.portscanbuddy;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by Preben on 28/03/2016.
 */
public class ScanNetwork {

    private String ipAddress;
    private String subnetMask;
    private int timeout;

    public ScanNetwork(String ipAddress, String subnetMask, int timeout)
    {
        this.ipAddress = ipAddress;
        this.subnetMask = subnetMask;
        this.timeout = timeout;

        for (String ip : hostsToScan())
        {
            new ScanHostOnline().execute();
        }
    }

    private ArrayList<String> hostsToScan()
    {
        ArrayList<String> hostsList = new ArrayList<>();

        int amountOfHosts = calculateHostsNetwork();
        String networkAddress = calculateNetworkAddress();

        String octetsNetwork[] = networkAddress.split("\\.");

                                                            //example network address
        int firstOctet = Integer.valueOf(octetsNetwork[0]); //192
        int secondOctet = Integer.valueOf(octetsNetwork[1]); //168
        int thirdOctet = Integer.valueOf(octetsNetwork[2]); //0
        int fourthOctet = Integer.valueOf(octetsNetwork[3]); //0

        for (int i = 0; i < amountOfHosts; i++) {
            if (fourthOctet < 255)
            {
                fourthOctet += 1;
            }
            else
            {
                if (thirdOctet < 255)
                {
                    thirdOctet += 1;
                    fourthOctet = 1;
                }
                else
                {
                    if (secondOctet < 255)
                    {
                        secondOctet += 1;
                        thirdOctet = 1;
                    }
                    else
                    {
                        if (firstOctet < 255)
                        {
                            firstOctet += 1;
                            secondOctet = 1;
                        }
                        else
                        {
                            secondOctet += 1;
                        }
                    }
                }
            }
            String ipAddress = String.valueOf(firstOctet) + "." + String.valueOf(secondOctet) + "." + String.valueOf(thirdOctet) + "." + String.valueOf(fourthOctet);
            hostsList.add(ipAddress);
        }

        return hostsList;
    }

    private String calculateNetworkAddress()
    {
        String octetsIp[] = ipAddress.split("\\.");
        String octetsSubnetmask[] = subnetMask.split("\\.");

        String binaryOctectsIp = "";
        String binarySubnetmask = "";

        for (int i = 0; i < octetsIp.length; i++) {
            binaryOctectsIp += String.format("%8s", Integer.toBinaryString(Integer.valueOf(octetsIp[i]))).replace(' ', '0');
        }

        for (int i = 0; i < octetsSubnetmask.length; i++) {
            binarySubnetmask += String.format("%8s", Integer.toBinaryString(Integer.valueOf(octetsSubnetmask[i]))).replace(' ', '0');
        }

        String logicalResult = "";

        for (int i = 0; i < 32; i++) { //logical 'AND' truth table
            if (binaryOctectsIp.charAt(i) == '1' && binarySubnetmask.charAt(i) == '1')
            {
                logicalResult += "1";
            }
            else
            {
                logicalResult += "0";
            }
        }

        String networkAddres = "";

        for (int i = 0; i < 32; i+=8) {
            if (i < 24) {
                networkAddres += Integer.parseInt(logicalResult.substring(i, i + 8), 2) + ".";
            }
            else
            {
                networkAddres += Integer.parseInt(logicalResult.substring(i, i + 8), 2);
            }
        }

        return networkAddres;
    }

    private int calculateHostsNetwork()
    {
        String octets[] = subnetMask.split("\\.");
        String binaryOctets = "";

        for (int i = 0; i < octets.length; i++) {
            binaryOctets += Integer.toBinaryString(Integer.valueOf(octets[i]));
        }

        int networkBitCounter = 0;

        for (int i = 0; i < binaryOctets.length(); i++) {
            if (binaryOctets.charAt(i) == '1')
            {
                networkBitCounter++;
            }
        }

        networkBitCounter = 32 - networkBitCounter;

        String binaryStringAmountHosts = "";

        for (int i = 0; i < networkBitCounter; i++) {
            binaryStringAmountHosts += "1";
        }

        int amountOfHosts =  Integer.parseInt(binaryStringAmountHosts, 2) - 1;

        return amountOfHosts;
    }

    private class ScanHostOnline extends AsyncTask <String, Void, Boolean>
    {
        private String ipAddress;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                if (InetAddress.getByName(ipAddress).isReachable(timeout)){
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isHostAlive) {
            if (isHostAlive)
            {
                Host host = new Host(ipAddress);
            }
        }
    }
}
