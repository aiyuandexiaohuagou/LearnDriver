package com.example.administrator.learnremoteproject;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/14.
 */
public class MyApp extends Application {
    private static final String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }


}
