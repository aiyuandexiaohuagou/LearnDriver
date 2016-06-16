package com.nanosic.testremoteservice;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    Intent intent = new Intent("com.nanosic.tvservice.service");

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");

        super.onCreate(savedInstanceState);
        intent.setPackage("com.example.administrator.learnremoteproject");
        intent.setAction("com.example.administrator.MyRemoteService");
        Log.i(TAG, "onCreate: bindService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
        intent.setPackage("com.example.administrator.learnremoteproject");
        intent.setAction("com.example.administrator.MyRemoteService");
        Log.i(TAG, "onRestart: bindService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
