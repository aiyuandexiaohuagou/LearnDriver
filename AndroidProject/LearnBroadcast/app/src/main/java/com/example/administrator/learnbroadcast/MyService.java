package com.example.administrator.learnbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/3/3.
 */
public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent i = new Intent();
        i.setAction("com.example.administrator.learnbroadcast.BROADCAST_FROM_MYSERVICE");
        i.putExtra("msg", "From MyService Broadcast");
        sendBroadcast(i);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

}
