package com.nanosic.local_bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private ServiceConnection sc;
    boolean isBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyLocalService.MyBinder binder = (MyLocalService.MyBinder)service;
                Log.d(TAG, "onServiceConnected: " + binder.getService().toString());
                Log.d(TAG, "binder.add(3, 5)=" + binder.add(3, 5));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: ");
            }
        };

        assert findViewById(R.id.btn_bindService) != null;
        Button buttonBindService = (Button)findViewById(R.id.btn_bindService);
        if (buttonBindService != null) {
            buttonBindService.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   bindService(new Intent(MainActivity.this, MyLocalService.class), sc, Context.BIND_AUTO_CREATE);
                   isBind = true;
               }
           });
        }

        Button buttonUnBindService = (Button)findViewById(R.id.btn_unbindService);
        if (buttonUnBindService != null) {
            buttonUnBindService.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isBind) {
                        unbindService(sc);
                        isBind = false;
                    }
                }
            });
        }
    }
}
