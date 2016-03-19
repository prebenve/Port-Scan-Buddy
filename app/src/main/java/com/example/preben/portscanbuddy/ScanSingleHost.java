package com.example.preben.portscanbuddy;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Preben on 19/03/2016.
 */
public class ScanSingleHost {

    private Host host;


    public ScanSingleHost(Host host)
    {
        this.host = host;

        new ScanPort().execute(445); //scant poort445

    }

    //// TODO: 19/03/2016 foreach parameter.length portscan?
    private class ScanPort extends AsyncTask <Integer, Boolean, Boolean> //multithreading class
    {
        private int portNumber;

        @Override
        protected Boolean doInBackground(Integer... portNumberParam) {
            portNumber = portNumberParam[0];

            try {
                Socket socket = new Socket(host.getIp(), portNumber);
                
                return socket.isConnected();

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean portOpen) {

            if (portOpen)
            {
                host.addOpenPort(portNumber);
            }
        }
    }



}
