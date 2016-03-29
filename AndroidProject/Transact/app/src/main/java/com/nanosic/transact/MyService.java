package com.nanosic.transact;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/28.
 */
public class MyService extends Service {
    private final String TAG = "MyService";
    private Mybinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new Mybinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class Mybinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply,
                                     int flags) throws RemoteException {
			/*
			 * Parcel data	activity写入的数据
			 * Parcel reply	执行完成之后写入的数据
			 */
            int x = data.readInt();
            int y = data.readInt();
            Log.d(TAG, "onTransact: x=" + x + ", y=" + y);
            reply.writeInt(x+y);

            return true;
        }
    }
}
