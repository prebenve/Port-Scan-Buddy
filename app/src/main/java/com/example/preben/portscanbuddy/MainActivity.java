package com.example.preben.portscanbuddy;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

    private EditText txtHost;
    private Button btnScan;
    private TextView txtInfoPorts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHost = (EditText) findViewById(R.id.txtHost);
        btnScan = (Button) findViewById(R.id.btnScan);
        txtInfoPorts = (TextView) findViewById(R.id.txtPortInfo);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "btnScan touched", Toast.LENGTH_LONG).show();
                //scanHost(txtHost.toString());
                scanHost("192.168.0.200");
            }
        });
    }

    private void scanHost(String ip)
    {
        Host host = new Host(ip, txtInfoPorts);
        ScanSingleHost scanHost = new ScanSingleHost(host);
    }

}
