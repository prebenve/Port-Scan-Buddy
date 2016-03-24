package com.example.preben.portscanbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnScanHost;
    private Button btnScanNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanHost = (Button) findViewById(R.id.btnScanHost);
        btnScanNetwork = (Button) findViewById(R.id.btnScanNetwork);

        btnScanHost.setOnClickListener(listener);
        btnScanNetwork.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btnScanHost:

                    Intent intentScanHost = new Intent(getApplicationContext(), ScanHostIntent.class);
                    startActivity(intentScanHost);
                    break;

                case R.id.btnScanNetwork:

                    Intent intentScanNetwork = new Intent(getApplicationContext(), ScanNetworkIntent.class);
                    startActivity(intentScanNetwork);
                    break;

                default:
                    break;
            }
        }
    };

}
