package com.example.preben.portscanbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ScanHostIntent extends AppCompatActivity {

    private Button btnScanHost;
    private TextView txtOpenPorts;
    private EditText txtHost;
    private Spinner spinnerTypeScan;
    private Spinner spinnerSocketTimeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_host);

        btnScanHost = (Button) findViewById(R.id.btnScanHost);
        txtHost = (EditText) findViewById(R.id.txtHost);
        txtOpenPorts = (TextView) findViewById(R.id.txtOpenPorts);
        spinnerTypeScan = (Spinner) findViewById(R.id.spinnerTypeScan);
        spinnerSocketTimeout = (Spinner) findViewById(R.id.spinnerSocketTimeout);

        populateSpinners();

        btnScanHost.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String hostName = txtHost.getText().toString().trim();

            if (!hostName.isEmpty())
            {
                Host host = new Host(hostName, txtOpenPorts);
                int timeout = (spinnerSocketTimeout.getSelectedItemPosition() + 1) * 50;

                if (spinnerTypeScan.getSelectedItemPosition() == 0) //most common ports
                {
                    new ScanPorts(host, timeout).scanMostCommonPorts();
                } else {
                    new ScanPorts(host, timeout).scanAllPorts();
                }
            }
            else
            {
                txtOpenPorts.setText("Please enter a valid IP address / host name");
            }
        }
    };

    private void populateSpinners()
    {
        String socketTimeouts[] = new String[10];

        for (int i = 0; i < 10; i++) {
            socketTimeouts[i] = (i+1) * 50 + " milliseconds";
        }

        ArrayAdapter<CharSequence> adapterSpinnerTypeScan = ArrayAdapter.createFromResource(this,R.array.portsArray, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterSpinnerSocketTimeout = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, socketTimeouts);

        adapterSpinnerTypeScan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterSpinnerSocketTimeout.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTypeScan.setAdapter(adapterSpinnerTypeScan);
        spinnerSocketTimeout.setAdapter(adapterSpinnerSocketTimeout);
        spinnerSocketTimeout.setSelection(1); //set default socket timeout to 100ms
    }


}
