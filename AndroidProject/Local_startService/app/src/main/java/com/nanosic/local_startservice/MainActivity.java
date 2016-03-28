package com.nanosic.local_startservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assert findViewById(R.id.btn_startService) != null;
        Button buttonStartService = (Button)findViewById(R.id.btn_startService);
        if (buttonStartService != null) {
            buttonStartService.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(MainActivity.this, LocalService.class);
                   startService(i);
               }
           });
        }

        Button buttonStopService = (Button)findViewById(R.id.btn_stopService);
        if (buttonStopService != null) {
            buttonStopService.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, LocalService.class);
                    stopService(i);
                }
            });
        }
    }
}
