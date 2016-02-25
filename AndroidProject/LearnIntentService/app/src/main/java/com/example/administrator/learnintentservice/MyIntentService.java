package com.example.administrator.learnintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MyIntentService extends IntentService {
    final private String TAG = "MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 20 * 1000;
        Log.d(TAG, "onStartCommand");
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        }
        Log.d(TAG, "time out");
    }
}
