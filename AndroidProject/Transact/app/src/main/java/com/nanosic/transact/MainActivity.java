package com.nanosic.transact;

import android.app.AlarmManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                data.writeInt(10);
                data.writeInt(20);
                try {
                    /*
                     * transact()，将包含数据的data，调用binder对象的onTransact()方法，
                     * 接收onTransact()写入的数据reply，通过reply得到执行结果
                     * 实际上，service中的binder的onTransact()方法在主线程中调用，不能执行耗时操作
                     * 可以在执行service.transact(100, data, reply, 0);时使用多线程
                     */
                    service.transact(100, data, reply, 0);
                    int result = reply.readInt();
                    Log.d(TAG, "onServiceConnected: result=" + result);
                    //得到结果
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                data.recycle();
                reply.recycle();
            };
        };
        bindService(new Intent(MainActivity.this, MyService.class), conn, Service.BIND_AUTO_CREATE);
    }

}
