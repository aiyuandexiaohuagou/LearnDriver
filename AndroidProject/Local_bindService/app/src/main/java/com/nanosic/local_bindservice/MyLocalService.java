package com.nanosic.local_bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/28.
 */
public class MyLocalService extends Service {
    private final String TAG = "MyLocalService";
    public MyBinder mBinder;
    public class MyBinder extends Binder {
        public MyLocalService getService() {
            return MyLocalService.this;
        }

        public int add(int a, int b) {
            Log.d(TAG, "add: " + a + "+" + b);
            return a + b;
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        mBinder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
    }
}
